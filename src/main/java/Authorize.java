import com.google.gson.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Authorize {

    public boolean isAuth = false;
    private final static String clientId = "1316195262f34d4b8aa63d3124190378";
    private final static String clientSecret = "96ff8d83a78d42929962aab9fbf3091f";
    private String code = "";
    private String access_token;
    private final String accessPoint;


    public Authorize(String accessPoint) {
        this.accessPoint = accessPoint;
    }

    /**
     * Do Authorization for Spotify App.
     * Therefore a Link which leads to Login has to be clicked
     * If code is received a auth_token is requested and parsed
     *
     * @throws IOException          = if something goes wrong
     * @throws InterruptedException = if something goes wrong
     */
    public void doAuth() throws InterruptedException, IOException {
        //Create a HttpServer
        HttpServer server = HttpServer.create();
        //Bind it to localhost:8080
        server.bind(new InetSocketAddress(8080), 0);
        server.createContext("/",
                exchange -> {
                    //String have to be in this expression it seems. Otherwise they don't seem to get set correctly
                    String noGood = "Not found authorization code. Try again.";
                    String authGood = "Got the code. Return back to your program.";
                    String query = exchange.getRequestURI().getQuery();
                    if (query == null) query = "";
                    String writeString = "";
                    //Choose which String to write on Server
                    if (query.contains("code")) {
                        code = query;
                        writeString = authGood;
                    } else {
                        writeString = noGood;
                    }
                    //Acutally write the String
                    exchange.sendResponseHeaders(200, writeString.length());
                    exchange.getResponseBody().write(writeString.getBytes());
                    exchange.getResponseBody().close();
                }
        );

        //Server is set up --> Start Server
        server.start();
        System.out.println("use this link to request the access code:");
        //Authorize Code with client_id and redirect_url
        System.out.println(accessPoint + "/authorize?client_id=" + clientId + "&redirect_uri=http://localhost:8080&response_type=code");
        System.out.println("waiting for code...");
        /*
        As long as no code is there just wait
         */
        while (code.equals("")) {
            Thread.sleep(10);
        }

        System.out.println("code received");

        //Stop Server as soon as Code is received
        server.stop(0);

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(accessPoint + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=authorization_code&code=" + code.split("=")[1] +
                        "&redirect_uri=http://localhost:8080&client_id=" + clientId + "&client_secret=" + clientSecret))
                .build();

        System.out.println("making http request for access_token...");
        HttpClient client = HttpClient.newBuilder().build();
        //Send the request
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.toString());

        //Get the access Token
        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        access_token = jsonResponse.get("access_token").getAsString();
//        System.out.println(access_token);
    }

    public String getAccess_token() {
        return access_token;
    }

    public boolean isAuth() {
        return isAuth;
    }
}
