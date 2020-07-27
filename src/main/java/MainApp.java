import java.io.IOException;
import java.util.Scanner;

public class MainApp {

//    public static List<User> usersList = new ArrayList<>();
    public static User currentUser;

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
                //just create any user
                currentUser = new User("123");
            }
        } else {
            accessPoint = "https://accounts.spotify.com";
            resourcePoint = "https://api.spotify.com";
        }
        Authorize auth = new Authorize(accessPoint);

        while (true) {
            String in = scanner.nextLine();
            String[] input = in.split("\\s+", 2);
            switch (input[0].toLowerCase()) {
                case "new": {
                    if (!auth.isAuth()) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.setStrategy(new getNew());
                        ctx.select("None", resourcePoint);
                    }
                    break;
                }
                case "featured": {
                    if (!auth.isAuth) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.setStrategy(new getFeatured());
                        ctx.select("None", resourcePoint);
                    }
                    break;
                }
                case "categories": {
                    if (!auth.isAuth) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.setStrategy(new getCategories());
                        ctx.select("None", resourcePoint);
                    }
                    break;
                }
                case "playlists": {
                    if (!auth.isAuth) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.setStrategy(new getPlaylists());
                        ctx.select(input[1], resourcePoint);
                    }
                    break;
                }
                case "auth": {
                    if(auth.isAuth) {
                        System.out.println("Already LoggedIn");
                    } else {
                        auth.doAuth();
                        currentUser = new User(auth.getAccess_token());
                        auth.isAuth = true;
                    }
                    break;
                }
                case "exit": {
                    System.out.println("---GOODBYE!---");
                    System.exit(0);
                    auth.isAuth = false;
                    break;
                }
                case "logout": {
                    auth.isAuth = false;
                    currentUser = null;
                    System.out.println("---YOU ARE NOW LOGGED OUT!---");
                    break;
                }
                default: {
                    System.out.println("Entered Invalid Phrase");
                }
            }
        }
    }
}
