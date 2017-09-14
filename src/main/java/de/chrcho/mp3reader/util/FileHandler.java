package de.chrcho.mp3reader.util;

import java.io.File;

/**
 * Handler for founded files by directory scanner.
 * 
 * @author Chrcho
 */
public interface FileHandler {

	/**
	 * Handle founded file
	 * 
	 * @param file File object is a file
	 */
	public void handle(File file);
}