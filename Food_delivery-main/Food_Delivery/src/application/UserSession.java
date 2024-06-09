package application;

public class UserSession {
    private static UserSession instance;
    private String username;
    private String email;
    private String address;
    private String phone_no;


    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getphone_no() {
        return phone_no;
    }

    public void setphone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}
