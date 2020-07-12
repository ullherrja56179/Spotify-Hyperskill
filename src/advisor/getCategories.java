package advisor;

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
