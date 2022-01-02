package io.drivers_app.my_app.rest;


import java.util.List;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


    @GetMapping("/getNormalUsers")
    public ResponseEntity<List<NormalUser> > getNormalUsers() {
        return ResponseEntity.ok(Data.getInstance().dataOperation.getUsers());
    }
    
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(Data.getInstance().userList);
    }


    @PostMapping("/register/newUser")
    public RedirectView createUser(@RequestBody @Valid final NormalUser user) {
    	Data.getInstance().dataOperation.addUser(user);
    	
    	loginDTO tmp = new loginDTO();
    	tmp.setUsername(user.getUsername() );
    	tmp.setPassword(user.getPassword() );
    	
    	login(tmp);
    	
    	return login(tmp);
    }
    
    @PostMapping("/register/newDriver")
    public ResponseEntity<Long> createDriver(@RequestBody @Valid final Driver driver) {
    	
        return new ResponseEntity<>(Data.getInstance().dataOperation.addUser(driver), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public RedirectView login(@RequestBody @Valid final loginDTO user) {
    	int type = Data.getInstance().dataOperation.findUser(user.getUsername(), user.getPassword());
    	
    	if(type == 1)
    	{
    		return new RedirectView("/api/user/"+user.getUsername());
    	}
    	else if(type == 2)
    	{
    		return new RedirectView("/api/driver/"+user.getUsername());
    	}
    	else if(type == 3)
    	{
    		return new RedirectView("/api/admin/"+user.getUsername());
    	}
    	
        return new RedirectView("/api/auth/login");
    }
    


}
