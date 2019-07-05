package com.stackroute.keepnote.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.model.Note;

/*
 * Annotate the class with @Controller annotation.@Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@Controller
public class NoteController {

	
	/*
	 * From the problem statement, we can understand that the application requires
	 * us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the persistence data. Each note
	 * should contain Note Id, title, content, status and created date. 2. Add a new
	 * note which should contain the note id, title, content and status. 3. Delete
	 * an existing note 4. Update an existing note
	 * 
	 */

	/*
	 * Autowiring should be implemented for the NoteDAO. Create a Note object.
	 * 
	 */
	
	private NoteDAO noteDAO;
	@Autowired
	public NoteController(NoteDAO noteDao2) {
		this.noteDAO=noteDao2;
	}
	/*
	 * Define a handler method to read the existing notes from the database and add
	 * it to the ModelMap which is an implementation of Map, used when building
	 * model data for use with views. it should map to the default URL i.e. "/index"
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("index", "noteform", new Note());
		/*
		 * note.setNoteId(1); note.setNoteTitle("Load");
		 * note.setNoteContent("Load note content"); note.setNoteStatus("not started");
		 * List<Note> list=new ArrayList<>(); list.add(note);
		 * noteRepository.setList(list);
		 */
		List<Note> noteList = noteDAO.getAllNotes();
		mav.addObject("noteList", noteList);
		return mav;
	}

	/*
	 * Define a handler method which will read the NoteTitle, NoteContent,
	 * NoteStatus from request parameters and save the note in note table in
	 * database. Please note that the CreatedAt should always be auto populated with
	 * system time and should not be accepted from the user. Also, after saving the
	 * note, it should show the same along with existing messages. Hence, reading
	 * note has to be done here again and the retrieved notes object should be sent
	 * back to the view using ModelMap This handler method should map to the URL
	 * "/add".
	 */
		
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	  public ModelAndView addNote(@ModelAttribute Note note,HttpServletRequest request, HttpServletResponse response) {
		 if("".equals(note.getNoteTitle())|| "".equals(note.getNoteContent())||"".equals(note.getNoteStatus())) {
			 return new ModelAndView("index", "noteform", new Note());
		 }
		boolean saveStatus= noteDAO.saveNote(note);
	    return new ModelAndView("redirect:/");
	  }
	 
	/*
	 * Define a handler method which will read the NoteId from request parameters
	 * and remove an existing note by calling the deleteNote() method of the
	 * NoteRepository class.This handler method should map to the URL "/delete".
	 */
	 
	 @GetMapping("delete")
	 public String deleteNote(@RequestParam("noteId") String noteId) {
		 boolean status=noteDAO.deleteNote(Integer.parseInt(noteId));
		 return "redirect:/";
	 }

	/*
	 * Define a handler method which will update the existing note. This handler
	 * method should map to the URL "/update".
	 */
	 @RequestMapping(value = "/update", method = RequestMethod.POST)
	  public ModelAndView updateNote(@ModelAttribute Note note) {
		 System.out.println(note.getNoteUpdateId());
		 if("".equals(note.getNoteContent())||"".equals(note.getNoteStatus())) {
			 return new ModelAndView("index", "noteform", new Note());
		 }
		boolean saveStatus= noteDAO.UpdateNote(note);
	    return new ModelAndView("redirect:/");
	  }
	 
	 

}
