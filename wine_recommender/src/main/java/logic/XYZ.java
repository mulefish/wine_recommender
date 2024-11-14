package logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class XYZ {

    private final String PATH = "C:\\Users\\squar\\jars\\glove.6B\\glove.6B.50d.txt";

    List<MyWine> wines = new ArrayList<>();

    // HashSets to collect unique values for each dimension
    private HashSet<String> typeSet = new HashSet<>();
    private HashSet<String> varietySet = new HashSet<>();
    private HashSet<String> regionSet = new HashSet<>();
    private HashSet<String> topNoteSet = new HashSet<>();
    private HashSet<String> bottomNoteSet = new HashSet<>();

    // Maps to store embeddings for each unique term
    private Map<String, double[]> typeEmbeddings = new HashMap<>();
    private Map<String, double[]> varietyEmbeddings = new HashMap<>();
    private Map<String, double[]> regionEmbeddings = new HashMap<>();
    private Map<String, double[]> topNoteEmbeddings = new HashMap<>();
    private Map<String, double[]> bottomNoteEmbeddings = new HashMap<>();
    private Map<String, Map<String, double[]>> everything = new HashMap<>();

    private Map<String, double[]> wordEmbeddings = new HashMap<>(); // All GloVe embeddings

    public XYZ() {
        // Load the GloVe model
        loadGloveModel(PATH);
    }

    public ArrayList<MyWine> getWines(String url, String user, String password) {
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
                String topNote = rs.getString("topnote");
                String bottomNote = rs.getString("bottomnote");

                MyWine wine = new MyWine(id, type, variety, year, region, price, topNote, bottomNote);
                wines.add(wine);

                // Collect unique strings for each dimension
                typeSet.add(type);
                varietySet.add(variety);
                regionSet.add(region);
                topNoteSet.add(topNote);
                bottomNoteSet.add(bottomNote);
            }

            // Get embeddings for each unique string
            populateEmbeddings(typeSet, typeEmbeddings);
            populateEmbeddings(varietySet, varietyEmbeddings);
            populateEmbeddings(regionSet, regionEmbeddings);
            populateEmbeddings(topNoteSet, topNoteEmbeddings);
            populateEmbeddings(bottomNoteSet, bottomNoteEmbeddings);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(wines); // Return the populated wines list
    }

    private void loadGloveModel(String gloveFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(gloveFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(" ");
                String word = values[0];
                double[] vector = new double[values.length - 1];
                for (int i = 1; i < values.length; i++) {
                    vector[i - 1] = Double.parseDouble(values[i]);
                }
                wordEmbeddings.put(word, vector);
            }
            //System.out.println("Loaded GloVe model with " + wordEmbeddings.size() + " words.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateEmbeddings(HashSet<String> set, Map<String, double[]> embeddingsMap) {
        for (String term : set) {
            double[] vector = wordEmbeddings.get(term.toLowerCase()); // Lowercase for consistency with GloVe
            if (vector != null) {
                embeddingsMap.put(term, vector);
            } else {
                //      System.out.println("No embedding found for: " + term);
            }
        }
    }


    public Map<String, Map<String, double[]>> getEmbeddings(ArrayList<MyWine> wines) {

        everything.put("type", typeEmbeddings);
        everything.put("variety", varietyEmbeddings);
        everything.put("region", regionEmbeddings);
        everything.put("topNote", topNoteEmbeddings);
        everything.put("bottomNote", bottomNoteEmbeddings);


        return everything;
    }
}
