package sample;

public class User {
    private String firstname;
    private String lastname;
    private String cardnumber;
    private String password;
    private String money;

    public User(String firstname, String lastname, String cardnumber, String password, String money) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.cardnumber = cardnumber;
        this.password = password;
        this.money = money;
    }

    public User() {}

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
/*
    public void copy(User user) {
        this.cardnumber= user.getCardnumber();
        this.password = user.getPassword();
        this.money = user.getMoney();
    }*/
}
