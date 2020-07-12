package advisor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<User> usersList = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Context ctx = new Context();
        String accessPoint = "";
        String resourcePoint = "";

        if(args.length > 0) {
            if("-access".equals(args[0])) {
                accessPoint = args[1];
            }
            if("-resource".equals(args[2])) {
                resourcePoint = args[3];
            }
        } else {
            accessPoint = "https://accounts.spotify.com";
            resourcePoint = "https://api.spotify.com";
        }

        while (true) {
            String in = scanner.nextLine();
            String[] input = in.split("\\s+");
            switch (input[0].toLowerCase()) {
                case "new": {
                    ctx.setStrategy(new getNew());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.select("None");
                    }
                    break;
                }
                case "featured": {
                    ctx.setStrategy(new getFeatured());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.select("None");
                    }
                    break;
                }
                case "categories": {
                    ctx.setStrategy(new getCategories());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.select("None");
                    }
                    break;
                }
                case "playlists": {
                    ctx.setStrategy(new getPlaylists());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.select(input[1]);
                    }
                    break;
                }
                case "auth": {
                    if(ctx.isAuth) {
                        System.out.println("Already LoggedIn");
                    } else {
                        Authorize auth = new Authorize(accessPoint);
                        auth.doAuth();
                        usersList.add(new User(auth.getAccess_token()));
                        ctx.login();
                    }
                    break;
                }
                case "exit": {
                    System.out.println("---GOODBYE!---");
                    System.exit(1);
                    ctx.logout();
                    break;
                }
                case "logout": {
                    ctx.logout();
                    System.out.println("---YOU ARE NOW LOGGED OUT!---");
                    break;
                }
                case "show": {
                    for(User user : usersList) {
                        System.out.println("id = " + user.getId() + " token = " + user.getAccess_token());
                    }
                    break;
                }
                default: {
                    System.out.println("Entered Invalid Phrase");
                }
            }
        }
    }
}
