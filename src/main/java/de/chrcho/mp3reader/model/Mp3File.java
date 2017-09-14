package de.chrcho.mp3reader.model;

import java.io.File;

/**
 * Mp3 file with some ID3v1 information (title, interpreter, genre).
 * 
 * @author Chrcho
 */
public class Mp3File {

	private File file;

	private boolean tag;
	private String title = "";
	private String interpreter = "";
	private String genre = "";

	public Mp3File() {

	}

	public Mp3File(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public boolean isTag() {
		return tag;
	}

	public void setTag(boolean tag) {
		this.tag = tag;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getInterpreter() {
		return interpreter;
	}

	public void setInterpreter(String interpreter) {
		this.interpreter = interpreter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}