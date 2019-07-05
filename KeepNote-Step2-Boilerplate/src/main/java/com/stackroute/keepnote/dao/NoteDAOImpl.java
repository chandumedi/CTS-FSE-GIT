package com.stackroute.keepnote.dao;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.keepnote.model.Note;

/*
 * This class is implementing the NoteDAO interface. This class has to be annotated with @Repository
 * annotation.
 * @Repository - is an annotation that marks the specific class as a Data Access Object, thus 
 * 				 clarifying it's role.
 * @Transactional - The transactional annotation itself defines the scope of a single database 
 * 					transaction. The database transaction happens inside the scope of a persistence 
 * 					context.  
 * */
@Repository
@Transactional
public class NoteDAOImpl implements NoteDAO {

	/*
	 * Autowiring should be implemented for the SessionFactory.
	 */

	private SessionFactory sessionFactory;

	@Autowired
	public NoteDAOImpl(SessionFactory sessionFactory1) {
		this.sessionFactory = sessionFactory1;
	}

	/*
	 * Save the note in the database(note) table.
	 */

	public boolean saveNote(Note note) {
		try {
		note.setCreatedAt(LocalDateTime.now());
		Session session = sessionFactory.getCurrentSession();
		session.save(note);
		
		return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
	}

	/*
	 * Remove the note from the database(note) table.
	 */

	public boolean deleteNote(int noteId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.delete(getNoteById(noteId));
			
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/*
	 * retrieve all existing notes sorted by created Date in descending
	 * order(showing latest note first)
	 */
	public List<Note> getAllNotes() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Note.class);
		List<Note> noteList = sortByCreationDate(criteria.list());
		return noteList;

	}

	@SuppressWarnings("unchecked")
	private List<Note> sortByCreationDate(List list) {
		list.sort(Comparator.comparing(Note::getCreatedAt).reversed());
		return list;
	}

	/*
	 * retrieve specific note from the database(note) table
	 */
	public Note getNoteById(int noteId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Note.class);
			criteria.add(Restrictions.eq("noteId", noteId));
			return (Note) criteria.uniqueResult();
		} catch (Exception e) {
			return null;
		}
	}

	/* Update existing note */

	public boolean UpdateNote(Note note) {
		try {
		Session session = sessionFactory.getCurrentSession();
		Note updateNote = getNoteById(note.getNoteId());
		updateNote.setNoteContent(note.getNoteContent());
		updateNote.setNoteStatus(note.getNoteStatus());
		session.update(updateNote);
		return true;
		}catch (Exception e) {
			return false;
		}

	}

}
