package com.stackroute.keepnote.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stackroute.keepnote.model.Note;
import com.stackroute.keepnote.repository.NoteRepository;

/*Annotate the class with @Controller annotation. @Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 * */
@Controller
public class NoteController {
	
	private ApplicationContext ctx=new ClassPathXmlApplicationContext("beans.xml");
	private Note note=ctx.getBean("note", Note.class);
	private NoteRepository noteRepository=ctx.getBean("noteRepository", NoteRepository.class);
	/* 
	 * Get the application context from resources/beans.xml file using ClassPathXmlApplicationContext() class.
	 * Retrieve the Note object from the context.
	 * Retrieve the NoteRepository object from the context.
	 */

	/*Define a handler method to read the existing notes by calling the getAllNotes() method 
	 * of the NoteRepository class and add it to the ModelMap which is an implementation of Map 
	 * for use when building model data for use with views. it should map to the default URL i.e. "/" 
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	  public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("index", "noteform", new Note());
	/*	note.setNoteId(1);
		note.setNoteTitle("Load");
		note.setNoteContent("Load note content");
		note.setNoteStatus("not started");
		List<Note> list=new ArrayList<>();
		list.add(note);
		noteRepository.setList(list);*/
		List<Note> noteList=noteRepository.getAllNotes();
		mav.addObject("noteList", noteList);
	    return mav;
	  }
	
	/*Define a handler method which will read the Note data from request parameters and
	 * save the note by calling the addNote() method of NoteRepository class. Please note 
	 * that the createdAt field should always be auto populated with system time and should not be accepted 
	 * from the user. Also, after saving the note, it should show the same along with existing 
	 * notes. Hence, reading notes has to be done here again and the retrieved notes object 
	 * should be sent back to the view using ModelMap.
	 * This handler method should map to the URL "/saveNote". 
	*/
	 @RequestMapping(value = "/saveNote", method = RequestMethod.POST)
	  public ModelAndView addNote(@ModelAttribute Note note,HttpServletRequest request, HttpServletResponse response) {
		 if(note.getNoteTitle().equals("")|| note.getNoteContent().equals("")||note.getNoteStatus().equals("")) {
			 return new ModelAndView("index", "noteform", new Note());
		 }
		 noteRepository.addNote(note);
		 List<Note> noteList=noteRepository.getAllNotes();
		 ModelAndView mav=new ModelAndView("index","noteform",new Note());
		 mav.addObject("noteList", noteList);
	    return mav;
	  }
	
	/* Define a handler method to delete an existing note by calling the deleteNote() method 
	 * of the NoteRepository class
	 * This handler method should map to the URL "/deleteNote" 
	*/
	 
	 @GetMapping("deleteNote")
	 public String deleteNote(@RequestParam("noteId") String noteId) {
		 boolean status=noteRepository.deleteNote(Integer.parseInt(noteId));
		 return "redirect:/";
	 }
	 
	
}