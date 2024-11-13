package logic;

import java.sql.*;
import java.util.*;

public class Main {

//    private static String path = "C:\\Users\\squar\\jars\\word2vec-GoogleNews-vectors-master\\word2vec-GoogleNews-vectors-master\\GoogleNews-vectors-negative300.bin.gz" ;
//    private static GoogleNewsEmbeddings googleEmbeddings = new GoogleNewsEmbeddings(path);


    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/test_db";
        String user = "postgres";
        String password = "topsecret";

//        List<MyWine> wineList = new ArrayList<>(); // Collection to store MyWine objects

        // Maps to store unique strings with their Word2Vec vectors
        Map<String, double[]> typeVectors = new HashMap<>();
        Map<String, double[]> varietyVectors = new HashMap<>();
        Map<String, double[]> regionVectors = new HashMap<>();
        Map<String, double[]> topnoteVectors = new HashMap<>();
        Map<String, double[]> bottomnoteVectors = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement()) {

            String query = "SELECT id, type, variety, year, region, price, topnote, bottomnote FROM wines;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                String variety = rs.getString("variety");
                int year = rs.getInt("year");
                String region = rs.getString("region");
                int price = rs.getInt("price");
                String topnote = rs.getString("topnote");
                String bottomnote = rs.getString("bottomnote");
System.out.println("id: " + id ) ;
//                MyWine wine = new MyWine(id, type, variety, year, region, price, topnote, bottomnote);
//                wineList.add(wine);

//                // Populate maps with unique strings as keys and Word2Vec vectors as values
//                typeVectors.putIfAbsent(type, generateWord2VecVector(type));
//                varietyVectors.putIfAbsent(variety, generateWord2VecVector(variety));
//                regionVectors.putIfAbsent(region, generateWord2VecVector(region));
//                topnoteVectors.putIfAbsent(topnote, generateWord2VecVector(topnote));
//                bottomnoteVectors.putIfAbsent(bottomnote, generateWord2VecVector(bottomnote));
            }

//            // Output the maps for inspection (optional)
//            System.out.println("Type Vectors: " + typeVectors);
//            System.out.println("Variety Vectors: " + varietyVectors);
//            System.out.println("Region Vectors: " + regionVectors);
//            System.out.println("Topnote Vectors: " + topnoteVectors);
//            System.out.println("Bottomnote Vectors: " + bottomnoteVectors);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Mock method to generate Word2Vec vector
    public static double[] generateWord2VecVector(String word) {
        // Replace with actual Word2Vec vector generation code
        return new double[]{0.0, 1.0, 2.0}; // Example vector
    }
}
