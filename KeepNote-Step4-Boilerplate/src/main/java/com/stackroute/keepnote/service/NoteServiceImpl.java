package com.stackroute.keepnote.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.dao.CategoryDAO;
import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.dao.ReminderDAO;
import com.stackroute.keepnote.dao.UserDAO;
import com.stackroute.keepnote.exception.CategoryNotFoundException;
import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.exception.ReminderNotFoundException;
import com.stackroute.keepnote.model.Category;
import com.stackroute.keepnote.model.Note;
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
public class NoteServiceImpl implements NoteService {

	/*
	 * Autowiring should be implemented for the NoteDAO,CategoryDAO,ReminderDAO.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
	private NoteDAO noteDao;
	@Autowired
	private ReminderDAO reminderDao;
	@Autowired
	private CategoryDAO categoryDao;

	public NoteServiceImpl(NoteDAO noteDao, ReminderDAO reminderDao, CategoryDAO categoryDao) {
		this.noteDao = noteDao;
		this.reminderDao = reminderDao;
		this.categoryDao = categoryDao;
	}

	/*
	 * This method should be used to save a new note.
	 */
	public boolean createNote(Note note) throws ReminderNotFoundException, CategoryNotFoundException {
		boolean createNote = noteDao.createNote(note);

		Reminder reminder = note.getReminder();
		Category category = note.getCategory();
		try {
			if (reminder != null) {
				reminderDao.getReminderById(reminder.getReminderId());

			}
		} catch (ReminderNotFoundException e) {
			// TODO: handle exception

			throw new ReminderNotFoundException("ReminderNotFoundException");
		}
		try {

			if (category != null) {
				categoryDao.getCategoryById(category.getCategoryId());
			}

		} catch (CategoryNotFoundException e) {
			// TODO: handle exception
			throw new CategoryNotFoundException("CategoryNotFoundException");
		}

		return createNote;

	}

	/* This method should be used to delete an existing note. */

	public boolean deleteNote(int noteId) {

		try {
			Note note = getNoteById(noteId);
			if (note == null) {
				return false;
			}

		} catch (NoteNotFoundException e) {

		}

		return noteDao.deleteNote(noteId);

	}
	/*
	 * This method should be used to get a note by userId.
	 */

	public List<Note> getAllNotesByUserId(String userId) {
		return noteDao.getAllNotesByUserId(userId);

	}

	/*
	 * This method should be used to get a note by noteId.
	 */
	public Note getNoteById(int noteId) throws NoteNotFoundException {
		Note note = noteDao.getNoteById(noteId);
		if (note == null) {
			throw new NoteNotFoundException("Note not found");
		}
		return note;

	}

	/*
	 * This method should be used to update a existing note.
	 */

	public Note updateNote(Note note, int id)
			throws ReminderNotFoundException, NoteNotFoundException, CategoryNotFoundException {

		Note noteExisted = getNoteById(id);
		Reminder reminder = note.getReminder();
		Category category = note.getCategory();

		if (noteExisted == null) {
			throw new NoteNotFoundException("NoteNotFoundException");
		}

		else {
			noteExisted.setCategory(note.getCategory());
			noteExisted.setReminder(note.getReminder());
			noteExisted.setNoteContent(note.getNoteContent());
			noteExisted.setNoteTitle(note.getNoteTitle());
			noteExisted.setNoteStatus(note.getNoteStatus());
			noteDao.UpdateNote(noteExisted);
			

		}
		try {
			if (reminder != null)

			{
				reminderDao.getReminderById(reminder.getReminderId());
			}
		} catch (ReminderNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try {
			if (category != null)

			{
				categoryDao.getCategoryById(category.getCategoryId());
			}
		} catch (CategoryNotFoundException e) {

		}
		return noteExisted;

	}

}
