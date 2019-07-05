package com.stackroute.keepnote.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exceptions.UserAlreadyExistsException;
import com.stackroute.keepnote.exceptions.UserNotFoundException;
import com.stackroute.keepnote.model.User;
import com.stackroute.keepnote.repository.UserRepository;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn't currently 
* provide any additional behavior over the @Component annotation, but it's a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class UserServiceImpl implements UserService {

	/*
	 * Autowiring should be implemented for the UserRepository. (Use
	 * Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	/*
	 * This method should be used to save a new user.Call the corresponding method
	 * of Respository interface.
	 */

	public User registerUser(User user) throws UserAlreadyExistsException {
		try {
			user.setUserAddedDate(new Date());
			User existingUser = getUserById(user.getUserId());
			if (existingUser != null) {
				throw new UserAlreadyExistsException("UserAlreadyExistsException");
			}
			
		} catch (UserNotFoundException e) {
			throw new UserAlreadyExistsException("UserAlreadyExistsException");
		}
		User insertStatus=userRepository.insert(user);
		if(insertStatus==null) {throw new UserAlreadyExistsException("UserAlreadyExistsException");}
		return user;
	}

	/*
	 * This method should be used to update a existing user.Call the corresponding
	 * method of Respository interface.
	 */

	public User updateUser(String userId, User user) throws UserNotFoundException {
		try {
			User existingUser = getUserById(userId);
			user.setUserAddedDate(existingUser.getUserAddedDate());
			user.setUserId(userId);
			userRepository.delete(existingUser);
			userRepository.insert(user);
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException("UserNotFoundException");
		}
		return user;
	}

	/*
	 * This method should be used to delete an existing user. Call the corresponding
	 * method of Respository interface.
	 */

	public boolean deleteUser(String userId) throws UserNotFoundException {
		try {
			User existingUser = getUserById(userId);
			userRepository.delete(existingUser);
		} catch (UserNotFoundException e) {
			throw new UserNotFoundException("UserNotFoundException");
		}
		return true;
	}

	/*
	 * This method should be used to get a user by userId.Call the corresponding
	 * method of Respository interface.
	 */

	public User getUserById(String userId) throws UserNotFoundException {
		try {
			Optional<User> userRepo = userRepository.findById(userId);
			if (!userRepo.isPresent()) {
				return null;
			}
			return userRepo.get();
		} catch (Exception e) {
			throw new UserNotFoundException("User not found");
		}
	}

}
