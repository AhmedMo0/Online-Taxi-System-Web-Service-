package io.drivers_app.my_app.domain;

import org.springframework.stereotype.Component;

@Component
public class Admin extends Person {

	public Admin() {}
	
	public Admin(String userName, String pass, String mobile)
	{
		super(userName, pass, mobile);
	}
	
    public void addAdmin(Admin admin) {
        Data.userList.add(admin);
    }

    public void verify(int indx)
    {
    		Driver driver = Data.pendingDrivers.get(indx);
    		Data.getInstance().addDriver(driver);
    	
            Data.pendingDrivers.remove(indx);
            Data.userList.add(driver);
    }

    public void suspend(Person p)
    {
    	boolean found = false;
    	
        for(int i=0 ; i<Data.userList.size() ; i++)
        {
            if(Data.userList.get(i)==p)
            {
                Data.userList.remove(i);
                found = true;
                break;
            }
        }
        
        if(!found)
        {
        	System.out.println("user not founded");
        	
        	return;
        }
        
        Data.suspended.add(p);
        

        
    }

    public void activate(Person p)
    {
        for(int i=0 ; i<Data.suspended.size() ; i++){
            if(Data.suspended.get(i)==p)
            {
                Data.suspended.remove(i);
                Data.userList.add(p);
                return;
            }
        }
        System.out.println("Not suspended.");

    }
    
    public void showPendingDrivers()
    {
    	Data database = Data.getInstance();
    	Driver tmp;
    	System.out.println("Pending Drivers");
		for(int i=0; i<database.pendingDrivers.size(); i++)
		{
			tmp = database.pendingDrivers.get(i);
			System.out.print((i+1) + "- ");
			System.out.println(tmp.toString() );
		}
    }
    
    public void showSuspendedUsers()
    {
    	Data database = Data.getInstance();
    	Person tmp;
    	System.out.println("Suspended users");
    	
    	if(database.suspended.size() != 0)
    	{
			for(int i=0; i<database.suspended.size(); i++)
			{
				tmp = database.suspended.get(i);
				System.out.print((i+1) + "- ");
				System.out.println(tmp.toString() );
			}
    	}
    	else
    	{
    		System.out.println("Suspended List is Empty");
    	}
    }

}