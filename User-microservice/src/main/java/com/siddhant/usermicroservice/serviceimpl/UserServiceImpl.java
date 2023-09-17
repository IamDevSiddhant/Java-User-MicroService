package com.siddhant.usermicroservice.serviceimpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddhant.usermicroservice.entity.User;
import com.siddhant.usermicroservice.exception.GlobalException;
import com.siddhant.usermicroservice.repository.UserRepository;
import com.siddhant.usermicroservice.service.UserService;

@Service

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepo;
	
	
	@Override
	public User saveUser(User user) {
		
		String randomuserid = UUID.randomUUID().toString(); 
		user.setId(randomuserid);
		return userrepo.save(user); 
	}

	@Override
	public List<User> getAllUsers() {
		return userrepo.findAll();
	}

	@Override
	public User getUser(String userId) {
		return userrepo.findById(userId).orElseThrow(()->new GlobalException("User Not Found on Server!!!"));
	}

	@Override
	public User updateUser(String Id,User updatedUser) {
		
		User existingUser = userrepo.findById(Id).orElseThrow(()->new GlobalException("User Not Found on Server for updating!!!"));
		
//		User updatedUser = new User();
		
		//existingUser.setId(updatedUser.getId());
		existingUser.setUserName(updatedUser.getUserName());
		existingUser.setUserEmail(updatedUser.getUserEmail());
		existingUser.setUserDescription(updatedUser.getUserDescription());
		
		return userrepo.save(existingUser);
	}

//	@Override
//	public String deleteUser(String Id) {
//		userrepo.deleteById(Id);
//		return "User Deleted!!!";
//	}

}
