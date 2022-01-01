package io.drivers_app.my_app;

import javax.persistence.EntityManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.drivers_app.my_app.domain.Trip;



@SpringBootApplication
public class MyAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    
        
    }
    
    /*
    @Bean
    public CommandLineRunner mappingDemo(UserRepository userRepository,
    									TripRepository tripRepository)
    {
    	return args -> {
    		// test
    		
            User user = new User("ahmed", "a@g", "123p", "01111");
            Trip trip1 = new Trip("A", "B");
            Trip trip2 = new Trip("C", "D");
            trip1.setUser1(user);
            trip2.setUser1(user);
            
            
            user.userOperations.addTrip(trip1);
            user.userOperations.addTrip(trip2);
            System.out.println("/////>>>>>> "+ user.getUserTrips());
            
            userRepository.save(user);
            
            user.getUserTrips().remove(0);
            System.out.println("/////>>>>>> "+ user.getUserTrips());
            
    	};
    }
    */
    

}

/*
 * for api testing 
 * http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/
 * 
 * to connect h2database
 * http://localhost:8080/h2-console/login.do?jsessionid=a09becf75a7a38dd7859f86add493bea
 */