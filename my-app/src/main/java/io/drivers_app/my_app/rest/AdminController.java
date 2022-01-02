package io.drivers_app.my_app.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.drivers_app.my_app.domain.Admin;
import io.drivers_app.my_app.domain.Data;
import io.drivers_app.my_app.domain.Driver;
import io.drivers_app.my_app.domain.Event;
import io.drivers_app.my_app.domain.Person;

@RestController
@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private Admin curr = null;


    
    @GetMapping("/{username}")
    public ResponseEntity<String> getUser(@PathVariable final String username) {
    	curr = (Admin)Data.getInstance().dataOperation.getCurrentUser();
    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Access Denied or Already Logged Out");
    	}
    	
        return ResponseEntity.ok(curr.toString());
    }
    
    @GetMapping("/{username}/myData")
    public ResponseEntity<Admin> getAdminData(@PathVariable final String username) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(curr, HttpStatus.CREATED);
    }
    
    

    @GetMapping("/{username}/showPendingList")
    public ResponseEntity<List<Driver>> showPendingList(@PathVariable final String username) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(Data.getInstance().pendingDrivers, HttpStatus.OK);
    }
    
    @PostMapping("/{username}/verifyDriver/indexNum/{num}")
    public ResponseEntity<String> verifyDriver(@PathVariable final String username,
    										@PathVariable final int num) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	curr.verify(num);
    	return new ResponseEntity<>("Driver Verified", HttpStatus.OK);
    }
    
    @GetMapping("/{username}/showSuspendedList")
    public ResponseEntity<List<Person> > showSuspendedList(@PathVariable final String username) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(Data.getInstance().suspended, HttpStatus.OK);
    }
    
    @PostMapping("/{username}/suspendUser/{suspendedUsername}")
    public ResponseEntity<String> suspendUser(@PathVariable final String username,
    										@PathVariable final String suspendedUsername) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	Person tmp = Data.getInstance().dataOperation.getUserByName(suspendedUsername);
    	curr.suspend(tmp);
    	
    	return new ResponseEntity<>("Driver Verified", HttpStatus.OK);
    }
    
    
    @GetMapping("/{username}/showAreasWithDiscount")
    public ResponseEntity<List<String> > showAreasWithDiscount(@PathVariable final String username) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(Data.getInstance().destAreaWithDiscount, HttpStatus.OK);
    }
    
    @PostMapping("/{username}/addAreasWithDiscount")
    public ResponseEntity<?> addAreasWithDiscount(@PathVariable final String username,
    								@RequestBody @Valid final ArrayList<String> areasList) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	ArrayList<String> tmp = new ArrayList<>(areasList);
    	return new ResponseEntity<>(Data.getInstance().destAreaWithDiscount = tmp, HttpStatus.CREATED);
    }
    
    
    @GetMapping("/{username}/showAllEvents")
    public ResponseEntity<List<Event> > showAllEvents(@PathVariable final String username) {    	
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(Data.getInstance().eventList, HttpStatus.OK);
    }
    
    @GetMapping("/{username}/logout")
    public ResponseEntity<?> logout(@PathVariable final String username) {
    	curr = null;
    	
        return ResponseEntity.ok("Admin logged out");
    }
    
    

}

