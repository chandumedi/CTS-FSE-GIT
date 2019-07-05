package com.stackroute.keepnote.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.keepnote.exception.NoteNotFoundExeption;
import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.model.NoteUser;
import com.stackroute.keepnote.repository.NoteRepository;

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
public class NoteServiceImpl implements NoteService {

	/*
	 * Autowiring should be implemented for the NoteRepository and MongoOperation.
	 * (Use Constructor-based autowiring) Please note that we should not create any
	 * object using the new keyword.
	 */
	@Autowired
	private NoteRepository noteRepository;

	public NoteServiceImpl(NoteRepository noteRepository) {
		super();
		this.noteRepository = noteRepository;
	}

	/*
	 * This method should be used to save a new note.
	 */
	public boolean createNote(Note note) {
		note.setNoteCreationDate(new Date());
		NoteUser noteUser=null;
		int count=1;
		List<Note> noteList=new ArrayList<>();
		int random = (int)(Math.random() * 50 + 1);
		note.setNoteId(random);
		Optional<NoteUser> noteUserRepo = noteRepository.findById(note.getNoteCreatedBy());
		if (!noteUserRepo.isPresent()) {
			 noteUser = new NoteUser();
			noteUser.setUserId(note.getNoteCreatedBy());
			noteList.add(note);
			
			
		}else {
			 noteUser=noteUserRepo.get();
			 if(noteUser.getNotes()!=null)
			 noteList.addAll(noteUser.getNotes());
			 noteList.add(note);
		}
		noteUser.setNotes(noteList);
		noteRepository.delete(noteUser);
		NoteUser noteUserFlag = noteRepository.insert(noteUser);
		if (noteUserFlag == null)
			return false;
		return true;
	}

	/* This method should be used to delete an existing note. */

	public boolean deleteNote(String userId, int noteId) {
		try {
			List<Note> noteList = getAllNoteByUserId(userId);
			noteList.removeIf(r -> noteId == r.getNoteId());
			NoteUser noteUser = new NoteUser();
			noteUser.setUserId(userId);
			noteUser.setNotes(noteList);
			NoteUser userNot = noteRepository.findById(userId).get();
			noteRepository.delete(userNot);
			noteRepository.insert(noteUser);
		} catch (Exception e) {
			throw new NullPointerException();
		}
		return true;
	}

	/* This method should be used to delete all notes with specific userId. */

	public boolean deleteAllNotes(String userId) {
		try {
			List<Note> noteList = getAllNoteByUserId(userId);
			if (noteList.isEmpty()) {
				return false;
			}
			NoteUser noteUser = new NoteUser();
			noteUser.setUserId(userId);
			noteUser.setNotes(null);
			noteRepository.save(noteUser);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/*
	 * This method should be used to update a existing note.
	 */
	public Note updateNote(Note note, int id, String userId) throws NoteNotFoundExeption {
		try {
			Optional<NoteUser> noteUserRepo = noteRepository.findById(userId);
			NoteUser noteUser = noteUserRepo.get();
			if (noteUser.getNotes() != null) {
				List<Note> notes = noteUser.getNotes();
				List<Note> updatedList = new ArrayList<>();
				for (Note note2 : notes) {
					if (id == note2.getNoteId()) {
						updatedList.add(note);
					}else {
					updatedList.add(note2);
					}
				}
				noteUser.setNotes(updatedList);
				noteRepository.delete(noteUser);
				noteRepository.save(noteUser);
			}
		} catch (Exception e) {
			throw new NoteNotFoundExeption("NoteNotFoundExeption");
		}

		return note;
	}

	/*
	 * This method should be used to get a note by noteId created by specific user
	 */
	public Note getNoteByNoteId(String userId, int noteId) throws NoteNotFoundExeption {
		try {
			List<Note> note = getAllNoteByUserId(userId);
			if (note == null) {
				throw new NoteNotFoundExeption("NoteNotFoundExeption");
			}
			Note existingNote = note.stream().filter(r -> noteId == r.getNoteId()).findAny().orElse(null);
			return existingNote;
		} catch (Exception e) {
			throw new NoteNotFoundExeption("NoteNotFoundExeption");
		}
	}

	/*
	 * This method should be used to get all notes with specific userId.
	 */
	public List<Note> getAllNoteByUserId(String userId) {
		List<Note> noteList = null;
		try {
			NoteUser noteUser = noteRepository.findById(userId).get();
			noteList = noteUser.getNotes();
		} catch (Exception e) {
			return null;
		}
		return noteList;
	}

}
