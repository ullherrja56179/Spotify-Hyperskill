package advisor;

class Context {
    Stragety strategy;

    public void setStrategy(Stragety strategy) {
        this.strategy = strategy;
    }

    public void select(String genre) {
        this.strategy.select(genre);
    }
}

interface Stragety {
    void select(String genre);
}

class getFeatured implements Stragety {

    @Override
    public void select(String genre) {
        System.out.println("---FEATURED---");
        System.out.println("Mellow Morning");
        System.out.println("Wake Up and Smell the Coffee");
        System.out.println("Monday Motivation");
        System.out.println("Songs to Sing in the Shower");
    }
}

class getNew implements Stragety {

    @Override
    public void select(String genre) {
        System.out.println("---NEW RELEASES---");
        System.out.println("Mountains [Sia, Diplo, Labrinth]");
        System.out.println("Runaway [Lil Peep]");
        System.out.println("The Greatest Show [Panic! At The Disco]");
        System.out.println("All Out Life [Slipknow]");
    }
}

class getCategories implements Stragety {

    @Override
    public void select(String genre) {
        System.out.println("---CATEGORIES---");
        System.out.println("Top Lists");
        System.out.println("Pop");
        System.out.println("Mood");
        System.out.println("Latin");
    }
}

class getPlaylists implements Stragety {

    @Override
    public void select(String genre) {
        System.out.println("---" + genre.toUpperCase() + " PLAYLISTS---");
        System.out.println("Walk Like a Badass");
        System.out.println("Rage Beats");
        System.out.println("Arab Mood Booster");
        System.out.println("Sunday Stroll");
    }
}
