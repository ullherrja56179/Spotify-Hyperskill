import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

class getFeatured implements Stragety {

    private final String link = "/v1/browse/featured-playlists";
    private final String accessToken = MainApp.currentUser.getAccess_token();

    @Override
    public void select(String genre, String ressourcePoint) throws IOException, InterruptedException {

        //Build Request with link and
        HttpRequest httpRequest = HttpRequest.newBuilder().header(
                "Authorization", "Bearer " + accessToken).uri(
                URI.create(ressourcePoint + link)).GET().build();

        //Client to get the Response
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        //Code must be 200 if OK
        if( response.statusCode() != 200) {
            JsonObject resp = JsonParser.parseString(response.body()).getAsJsonObject();
            System.out.println(resp.get("error").getAsJsonObject().get("message").toString().replace("\"",""));
            return;
        }

        //Parse response
        JsonObject resp = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject playlists = resp.get("playlists").getAsJsonObject();

        Map<String, String> informations = new TreeMap<>();

        //Fill Map with names and links
        for(JsonElement name : playlists.get("items").getAsJsonArray()) {
            JsonObject tmp = name.getAsJsonObject();
            informations.put(tmp.get("name").toString().replace("\"",""), tmp.get("external_urls").getAsJsonObject().get("spotify").toString().replace("\"",""));
        }

        printInformations(informations);
    }

    private void printInformations(Map<String, String> map) {
        map.forEach((String name, String href) -> System.out.printf(
                "%s\n%s\n\n", name, href
        ));
    }
}