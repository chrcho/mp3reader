package de.chrcho.mp3reader;

import java.io.File;

import de.chrcho.mp3reader.model.ID3v1GenreFinder;
import de.chrcho.mp3reader.service.Mp3FileHandler;
import de.chrcho.mp3reader.util.DirectoryScanner;
import de.chrcho.mp3reader.view.ConsoleOutput;

/**
 * Scan all mp3 files of directory and subdirectories and print some ID3v1
 * information.
 * 
 * @author Chrcho
 */
public class Mp3Reader {

	public static void main(String[] args) {

		// Required params not available > show help
		if (args.length == 0) {
			ConsoleOutput.printHelp("Es muss ein Verzeichnis angegeben werden!", false);
			System.exit(1);
		}

		// First param - directory to scan (required)
		File directory = new File(args[0]);
		if (!directory.isDirectory()) {
			// Wrong directory
			ConsoleOutput.printHelp("Das angegebene Verzeichnis existiert nicht!", false);
			System.exit(1);
		}

		// Second param - genre filter (optional)
		String genreFilter = null;
		if (args.length == 2) {
			if (ID3v1GenreFinder.check(args[1])) {
				genreFilter = args[1];
			} else {
				// Genre not found
				ConsoleOutput.printHelp("Das angegebene Genre existiert nicht!", true);
				System.exit(1);
			}
		}

		// Scan all files of directory and subdirectories
		// and create mp3 file list
		DirectoryScanner directoryScanner = new DirectoryScanner();
		Mp3FileHandler mp3FileHandler = new Mp3FileHandler();
		directoryScanner.scan(directory, mp3FileHandler);

		// Print all mp3 file
		ConsoleOutput.printMp3FileList(mp3FileHandler.getMp3FileList(), genreFilter);
	}
}