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
import io.drivers_app.my_app.domain.NormalUser;
import io.drivers_app.my_app.domain.Person;
import io.drivers_app.my_app.domain.loginDTO;

@RestController
@RequestMapping(value = "/api/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private Admin curr = null;

    // return all Normal users 
	/*
    @GetMapping("/getPendingDrivers")
    public ResponseEntity<List<Driver> > getAllUsers() {
        return ResponseEntity.ok(Data.getInstance().dataOperation.getUsers());
    }
    */

    
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
        //return ResponseEntity.ok(curr);
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(curr, HttpStatus.CREATED);
    }
    
    @GetMapping("/{username}/logout")
    public ResponseEntity<Admin> logout(@PathVariable final String username) {
    	curr = null;
    	
        return ResponseEntity.ok(curr);
    }
    

    @GetMapping("/{username}/showPendingList")
    public ResponseEntity<List<Driver>> showPendingList(@PathVariable final String username) {    	
        //return ResponseEntity.ok(curr);
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(Data.getInstance().pendingDrivers, HttpStatus.OK);
    }
    
    @GetMapping("/{username}/suspendedUsersList")
    public ResponseEntity<List<Person> > suspendedUsersList(@PathVariable final String username) {    	
        //return ResponseEntity.ok(curr);
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(Data.getInstance().suspended, HttpStatus.OK);
    }
    
    @PostMapping("/{username}/addAreasWithDiscount")
    public ResponseEntity<?> addAreasWithDiscount(@PathVariable final String username,
    								@RequestBody @Valid final ArrayList<String> areasList) {    	
        //return ResponseEntity.ok(curr);
    	if(curr == null)
    	{
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Access Denied or Already Logged Out");
    	}
    	
    	return new ResponseEntity<>(Data.getInstance().destAreaWithDiscount = areasList, HttpStatus.CREATED);
    }
    
    
    
    

    /*
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable final Long id,
            @RequestBody @Valid final UserDTO userDTO) {
        userService.update(id, userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
	*/

}

