package io.drivers_app.my_app.domain;

public abstract class Person {

    private String userName;
    private String password;
    private String mobileNumber;
    private String email;

    public Person() {}
    
    public Person(String userName, String password, String mobileNumber)
    {
    	this.userName = userName;
    	this.password = password;
    	this.mobileNumber = mobileNumber;
    }
    
    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
