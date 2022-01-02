package io.drivers_app.my_app.domain;


import java.util.Date;

public class Event {


    private String name;
    private Date time;
    private String capName;
    private String userName;
    private double tripPrice;
    private int number;


    public Event(String name  , String capName , double tripPrice)
    {
        this.name=name;
        this.capName=capName;
        this.tripPrice=tripPrice;
        number=1;
        this.time=new Date();
    }
    public Event(String name  , String userName )
    {
        this.name=name;
        this.userName=userName;
        number=2;
        this.time=new Date();
    }
    public Event(String name  , String capName ,String userName )
    {
        this.name=name;
        this.capName=capName;
        this.userName=userName;
        number=3;
        this.time=new Date();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    public String getCapName() {
        return capName;
    }

    public void setCapName(String capName) {
        this.capName = capName;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getTripPrice() {
        return tripPrice;
    }

    public void setTripPrice(double tripPrice) {
        this.tripPrice = tripPrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    public void printEvent()
    {
        System.out.println(this.getName());
        System.out.println(this.getTime().toString());
        if(number==1)
        {
            System.out.println("Captain name: "+this.getCapName());
            System.out.println("Trip price: "+this.getTripPrice());
        }
        else if(number==2)
        {
            System.out.println("User name: "+this.getUserName());
        }
        else if(number==3)
        {
            System.out.println("Captain name: "+this.getCapName());
            System.out.println("User name: "+this.getUserName());
        }

    }
}