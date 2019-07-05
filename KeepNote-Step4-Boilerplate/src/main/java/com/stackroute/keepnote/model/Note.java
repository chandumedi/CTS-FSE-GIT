package com.stackroute.keepnote.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */
@Entity
@Table(name = "Note")
public class Note implements Serializable {
	/*
	 * This class should have eight fields
	 * (noteId,noteTitle,noteContent,noteStatus,createdAt,
	 * category,reminder,createdBy). Out of these eight fields, the field noteId
	 * should be primary key and auto-generated. This class should also contain the
	 * getters and setters for the fields along with the no-arg , parameterized
	 * constructor and toString method. The value of createdAt should not be
	 * accepted from the user but should be always initialized with the system date.
	 * annotate category and reminder field with @ManyToOne.
	 */

	private static final long serialVersionUID = -8358573191620477890L;

	@Id

	@Column(name = "note_id")
	private int noteId;

	@Column(name = "note_title")
	private String noteTitle;
	@Column(name = "note_content")
	private String noteContent;
	@Column(name = "note_status")
	private String noteStatus;
	@Column(name = "note_creation_date")
	private Date noteCreatedAt;
	@Column(name = "note_creation_by")
	private String createdBy;
	@ManyToOne
	private Category category;
	@ManyToOne
	private Reminder reminder;

	

	public Note() {

	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Note(int noteId, String noteTitle, String noteContent, String noteStatus, Date createdAt, Category category,
			Reminder remainder, String createdBy) {
	
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteStatus = noteStatus;
		this.noteCreatedAt = createdAt;
		this.category = category;
		this.reminder = remainder;
		this.category=category;
		this.createdBy=createdBy;
	}



	public int getNoteId() {

		return noteId;
	}

	public String getNoteTitle() {

		return noteTitle;
	}

	public String getNoteContent() {

		return noteContent;
	}

	public String getNoteStatus() {

		return noteStatus;
	}

	public void setNoteId(int noteId) {
		this.noteId=noteId;
	}

	

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public void setNoteStatus(String noteStatus) {
		this.noteStatus = noteStatus;
	}

	



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	



	public Reminder getReminder() {
		return reminder;
	}



	public void setReminder(Reminder reminder) {
		this.reminder = reminder;
	}



	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public Date getNoteCreatedAt() {
		return noteCreatedAt;
	}



	public void setNoteCreatedAt(Date noteCreatedAt) {
		this.noteCreatedAt = noteCreatedAt;
	}
	
	

}
