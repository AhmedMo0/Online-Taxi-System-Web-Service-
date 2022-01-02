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
import org.springframework.web.servlet.view.RedirectView;

import io.drivers_app.my_app.domain.Data;
import io.drivers_app.my_app.domain.Driver;
import io.drivers_app.my_app.domain.NormalUser;
import io.drivers_app.my_app.domain.loginDTO;


@RestController
@RequestMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    //private final UserService userService;

    // return all Normal users 
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<NormalUser> > getAllUsers() {
        return ResponseEntity.ok(Data.getInstance().dataOperation.getUsers());
    }


    //create new user
    @PostMapping("/newUser")
    public RedirectView createUser(@RequestBody @Valid final NormalUser user) {
    	Data.getInstance().dataOperation.addUser(user);
    	
    	loginDTO tmp = new loginDTO();
    	tmp.setUsername(user.getUsername() );
    	tmp.setPassword(user.getPassword() );
    	
    	login(tmp);
    	
    	return login(tmp);
        //return new ResponseEntity<>(Data.getInstance().dataOperation.addUser(user), HttpStatus.CREATED);
    }
    
    @PostMapping("/newDriver")
    public ResponseEntity<Long> createDriver(@RequestBody @Valid final Driver driver) {
    	
        return new ResponseEntity<>(Data.getInstance().dataOperation.addUser(driver), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public RedirectView login(@RequestBody @Valid final loginDTO user) {
    	int type = Data.getInstance().dataOperation.findUser(user.getUsername(), user.getPassword());
    	if(type == 3)
    	{
    		return new RedirectView("/api/admin/"+user.getUsername());
    	}
    	else if(type == 1)
    	{
    		System.out.println("you'r IN<<<<<<>>>>>>");
    		return new RedirectView("/api/user/"+user.getUsername());
    	}
        return new RedirectView("/api/auth/login");
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
