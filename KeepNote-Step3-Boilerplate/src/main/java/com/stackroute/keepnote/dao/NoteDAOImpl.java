package com.stackroute.keepnote.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.keepnote.exception.NoteNotFoundException;
import com.stackroute.keepnote.model.Note;

/*
 * This class is implementing the UserDAO interface. This class has to be annotated with 
 * @Repository annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, 
 * thus clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */
@Transactional
@Repository
public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.(Use
	 * constructor-based autowiring.
	 */
	@Autowired
	private SessionFactory sessionFactory;
	public NoteDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	/*
	 * Create a new note
	 */
	
	public boolean createNote(Note note) {
		try {
		sessionFactory.getCurrentSession().save(note);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/*
	 * Remove an existing note
	 */
	
	public boolean deleteNote(int noteId) {
		try {
			Note note=getNoteById(noteId);
			if(note==null) {
				return false;
			}
			sessionFactory.getCurrentSession().delete(note);
		} catch (NoteNotFoundException e) {
			return false;
		}
		return true;
	}

	/*
	 * Retrieve details of all notes by userId
	 */
	
	public List<Note> getAllNotesByUserId(String userId) {
		/*Criteria cr=sessionFactory.getCurrentSession().createCriteria(Note.class);
		cr.add(Restrictions.eq("createdBy",userId));
		List<Note> noteList=cr.list();*/
		List<Note> noteList=sessionFactory.getCurrentSession().createCriteria(Note.class).add(Restrictions.eq("createdBy", userId)).list();
		if(noteList.size()==0) {
		return null;	
		}
		return noteList;
		 

	}

	/*
	 * Retrieve details of a specific note
	 */
	
	public Note getNoteById(int noteId) throws NoteNotFoundException {
		Note note=sessionFactory.getCurrentSession().get(Note.class, noteId);
		if(note==null) {
			throw new NoteNotFoundException("Note found ");
		}
		return note;

	}

	/*
	 * Update an existing note
	 */

	public boolean UpdateNote(Note note) {
		sessionFactory.getCurrentSession().update(note);
		return true;

	}

}
