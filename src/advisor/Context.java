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
