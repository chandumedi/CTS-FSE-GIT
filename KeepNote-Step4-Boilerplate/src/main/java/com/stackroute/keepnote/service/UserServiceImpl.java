package com.stackroute.keepnote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.UserDAO;
import com.stackroute.keepnote.exception.UserAlreadyExistException;
import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;

/*
* Service classes are used here to implement additional business logic/validation 
* This class has to be annotated with @Service annotation.
* @Service - It is a specialization of the component annotation. It doesn�t currently 
* provide any additional behavior over the @Component annotation, but it�s a good idea 
* to use @Service over @Component in service-layer classes because it specifies intent 
* better. Additionally, tool support and additional behavior might rely on it in the 
* future.
* */
@Service
public class UserServiceImpl implements UserService {

	/*
	 * Autowiring should be implemented for the userDAO. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword.
	 */
	@Autowired
	private UserDAO userDAO;

	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/*
	 * This method should be used to save a new user.
	 */
	public boolean registerUser(User user) throws UserAlreadyExistException {
		try {
			User existingUser = getUserById(user.getUserId());
			if (existingUser != null) {
				throw new UserAlreadyExistException("User already exist");
			}

		} catch (UserNotFoundException e) {

		}
		return userDAO.registerUser(user);

	}

	/*
	 * This method should be used to update a existing user.
	 */

	public User updateUser(User updatedUser, String userId) throws Exception {
		User user = null;
		try {
			user = getUserById(userId);
			user.setUserName(updatedUser.getUserName());
			user.setUserMobile(updatedUser.getUserMobile());
			user.setUserPassword(updatedUser.getUserPassword());
			boolean updatedStatus = userDAO.updateUser(user);
			if(!updatedStatus) {
				return null;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return user;

	}

	/*
	 * This method should be used to get a user by userId.
	 */

	public User getUserById(String UserId) throws UserNotFoundException {
		try {
			User user = userDAO.getUserById(UserId);
			if (user == null) {
				throw new UserNotFoundException("User not fount with specified user id");
			}
			return user;

		} catch (Exception e) {
			throw new UserNotFoundException("User not fount with specified user id");
		}

	}

	/*
	 * This method should be used to validate a user using userId and password.
	 */

	public boolean validateUser(String userId, String password) throws UserNotFoundException {
		boolean validateFlag = userDAO.validateUser(userId, password);
		if (!validateFlag) {
			throw new UserNotFoundException("User not found with credentials");
		}
		return validateFlag;
	}

	/* This method should be used to delete an existing user. */
	public boolean deleteUser(String UserId) {
		try {
			User user = getUserById(UserId);
			if (user == null) {
				return false;
			}
		} catch (Exception e) {
			return userDAO.deleteUser(UserId);
		}
		return userDAO.deleteUser(UserId);

	}

}
