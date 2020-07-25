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

        System.out.printf("Using:\n1. %s\n2. %s\n", accessPoint, resourcePoint);

        while (true) {
            String in = scanner.nextLine();
            String[] input = in.split("\\s+", 2);
            switch (input[0].toLowerCase()) {
                case "new": {
                    ctx.setStrategy(new getNew());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.select("None", resourcePoint);
                    }
                    break;
                }
                case "featured": {
                    ctx.setStrategy(new getFeatured());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.select("None", resourcePoint);
                    }
                    break;
                }
                case "categories": {
                    ctx.setStrategy(new getCategories());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.select("None", resourcePoint);
                    }
                    break;
                }
                case "playlists": {
                    ctx.setStrategy(new getPlaylists());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application.");
                    } else {
                        ctx.select(input[1], resourcePoint);
                    }
                    break;
                }
                case "auth": {
                    if(ctx.isAuth) {
                        System.out.println("Already LoggedIn");
                    } else {
                        Authorize auth = new Authorize(accessPoint);
                        auth.doAuth();
                        currentUser = new User(auth.getAccess_token());
                        ctx.login();
                    }
                    break;
                }
                case "exit": {
                    System.out.println("---GOODBYE!---");
                    System.exit(0);
                    ctx.logout();
                    break;
                }
                case "logout": {
                    ctx.logout();
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
