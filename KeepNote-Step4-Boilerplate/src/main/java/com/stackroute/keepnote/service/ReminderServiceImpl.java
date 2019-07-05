package com.stackroute.keepnote.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.ReminderDAO;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Reminder;

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
public class ReminderServiceImpl implements ReminderService {

	/*
	 * Autowiring should be implemented for the ReminderDAO. (Use Constructor-based
	 * autowiring) Please note that we should not create any object using the new
	 * keyword.
	 */

	@Autowired
	private ReminderDAO reminderDao;
	

	/*
	 * This method should be used to save a new reminder.
	 */

	public ReminderServiceImpl(HttpSession session, ReminderDAO reminderDao) {
		
		this.reminderDao = reminderDao;
	}

	public boolean createReminder(Reminder reminder) {
		boolean cretFlag=false;
		try {
			Reminder existReminder=getReminderById(reminder.getReminderId());
			if(existReminder!=null) {
				return cretFlag;
			}
			
		} catch (ReminderNotFoundException e) {
			List<Reminder> catList = getAllReminderByUserId(reminder.getReminderCreatedBy());
			Reminder catObject =  catList.stream()
					.filter(r -> r.getReminderName().equals(reminder.getReminderName())).findAny().orElse(null);
			
			if (catObject == null) {
				 cretFlag=reminderDao.createReminder(reminder);
			}
			
		}
		return cretFlag;

	}

	/*
	 * This method should be used to update a existing reminder.
	 */

	public Reminder updateReminder(Reminder reminder, int id) throws ReminderNotFoundException {
		try {
			Reminder existReminder=getReminderById(id);
			existReminder.setNotes(reminder.getNotes());
			existReminder.setReminderDescription(reminder.getReminderDescription());
			existReminder.setReminderName(reminder.getReminderName());
			existReminder.setReminderType(reminder.getReminderType());
			boolean updatedReminder=reminderDao.updateReminder(existReminder);
			if(updatedReminder) return existReminder;
		}catch (Exception e) {
			throw new ReminderNotFoundException(e.getMessage());
		}
		return null;
	}

	/* This method should be used to delete an existing reminder. */
	
	public boolean deleteReminder(int reminderId) {

		reminderDao.deleteReminder(reminderId);
		return true;
	}

	/*
	 * This method should be used to get a reminder by reminderId.
	 */
	
	public Reminder getReminderById(int reminderId) throws ReminderNotFoundException {
		Reminder rem=reminderDao.getReminderById(reminderId);
		if(rem==null) {
			throw new ReminderNotFoundException("Reminder not found");
		}
		return rem;

	}

	/*
	 * This method should be used to get a reminder by userId.
	 */

	public List<Reminder> getAllReminderByUserId(String userId) {
		return reminderDao.getAllReminderByUserId(userId);

	}
}
