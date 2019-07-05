package com.stackroute.keepnote.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.keepnote.exception.UserNotFoundException;
import com.stackroute.keepnote.model.User;

/*
 * This class is implementing the UserDAO interface. This class has to be annotated with 
 * @Repository annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, 
 * thus clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */
@Repository
@Transactional
public class UserDaoImpl implements UserDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	@Autowired
	private SessionFactory sessionFactory;
	public UserDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	/*
	 * Create a new user
	 */

	public boolean registerUser(User user) {
		sessionFactory.getCurrentSession().save(user);
		return true;
	}

	/*
	 * Update an existing user
	 */

	public boolean updateUser(User user) {
		sessionFactory.getCurrentSession().save(user);

		return true;

	}

	/*
	 * Retrieve details of a specific user
	 */
	public User getUserById(String userId) {
		
		return sessionFactory.getCurrentSession().get(User.class, userId);
	}

	/*
	 * validate an user
	 */

	public boolean validateUser(String userId, String password) throws UserNotFoundException {
		Criteria cr=sessionFactory.getCurrentSession().createCriteria(User.class);
		cr.add(Restrictions.eq("userId",userId));
		cr.add(Restrictions.eq("userPassword",password));
		User user=(User) cr.uniqueResult();
		if(user==null) {
			
			throw new UserNotFoundException("User not found");
		}
		return true;

	}

	/*
	 * Remove an existing user
	 */
	public boolean deleteUser(String userId) {
		User user=getUserById(userId);
		if(user==null) {
			return false;
		}
		sessionFactory.getCurrentSession().delete(user);
		return true;

	}

}
