package advisor;

class getPlaylists implements Stragety {

    @Override
    public void select(String genre) {
        System.out.println("---" + genre.toUpperCase() + " PLAYLISTS---");
        System.out.println("Walk Like a Badass");
        System.out.println("Rage Beats");
        System.out.println("Arab Mood Booster");
        System.out.println("Sunday Stroll");
    }

    @Override
    public void doAuth() {}
}
