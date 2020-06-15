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
                    ctx.select("None");
                    break;
                }
                case "featured": {
                    ctx.setStrategy(new getFeatured());
                    ctx.select("None");
                    break;
                }
                case "categories": {
                    ctx.setStrategy(new getCategories());
                    ctx.select("None");
                    break;
                }
                case "playlists": {
                    ctx.setStrategy(new getPlaylists());
                    ctx.select(input[1]);
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
}
