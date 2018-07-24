package kr.co.dm7.blackpink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.dm7.blackpink.domain.Subscriber;
import kr.co.dm7.blackpink.repository.UserRepository;

@Service
public class SubscriberService {

	@Autowired
	private UserRepository userRepository;
	
	public boolean createUser(Subscriber user){
		try{
			userRepository.save(user);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public Optional<Subscriber> getUser(Long id){
		return userRepository.findById(id);
	}
	
	public List<Subscriber> getUsers(){
		return userRepository.findAll();
	}
	
	public Subscriber updateUser(Long id, Subscriber user){
		final Optional<Subscriber> fetchedUser = userRepository.findById(id);
		if(fetchedUser.isPresent()){
			user.setId(id);
			return userRepository.save(user);
		}
		else{
			return null;
		}
	}
	
	public Subscriber patchUser(Long id, Subscriber user){
		final Optional<Subscriber> fetchedUser = userRepository.findById(id);
		if(fetchedUser.isPresent()){
			if(user.getName() != null){
				fetchedUser.get().setName(user.getName());
			}
			
			return userRepository.save(fetchedUser.get());
		}
		else{
			return null;
		}
	}
	
	public boolean deleteUser(Long id){
		final Optional<Subscriber> fetchedUser = userRepository.findById(id);
		if(fetchedUser.isPresent()){
			userRepository.deleteById(id);
			return true;
		}
		else{
			return false;
		}
	}

}
