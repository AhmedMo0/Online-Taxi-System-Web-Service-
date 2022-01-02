package io.drivers_app.my_app.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Person {

    private String userName;
    private String password;
    private String mobileNumber;
    private String email;
    private String strBirthDate;
    
    @JsonIgnore
    private LocalDateTime birthDate;

    public Person() {}
    
    public Person(String userName, String password, String mobileNumber)
    {
    	this.userName = userName;
    	this.password = password;
    	this.mobileNumber = mobileNumber;
    	
    }
    
    

	public void setStrBirthDate(String strBirthDate) {
		this.strBirthDate = strBirthDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		LocalDateTime dt = LocalDate.parse(strBirthDate, formatter).atStartOfDay();
		
		birthDate = dt;
	}

	public String getStrBirthDate() {
		return strBirthDate;
	}
	
	public LocalDateTime getBirthDate() {
		return birthDate;
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
