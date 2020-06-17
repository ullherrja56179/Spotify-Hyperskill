package advisor;

class Context {
    Stragety strategy;
    public boolean isAuth;

    public void setStrategy(Stragety strategy) {
        this.strategy = strategy;
    }

    public void select(String genre) {
        this.strategy.select(genre);
    }
    public void doAuth() {
        this.isAuth = true;
        System.out.println("https://accounts.spotify.com/authorize?client_id=1316195262f34d4b8aa63d3124190378&redirect_uri=https://www.example.com&response_type=code");
        System.out.println("---SUCCESS---");
    }
}
