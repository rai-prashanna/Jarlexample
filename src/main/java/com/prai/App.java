package com.prai;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import by.borge.jarl.Jarl;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String uri="files/upload/updateservice/package";
        String method = "POST";
        List<String> roles=Arrays.asList("OmcEquipmentObserver",
                "CreateJob",
                "DeleteJob",
                "OmcEquipmentAdministrator");
        boolean opadecision=OPADecisionMaker.isAllowed(uri,method,roles);
        if (opadecision) {
            System.out.println( "OPA Decision Allowed" );
        }
//        System.out.println( "******************************************" );
       List<String> uris=Arrays.asList("/Managers/my-pod/Oem/Ericsson_2/RemoteBackupService/Actions/Ericsson2RemoteBackupService.CreateAndTransferBackup",
               "/TaskService/Tasks/1/",
               "/Managers/prai-pod/Oem/Ericsson_2/RemoteBackupService/Actions/Ericsson2RemoteBackupService.CreateAndTransferBackup",
               "/Managers/vanja-pod/Oem/Ericsson_2/RemoteBackupService/Actions/Ericsson2RemoteBackupService.CreateAndTransferBackup",
               "files/upload/updateservice/package",
               "/TaskService/Tasks/3/");
//        String method1 = "GET";
//
//        List<String> allowedUris=OPADecisionMaker.isAllowed(uris,method1,roles);
//        System.out.println( "******************************************" );
//        System.out.println( "" );
//
//
//        allowedUris.forEach(System.out::println);
        System.out.println( "******************************************" );

       boolean localDecision= OPADecisionMaker.isAllowedJarl(uri,method,roles);
        System.out.println( "The value of decision from Jarl " );
        System.out.println( localDecision );

        OPADecisionMaker.isAllowedJarl(uris,method,roles);

    }
}
