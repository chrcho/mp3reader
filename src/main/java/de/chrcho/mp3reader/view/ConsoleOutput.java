package de.chrcho.mp3reader.view;

import java.util.List;

import de.chrcho.mp3reader.Mp3Reader;
import de.chrcho.mp3reader.model.ID3v1GenreFinder;
import de.chrcho.mp3reader.model.Mp3File;

/**
 * Console outputs of mp3 reader.
 * 
 * @author Chrcho
 */
public class ConsoleOutput {

	/**
	 * Print help if error occurs.
	 * 
	 * @param error Error message
	 * @param printAllGenres Print all genre in help (true)
	 */
	public static void printHelp(String error, boolean printAllGenres) {
		System.out.println("Verwendung: java -jar " + Mp3Reader.class.getSimpleName().toLowerCase() + 
				".jar directory [genre]");
		System.out.println("   directory   Gültiges Verzeichnis mit MP3-Dateien");
		System.out.println("   genre       Anzeige nach Genre filtern");
		if(printAllGenres) {
			System.out.println();
			System.out.println("Nach folgenden Genres kann gefiltert werden:");
			int maxGenreLength = 0;
			for (String genre : ID3v1GenreFinder.GENRES) {
				if(genre.length() > maxGenreLength) {
					maxGenreLength = genre.length();
				}
			}
			for (int i = 0; i < ID3v1GenreFinder.GENRES.length; i++) {
				System.out.printf("   %-" + maxGenreLength + "s", ID3v1GenreFinder.GENRES[i]);
				if(((i + 1) % 4) == 0) {
					System.out.println();
				}
			}
		}
		if(error != null) {
			System.out.println();
			System.out.println("Fehler: " + error);
		}
	}

	/**
	 * Print all mp3 files with optional genre filter.
	 * 
	 * @param mp3FileList List of mp3 files
	 * @param genreFilter Show only mp3 files with this genre
	 */
	public static void printMp3FileList(List<Mp3File> mp3FileList, String genreFilter) {
		// Get max genre string length
		int maxGenreLength = 0;
		for (Mp3File mp3File : mp3FileList) {
			if(genreFilter == null || genreFilter.equals(mp3File.getGenre())) {
				if(mp3File.getGenre().length() > maxGenreLength) {
					maxGenreLength = mp3File.getGenre().length();
				}
			}
		}
		// Get max genre string length
		int maxSongNameLength = 0;
		for (Mp3File mp3File : mp3FileList) {
			if(genreFilter == null || genreFilter.equals(mp3File.getGenre())) {
				int songNameLength = (mp3File.getInterpreter() + " - " + mp3File.getTitle()).length();
				if(songNameLength > maxSongNameLength) {
					maxSongNameLength = songNameLength;
				}
			}
		}
		// Print genre, song and filename
		for (Mp3File mp3File : mp3FileList) {
			if(genreFilter == null || genreFilter.equals(mp3File.getGenre())) {
				String songName = (mp3File.isTag()) ? mp3File.getInterpreter() + " - " + 
						mp3File.getTitle(): "";
				System.out.printf("%-" + maxGenreLength + "s   ", mp3File.getGenre());
				System.out.printf("%-" + maxSongNameLength + "s   ", songName);
				System.out.println("(" + mp3File.getFile().getName() + ")");
			}
		}
	}
}