package com.prai;

import java.util.Arrays;
import java.util.List;

/**
 * Testing with Jarl library!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "******************************************" );
        String uri1="Chassis";
        String method1 = "GET";
        List<String> roles1=Arrays.asList("OmcEquipmentAdministrator");
       boolean localDecision= OPADecisionMaker.isAllowedJarl(uri1,method1,roles1);
        List<String> roles=Arrays.asList("CreateJob",
                "DeleteJob",
                "OmcEquipmentAdministrator");

        List<String> uris=Arrays.asList("/TaskService/Tasks/1/",
                "files/upload/updateservice/package",
                "/TaskService/Tasks/3/",
                "Chassis");
        List<String> methods=Arrays.asList("GET","GET","GET","GET");
        List<String> allowedUris=OPADecisionMaker.isAllowedJarl(uris,methods,roles);
        System.out.println( "The value of decision from Jarl " );
    }
}
