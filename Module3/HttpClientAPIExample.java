import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientAPIExample {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient(); // cite: 91
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/octocat")) // Example public API
                .build(); // cite: 91

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response Status Code: " + response.statusCode()); // cite: 92
            System.out.println("Response Body:\n" + response.body()); // cite: 92

            // Optional: Parse JSON response using a library like Jackson or Gson
            // You would need to add these libraries to your project's classpath.
            // Example with Gson:
            // Gson gson = new Gson();
            // GitHubUser user = gson.fromJson(response.body(), GitHubUser.class);
            // System.out.println("GitHub User Name: " + user.name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Optional: If you want to parse JSON, define a simple POJO
// class GitHubUser {
//     String login;
//     int id;
//     String node_id;
//     String name;
//     String company;
//     // ... other fields
// }