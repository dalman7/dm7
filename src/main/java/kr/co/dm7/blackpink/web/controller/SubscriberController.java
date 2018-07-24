package kr.co.dm7.blackpink.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dm7.blackpink.domain.Subscriber;
import kr.co.dm7.blackpink.service.SubscriberService;
import lombok.Setter;

@RestController
public class SubscriberController {

	@Setter(onMethod = @__({@Autowired}))
	private SubscriberService userService;
		
	@GetMapping("/subscribers")
	public List<Subscriber> userList() {
		
		return userService.getUsers();
	}
	
	@GetMapping("/subscriber/{id}")
	public ResponseEntity<Subscriber> userList(@PathVariable Long id) {
		Optional<Subscriber> user = userService.getUser(id); 
		if(user.isPresent()){	//exist
			return new ResponseEntity<Subscriber>(user.get(), HttpStatus.OK);
		}
		else{
			return new ResponseEntity<Subscriber>(HttpStatus.NOT_FOUND);
		}		
	}
//	
//	@PutMapping("/subscriber/{id}")
//	public ResponseEntity<Subscriber> updateUser(@PathVariable Long id, @RequestBody Subscriber user) {
//		Subscriber updateUser = userService.updateUser(id, user); 
//		if(updateUser != null){	//exist
//			return new ResponseEntity<Subscriber>(updateUser, HttpStatus.OK);
//		}
//		else{
//			return new ResponseEntity<Subscriber>(HttpStatus.NOT_FOUND);
//		}		
//	}
//		
//	@DeleteMapping("/subscriber/{id}")
//	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//		if(userService.deleteUser(id)){	//exist
//			return new ResponseEntity<Void>(HttpStatus.OK);
//		}
//		else{
//			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//		}		
//	}

}
