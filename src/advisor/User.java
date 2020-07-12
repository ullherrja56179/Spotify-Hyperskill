package advisor;

public class User {
    String access_token;
    int id;
    static int count = 0;

    public User(String access_token) {
        this.access_token = access_token;
        count++;
        this.id = count;
        System.out.println("Adding user with id = " + id + " and token = " + this.access_token);
    }

    public int getId() {
        return id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public static int getCount() {
        return count;
    }
}
