package advisor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Context ctx = new Context();

        while (true) {
            String in = scanner.nextLine();
            String[] input = in.split("\\s+");
            switch (input[0].toLowerCase()) {
                case "new": {
                    ctx.setStrategy(new getNew());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application");
                    } else {
                        ctx.select("None");
                    }
                    break;
                }
                case "featured": {
                    ctx.setStrategy(new getFeatured());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application");
                    } else {
                        ctx.select("None");
                    }
                    break;
                }
                case "categories": {
                    ctx.setStrategy(new getCategories());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application");
                    } else {
                        ctx.select("None");
                    }
                    break;
                }
                case "playlists": {
                    ctx.setStrategy(new getPlaylists());
                    if (!ctx.isAuth) {
                        System.out.println("Please, provide access for application");
                    } else {
                        ctx.select(input[1]);
                    }
                    break;
                }
                case "auth": {
                    ctx.doAuth();
                    break;
                }
                case "exit": {
                    System.out.println("---GOODBYE!---");
                    System.exit(1);
                    break;
                }
                default: {
                    System.out.println("Entered Invalid Phrase");
                }
            }
        }
    }

    public boolean checkIfAuth(Context ctx) {
        return ctx.isAuth;
    }
}
