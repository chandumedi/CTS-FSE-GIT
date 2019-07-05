package com.stackroute.keepnote.model;

import java.util.Date;
import java.util.List;

public class Note {
	
	/*
	 * This class should have eight fields
	 * (noteId,noteTitle,noteContent,noteStatus,createdAt,
	 * category,reminder,createdBy). This class should also contain the
	 * getters and setters for the fields along with the no-arg , parameterized
	 * constructor and toString method. The value of createdAt should not be
	 * accepted from the user but should be always initialized with the system date.
	 * 
	 */
	
	private int noteId;
	private String noteTitle;
	private String noteContent;
	private String noteStatus;
	private Date createdAt;
	private List<Reminder> reminder;
	private Category category;
	private String createdBy;
	public Note() {
		
	}
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getNoteContent() {
		return noteContent;
	}
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	public String getNoteStatus() {
		return noteStatus;
	}
	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}
	public Date getNoteCreationDate() {
		return createdAt;
	}
	public void setNoteCreationDate(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public List<Reminder> getReminder() {
		return reminder;
	}
	public void setReminders(List<Reminder> reminder) {
		this.reminder = reminder;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getNoteCreatedBy() {
		return createdBy;
	}
	public void setNoteCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	    
	
}
