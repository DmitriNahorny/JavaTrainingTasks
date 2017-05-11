package by.nahorny.mvc.entity;

/**
 * Created by Dmitri_Nahorny on 4/12/2017.
 */
public class User extends BusinessEntity {
    private int uniqueId;
    private String login;
    private String password;
    private String email;
    private String role;
    private float currentBalance;
    private int discount;

    public User(int uniqueId, String login, String password, String role, String email, float currentBalance, int discount) {
        this.uniqueId = uniqueId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.currentBalance = currentBalance;
        this.discount = discount;
    }

    public User(String login, String password, String role, String email, float currentBalance, int discount) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.currentBalance = currentBalance;
        this.discount = discount;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
