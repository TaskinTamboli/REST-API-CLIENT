import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class RestApiClient {

    public static void main(String[] args) {
        try {
            // Public REST API (dummy test API)
            String apiUrl = "https://jsonplaceholder.typicode.com/posts";

            // Create URL object
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Request setup
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            // Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONArray jsonArray = new JSONArray(response.toString());

            // Display first 5 posts
            System.out.println("=== Sample Posts from REST API ===");
            for (int i = 0; i < 5; i++) {
                JSONObject post = jsonArray.getJSONObject(i);
                System.out.println("ID: " + post.getInt("id"));
                System.out.println("Title: " + post.getString("title"));
                System.out.println("Body: " + post.getString("body"));
                System.out.println("---------------------------");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
