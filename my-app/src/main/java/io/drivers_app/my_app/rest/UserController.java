package io.drivers_app.my_app.rest;


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
import io.drivers_app.my_app.domain.NormalUser;
import io.drivers_app.my_app.domain.TripRequestDTO;


@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	 private NormalUser curr = null;


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
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(curr, HttpStatus.CREATED);
    }
    
    
    @PostMapping("/{username}/requestNewTrip")
    public ResponseEntity<?> addAreasWithDiscount(@PathVariable final String username,
    								@RequestBody @Valid final TripRequestDTO tripDTO) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	curr.userOperation.requestTrip(tripDTO.getSource(), tripDTO.getDest(), 
    									tripDTO.getOtherPassengers() );
    	
    	
    	return new ResponseEntity<>("new trip request added", HttpStatus.CREATED);
    }
    
    @GetMapping("/{username}/showAvailableOffers")
    public ResponseEntity<?> showAvailableOffers(@PathVariable final String username) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(curr.offersList, HttpStatus.CREATED);
    }
    
    @PostMapping("/{username}/acceptOffer/offerNum/{offerNum}")
    public ResponseEntity<?> acceptOffer(@PathVariable final String username,
    								@RequestBody @Valid final int offerNum) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	try {
    		curr.userOperation.acceptOffer(curr.offersList.get(offerNum) );
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "offer with index "+ offerNum 
												+ "not found");
		}
    	
    	
    	return new ResponseEntity<>("offer with index " + offerNum + " accepted", HttpStatus.OK);
    }
    
    @PostMapping("/{username}/finishTrip") // /{username}/finishTrip?rate=2
    public ResponseEntity<?> finishTrip(@PathVariable final String username,
    									@RequestParam(defaultValue = "-1") int rate) {
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	if(rate == Integer.parseInt("-1") )
    	{
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "enter rate for this trip before finishing it");
    	}
    	
    	curr.currTrip.setRate(rate);
    	curr.userOperation.finishTrip();
    	
    	return new ResponseEntity<>("current trips is finished " , HttpStatus.OK);
    }
    
    
    @GetMapping("/{username}/logout")
    public ResponseEntity<?> logout(@PathVariable final String username) {
    	curr = null;
    	
        return ResponseEntity.ok("user logged out");
    }
	    

	   

}
