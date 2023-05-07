package com.prai;

import by.borge.jarl.Jarl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OPADecisionMaker {
    private static String serverIP= "testserver";
    private static String serverPort= "32323";
    private static String coarsegrainedendpoint = "http://"+serverIP+":"+serverPort+"/v1/data/authz/redfish/v1/policy";
    private static String finegrainedendpoint = "http://"+serverIP+":"+serverPort+"/v1/data/authz/redfish/v1/fine/policy";

    private static String coarsedGrainedIRFile= "/repo/policy/optimizedPlans/coarse-grained-policies-plan.json";
    private static String coarsedGrainedentrypoint= "authz/redfish/v1/policy/allow";
    private static String fineGrainedIRFile= "/repo/policy/optimizedPlans/fine-grained-policies-plan.json";
    private static String fineGrainedIRFileentrypoint="authz/redfish/v1/fine/policy/batch_allow";
    public static boolean isAllowedJarl(String uri, String method, List<String> roles) {
        var file = new File(coarsedGrainedIRFile);
        boolean decision = false;

        Map<String, ?> data = Map.of();
        Map<String,Object> map = new HashMap<>();
        map.put("roles", roles);
        map.put("method",method);
        map.put("resource",uri);

        try {
            decision= Jarl.builder(file)
                    .build()
                    .getPlan(coarsedGrainedentrypoint)
                    .eval(map, data).allowed();
        } catch (IOException e) {
            System.out.println( "Exception while making decision by Jarl library " +e.getStackTrace() );
        }
        finally{
            return decision;
        }
    }


    public static List<String> isAllowedJarl(List < String > uris, List < String > methods, List < String > roles) {
      //  String IRFile2= "/repo/policy/optimizedPlans/fine-grained-policies-plan.json";
        var file = new File(fineGrainedIRFile);
        List<String> allowedUrls= Collections.EMPTY_LIST;
        Map<String, ?> data = Map.of();
        Map<String,Object> map = new HashMap<>();
        map.put("roles", roles);
        map.put("methods",methods);
        map.put("resources",uris);
        try {
            var resultSet = Jarl.builder(file)
                    .build()
                    .getPlan(fineGrainedIRFileentrypoint)
                    .eval(map, data);
            allowedUrls= (List<String>) resultSet.getFirst().getValue(List.class).stream().map(Object::toString).collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println( "Exception while making decision by Jarl library " +e.getStackTrace() );
        }
        finally{
            return allowedUrls;
        }
    }

    public static boolean isAllowed(String uri, String method, List<String> roles) {
        boolean opaDecision;
        ObjectMapper objectMapper = new ObjectMapper();
        SingleOPAInput input = new SingleOPAInput();
        input.setResource(uri);
        input.setMethod(method);
        input.setRoles(roles);
        SingleOPARequest opaRequest = new SingleOPARequest(input);
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        SingleOPAResponse response;
        try {
            String inputJson = objectMapper.writeValueAsString(opaRequest);

        //    LOGGER.info("OPADecisionMaker InputJSON created .....{}", inputJson);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                    .uri(URI.create(coarsegrainedendpoint))
                    .setHeader("User-Agent", "PRASHANNA")
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            response = objectMapper.readValue(httpResponse.body(), SingleOPAResponse.class);

        //    LOGGER.info("OPADecisionMaker httpResponse Body....{}", httpResponse.body());

            opaDecision = response.getResult().isAllow();
            System.getProperty("user.dir");
//            LOGGER.info("OPADecisionMaker Value of allow....{}", opaDecision);
//            LOGGER.info("OPADecisionMaker my current Directory....{}", System.getProperty("user.dir"));

            return response.getResult().isAllow();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<String> isAllowed (List < String > uris, String  method, List < String > roles){
        List<String> allowedUris = Collections.EMPTY_LIST;
        ObjectMapper objectMapper = new ObjectMapper();
        BulkOPAInput input = new BulkOPAInput();
        input.setMethod(method);
        input.setRoles(roles);
        input.setResources(uris);
        BulkOPARequest opaRequest = new BulkOPARequest(input);
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        BulkOPAResponse response;
        try {
            String inputJson = objectMapper.writeValueAsString(opaRequest);

          //  LOGGER.info("OPADecisionMaker BulkInputJSON created .....{}", inputJson);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                    .uri(URI.create(finegrainedendpoint))
                    .setHeader("User-Agent", "PRASHANNA")
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            response = objectMapper.readValue(httpResponse.body(), BulkOPAResponse.class);
            allowedUris = response.getResult().getBatchAllow();

//            LOGGER.info("OPADecisionMaker Bulk Value of allow....{}", allowedUris);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            return allowedUris;
        }
    }

}
