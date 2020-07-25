import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

class getNew implements Stragety {

    @Override
    public void select(String genre, String ressourcePoint) throws IOException, InterruptedException {

        //Build Request with link and
        String link = "/v1/browse/new-releases";

        HttpResponse<String> response = new RequestExecutor(link, ressourcePoint).MakeRequest();
        //Code must be 200 if OK
        if( response.statusCode() != 200) {
            JsonObject resp = JsonParser.parseString(response.body()).getAsJsonObject();
            System.out.println(resp.get("error").getAsJsonObject().get("message").toString().replace("\"",""));
            return;
        }

        //Parse response
        JsonObject resp = JsonParser.parseString(response.body()).getAsJsonObject();

        JsonObject albums = resp.get("albums").getAsJsonObject();

        List<String> madeBy = new ArrayList<>();

        //Print everything -> Artists get stored in an JsonArray and the Names in an Seperat Array
        //This array gets cleared for every new item
        for(JsonElement album : albums.get("items").getAsJsonArray()) {
            JsonArray artists = album.getAsJsonObject().get("artists").getAsJsonArray();
            System.out.println(album.getAsJsonObject().get("name").toString().replace("\"", ""));
            for (JsonElement artist : artists) {
                madeBy.add(artist.getAsJsonObject().get("name").toString().replace("\"", ""));
            }
            System.out.println(madeBy.toString().replace("\"",""));
            System.out.println(album.getAsJsonObject().get("external_urls").getAsJsonObject().get("spotify").toString().replace("\"", ""));
            System.out.println();
            madeBy.clear();
        }
    }
}
