package com.stackroute.keepnote.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;
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
public class CategoryDAOImpl implements CategoryDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	@Autowired
	private SessionFactory sessionFactory;

	public CategoryDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * Create a new category
	 */
	public boolean createCategory(Category category) {
		 sessionFactory.getCurrentSession().save(category);
	
		return true;

	}

	/*
	 * Remove an existing category
	 */
	public boolean deleteCategory(int categoryId) {
		try {
			Category category=getCategoryById(categoryId);
			if(category!=null) {
				sessionFactory.getCurrentSession().delete(category);
			}
			
		}  catch (CategoryNotFoundException e) {
			
		}

		return true;
	}
	/*
	 * Update an existing category
	 */

	public boolean updateCategory(Category category) {
		sessionFactory.getCurrentSession().update(category);
		return true;

	}
	/*
	 * Retrieve details of a specific category
	 */

	public Category getCategoryById(int categoryId) throws CategoryNotFoundException {
		Category cat=sessionFactory.getCurrentSession().get(Category.class, categoryId);
		if(cat==null) {
			throw new CategoryNotFoundException("Category not found");
		}
		return cat;

	}

	/*
	 * Retrieve details of all categories by userId
	 */
	public List<Category> getAllCategoryByUserId(String userId) {
		Criteria cr=sessionFactory.getCurrentSession().createCriteria(Category.class);
		cr.add(Restrictions.eq("categoryCreatedBy",userId));
		List<Category> catList=cr.list();
		return cr.list();

	}

}
