import java.io.IOException;

class Context {
    Stragety strategy;
    public boolean isAuth;

    public void setStrategy(Stragety strategy) {
        this.strategy = strategy;
    }

    public void select(String genre, String ressourcePoint) throws IOException, InterruptedException {
        this.strategy.select(genre, ressourcePoint);
    }

    public void login() {
        this.isAuth = true;
        System.out.println("---SUCCESS---");
    }

    public void logout() {
        this.isAuth = false;
    }
}
