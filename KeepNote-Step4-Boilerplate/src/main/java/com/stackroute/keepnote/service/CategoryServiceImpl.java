package com.stackroute.keepnote.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.CategoryDAO;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.model.Category;

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
public class CategoryServiceImpl implements CategoryService {
	/*
	 * Autowiring should be implemented for the CategoryDAO. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword.
	 */
	@Autowired
	private CategoryDAO categoryDao;

	@Autowired
	private HttpSession session;

	public CategoryServiceImpl(CategoryDAO categoryDao) {
		this.categoryDao = categoryDao;
	}

	/*
	 * This method should be used to save a new category.
	 */
	public boolean createCategory(Category category) {
		try {
			categoryDao.getCategoryById(category.getCategoryId());
			return categoryDao.createCategory(category);
		} catch (CategoryNotFoundException e) {
			String userId = (String) session.getAttribute("loggedInUserId");
			List<Category> catList = categoryDao.getAllCategoryByUserId(userId);
			Category catObject = (Category) catList.stream()
					.filter(r -> r.getCategoryName().equals(category.getCategoryName()));
			if (catObject == null) {
				return false;
			}
		}
		return false;
	}

	/* This method should be used to delete an existing category. */
	public boolean deleteCategory(int categoryId) {
		try {
			Category category=getCategoryById(categoryId);
			if(category==null) {
				return false;
			}
			
		} catch (CategoryNotFoundException e) {
			
		}
		
		return categoryDao.deleteCategory(categoryId);

	}

	/*
	 * This method should be used to update a existing category.
	 */

	public Category updateCategory(Category category, int id) throws CategoryNotFoundException {
		try {
			Category existingCat=getCategoryById(id);
			existingCat.setCategoryDescription(category.getCategoryDescription());
			existingCat.setCategoryName(category.getCategoryName());
			existingCat.setNotes(category.getNotes());
			categoryDao.updateCategory(existingCat);
		}catch (Exception e) {
			throw new  CategoryNotFoundException("Category not found");	
		}
		
		return category;

	}

	/*
	 * This method should be used to get a category by categoryId.
	 */
	public Category getCategoryById(int categoryId) throws CategoryNotFoundException {
		Category cat=categoryDao.getCategoryById(categoryId);
		if(cat==null) {
			throw new CategoryNotFoundException("Category not found");
		}
		return cat;

	}

	/*
	 * This method should be used to get a category by userId.
	 */

	public List<Category> getAllCategoryByUserId(String userId) {
		
		return categoryDao.getAllCategoryByUserId(userId);

	}

}
