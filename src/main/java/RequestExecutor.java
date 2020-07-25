import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestExecutor {
    String ressourcePoint = "";
    String link = "";
    String accessToken = MainApp.currentUser.getAccess_token();

    public RequestExecutor(String link, String ressourcePoint) {
        this.ressourcePoint = ressourcePoint;
        this.link = link;
    }

    public HttpResponse<String> MakeRequest() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder().header(
                "Authorization", "Bearer " + accessToken).uri(
                URI.create(this.ressourcePoint + link)).GET().build();

        //Client to get the Response
        HttpClient client = HttpClient.newBuilder().build();
        return client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}