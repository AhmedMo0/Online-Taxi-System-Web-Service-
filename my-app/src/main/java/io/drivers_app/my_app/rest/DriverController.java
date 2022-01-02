package io.drivers_app.my_app.rest;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.drivers_app.my_app.domain.Data;
import io.drivers_app.my_app.domain.Driver;

@RestController
@RequestMapping(value = "/api/driver", produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverController {
 	private Driver curr = null;


 	@GetMapping("/{username}")
    public ResponseEntity<String> getUser(@PathVariable final String username) {
    	curr = (Driver)Data.getInstance().dataOperation.getCurrentUser();
    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Access Denied or Already Logged Out");
    	}
    	
        return ResponseEntity.ok("Hi " + username + "you'r logged in");
    }
 
 	@PostMapping("/{username}/addFavoriteAreas")
    public ResponseEntity<?> addAreasWithDiscount(@PathVariable final String username,
    								@RequestBody @Valid final ArrayList<String> favList) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	ArrayList<String> tmp = new ArrayList<>(favList);
    	return new ResponseEntity<>(curr.favArea = tmp, HttpStatus.CREATED);
    }
 
 	@GetMapping("/{username}/showAvailablerides")
    public ResponseEntity<?> showAvailablerides(@PathVariable final String username) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(curr.availableTrips, HttpStatus.CREATED);
    }
 
 	@PostMapping("/{username}/sendOffer") // /{username}/sendOffer?tripNum=2
    public ResponseEntity<?> sendOffer(@PathVariable final String username,
    									@RequestParam(defaultValue = "-1") int tripNum, 
    									@RequestParam(defaultValue = "-1") double price) {
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	if(tripNum == Integer.parseInt("-1") || price == Integer.parseInt("-1"))
    	{
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "trip with index " +
    											tripNum + " not found");
    	}
    	if(price <= 0)
    	{
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid values for price");
    	}
    	
    	try {
    		
    		curr.driverOperations.sendOffer(curr.availableTrips.get(tripNum), price);
    		
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "trip not found");
		}
  
    	
    	return new ResponseEntity<>("offer has been sent for trip with index"+tripNum, HttpStatus.OK);
    }
 
 	
 	@GetMapping("/{username}/showRidesHistory")
    public ResponseEntity<?> showRidesHistory(@PathVariable final String username) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(curr.historyList, HttpStatus.CREATED);
    }
 	
 	

    @GetMapping("/{username}/myData")
    public ResponseEntity<?> getAdminData(@PathVariable final String username) {    	
        //return ResponseEntity.ok(curr);
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(curr, HttpStatus.OK);
    }
    
    
   
    
    
    @GetMapping("/{username}/logout")
    public ResponseEntity<?> logout(@PathVariable final String username) {
    	curr = null;
    	
        return ResponseEntity.ok("user logged out");
    }
	    

	   

}
