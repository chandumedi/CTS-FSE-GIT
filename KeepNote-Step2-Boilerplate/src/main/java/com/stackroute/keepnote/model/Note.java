package com.stackroute.keepnote.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/*
 * The class "Note" will be acting as the data model for the note Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 */
@Entity
@Table(name = "note")
public class Note implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8358573191620477890L;
	public String getNoteUpdateId() {
		return noteUpdateId;
	}



	public void setNoteUpdateId(String noteUpdateId) {
		this.noteUpdateId = noteUpdateId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "note_id")
	private int noteId;
	@Transient
	private String noteUpdateId;
	
	@Column(name = "note_title")
	private String noteTitle;
	@Column(name = "note_content")
	private String noteContent;
	public Note(int noteId, String noteTitle, String noteContent, String noteStatus, LocalDateTime createdAt) {
		super();
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteContent = noteContent;
		this.noteStatus = noteStatus;
		this.createdAt = createdAt;
	}

	@Column(name = "note_status")
	private String noteStatus;
	@Column(name = "note_creation_date")
	private LocalDateTime createdAt;

	public Note() {

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

	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
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

	public void setCreatedAt(LocalDateTime now) {
		this.createdAt = now;
	}

}
