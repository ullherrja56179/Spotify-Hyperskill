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

    public void login() {
        this.isAuth = true;
        System.out.println("---SUCCESS---");
    }

    public void logout() {
        this.isAuth = false;
    }
}
