package advisor;

class getFeatured implements Stragety {

    @Override
    public void select(String genre) {
        System.out.println("---FEATURED---");
        System.out.println("Mellow Morning");
        System.out.println("Wake Up and Smell the Coffee");
        System.out.println("Monday Motivation");
        System.out.println("Songs to Sing in the Shower");
    }

    @Override
    public void doAuth() {}
}