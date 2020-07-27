import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.TreeMap;

class getCategories implements Stragety {

    private final String accessToken = MainApp.currentUser.getAccess_token();
    private final static ArrayList<String> categories = new ArrayList<>();
    private final static TreeMap<String, String> ids = new TreeMap<>();

    @Override
    public void select(String genre, String ressourcePoint) throws IOException, InterruptedException {

        String link = "/v1/browse/categories?limit=10";
        HttpResponse<String> response = new RequestExecutor(link, ressourcePoint).MakeRequest();

        //Code must be 200 if OK
        if( response.statusCode() != 200) {
            JsonObject resp = JsonParser.parseString(response.body()).getAsJsonObject();
            System.out.println(resp.get("error").getAsJsonObject().get("message").toString().replace("\"",""));
            return;
        }

        //Parse response
        JsonObject resp = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonArray helper = resp.get("categories").getAsJsonObject().get("items").getAsJsonArray();

        for(JsonElement categorie : helper) {
            categories.add(categorie.getAsJsonObject().get("name").toString().replace("\"",""));
            ids.put(categorie.getAsJsonObject().get("name").toString().replace("\"",""), categorie.getAsJsonObject().get("id").toString().replace("\"", ""));
        }

        //this should only be done when called through "Categories"
        printCategories();
    }

    private void printCategories() {
        categories.forEach(System.out::println);
    }

    public static TreeMap<String, String> getIds() {
        return ids;
    }
}
