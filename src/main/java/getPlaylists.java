import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.TreeMap;

class getPlaylists implements Stragety {

    private final String accessToken = MainApp.currentUser.getAccess_token();
    private final Map<String, String> informations = new TreeMap<>();


    @Override
    public void select(String genre, String ressourcePoint) throws IOException, InterruptedException {

        getCategories categories = new getCategories();
        categories.select(genre, ressourcePoint);
        TreeMap<String, String> catIds = getCategories.getIds();

        String id = catIds.get(genre);

        System.out.println(id);

        if(id == null) {
            System.out.println("Unknown category name.");
            return;
        }

        String link = "/v1/browse/categories/" + id + "/playlists";
        HttpResponse<String> response = new RequestExecutor(link, ressourcePoint).MakeRequest();
//        System.out.println(response.statusCode());

        JsonObject resp;
        resp = JsonParser.parseString(response.body()).getAsJsonObject();
        if (response.statusCode() != 200) {
            printError(response);
            return;
        }

        JsonObject playlists;
        try {
            playlists = resp.get("playlists").getAsJsonObject();
        } catch (Exception e) {
            printError(response);
            return;
        }
        //Fill Map with names and links
        for (JsonElement name : playlists.get("items").getAsJsonArray()) {
            JsonObject tmp = name.getAsJsonObject();
            informations.put(tmp.get("name").toString().replace("\"", "")
                    , tmp.get("external_urls").getAsJsonObject().get("spotify").toString().replace("\"", ""));
        }

        printInformations();
    }

    private void printInformations() {
        informations.forEach((String name, String href) -> System.out.printf(
                "%s\n%s\n\n", name, href
        ));
    }

    private void printError(HttpResponse<String> response) {
        JsonObject resp = JsonParser.parseString(response.body()).getAsJsonObject();
        System.out.println(resp.get("error").getAsJsonObject().get("message").toString().replace("\"", ""));
    }
}
