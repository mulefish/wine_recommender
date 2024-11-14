package logic;

import java.util.ArrayList;
import java.util.Map;

public class Outside {
    public static void main(String... strings) {

        long startTime = System.currentTimeMillis(); // Start timing


        String url = "jdbc:postgresql://localhost:5432/test_db";
        String user = "postgres";
        String password = "topsecret";


        XYZ xyz = new XYZ();
        ArrayList<MyWine> wines = xyz.getWines(url, user, password);

        Map<String, Map<String, double[]>> everything = xyz.getEmbeddings(wines);

        // Loop through the outer map
        for (Map.Entry<String, Map<String, double[]>> outerEntry : everything.entrySet()) {
            // Print the outer key
            String outerKey = outerEntry.getKey();
//            System.out.println("Outer Key: " + outerKey);

            // Loop through the inner map
            Map<String, double[]> innerMap = outerEntry.getValue();
            for (Map.Entry<String, double[]> innerEntry : innerMap.entrySet()) {
                // Print the inner key and the length of the inner double array
                String innerKey = innerEntry.getKey();
                int arrayLength = innerEntry.getValue().length;
                System.out.println("Outer: " + outerKey + "    Inner: " + innerKey + " has " + arrayLength);
            }
        }



        long endTime = System.currentTimeMillis(); // End timing
        long duration = endTime - startTime; // Calculate duration


        System.out.println("everything length = " + everything.size() + " milsec " + duration );

    }

}
