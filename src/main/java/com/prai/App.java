package com.prai;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import by.borge.jarl.Jarl;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        var file = new File("/repo/policy2/plan.json");
        var input = Map.of("message", "world");
        Map<String, ?> data = Map.of();
        boolean allowed = false;
        try {
            allowed = Jarl.builder(file)
                    .build()
                    .getPlan("policy/hello")
                    .eval(input, data)
                    .allowed();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (allowed) {
            System.out.println( "Allowed" );
        }
    }
}
