package io.drivers_app.my_app.rest;


import java.util.List;
import javax.validation.Valid;

import org.apache.tomcat.jni.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.drivers_app.my_app.domain.Data;
import io.drivers_app.my_app.domain.Driver;
import io.drivers_app.my_app.domain.NormalUser;


@RestController
@RequestMapping(value = "/api/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    //private final UserService userService;

    // return all Normal users 
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<NormalUser> > getAllUsers() {
        return ResponseEntity.ok(Data.getInstance().dataOperation.getUsers());
    }


    //create new user
    @PostMapping("/newUser")
    public ResponseEntity<Long> createUser(@RequestBody @Valid final NormalUser user) {
    	
        return new ResponseEntity<>(Data.getInstance().dataOperation.addUser(user), HttpStatus.CREATED);
    }
    
    @PostMapping("/newDriver")
    public ResponseEntity<Long> createDriver(@RequestBody @Valid final Driver driver) {
    	
        return new ResponseEntity<>(Data.getInstance().dataOperation.addUser(driver), HttpStatus.CREATED);
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
