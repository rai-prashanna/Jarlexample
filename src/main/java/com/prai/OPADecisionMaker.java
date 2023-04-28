package com.prai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class OPADecisionMaker {

    private static String coarsegrainedendpoint = "http://testserver:32323/v1/data/authz/redfish/v1/policy";
    private static String finegrainedendpoint = "http://testserver:32323/v1/data/authz/redfish/v1/fine/policy";

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
            System.out.println("OPADecisionMaker InputJSON created .....  ");
            System.out.print(inputJson);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                    .uri(URI.create(coarsegrainedendpoint))
                    .setHeader("User-Agent", "PRASHANNA")
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println("OPADecisionMaker httpResponse .....  ");
            System.out.print(httpResponse);

            response = objectMapper.readValue(httpResponse.body(), SingleOPAResponse.class);
            System.out.println("OPADecisionMaker httpResponse Body....");
            System.out.print(httpResponse.body());

            opaDecision = response.getResult().isAllow();

            System.out.println("OPADecisionMaker Value of allow....");
            System.out.print(opaDecision);
            return response.getResult().isAllow();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<String> isAllowed (List < String > uris, String method, List < String > roles){
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
            System.out.println("OPADecisionMaker BulkInputJSON created .....");
            System.out.print(inputJson);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                    .uri(URI.create(finegrainedendpoint))
                    .setHeader("User-Agent", "PRASHANNA")
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            response = objectMapper.readValue(httpResponse.body(), BulkOPAResponse.class);
            allowedUris = response.getResult().getBatchAllow();
            System.out.println("OPADecisionMaker Bulk Value of allow .....");
            System.out.print(allowedUris);
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