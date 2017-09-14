package de.chrcho.mp3reader.util;

import java.io.File;

/**
 * Scan directory for files and handle them.
 * 
 * @author Chrcho
 */
public class DirectoryScanner {
	
	/**
	 * Scan directory
	 * 
	 * @param directory Directory of files
	 * @param fileHandler Specific file handler 
	 */
	public void scan(File directory, FileHandler fileHandler) {
		File[] fileArray = directory.listFiles();
		if (fileArray != null) {
			for (File file : fileArray) {
				if (file.isDirectory()) {
					this.scan(file, fileHandler);
				} else {
					fileHandler.handle(file);
				}
			}
		}
	}
}