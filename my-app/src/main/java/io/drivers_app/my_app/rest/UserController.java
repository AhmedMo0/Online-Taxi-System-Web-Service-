package io.drivers_app.my_app.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.drivers_app.my_app.domain.Data;
import io.drivers_app.my_app.domain.NormalUser;


@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	 private NormalUser curr = null;

	    // return all Normal users 
		/*
	    @GetMapping("/getPendingDrivers")
	    public ResponseEntity<List<Driver> > getAllUsers() {
	        return ResponseEntity.ok(Data.getInstance().dataOperation.getUsers());
	    }
	    */
	 
	 @GetMapping("/{username}")
	    public ResponseEntity<String> getUser(@PathVariable final String username) {
	    	curr = (NormalUser)Data.getInstance().dataOperation.getCurrentUser();
	    	
	    	if(curr == null)
	    	{
	    		throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Access Denied or Already Logged Out");
	    	}
	    	
	        return ResponseEntity.ok("Hi " + username + "you'r logged in");
	    }

	    @GetMapping("/{username}/myData")
	    public ResponseEntity<NormalUser> getAdminData(@PathVariable final String username) {    	
	        //return ResponseEntity.ok(curr);
	    	if(curr == null)
	    	{
	    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
	    	}
	    	
	    	return new ResponseEntity<>(curr, HttpStatus.CREATED);
	    }
	    
	    @GetMapping("/{username}/logout")
	    public ResponseEntity<NormalUser> logout(@PathVariable final String username) {
	    	curr = null;
	    	
	        return ResponseEntity.ok(curr);
	    }
	    

	   

}
