package de.chrcho.mp3reader.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import de.chrcho.mp3reader.model.ID3v1GenreFinder;
import de.chrcho.mp3reader.model.Mp3File;
import de.chrcho.mp3reader.util.FileHandler;

/**
 * Handler for mp3 files.
 * 
 * @author Chrcho
 */
public class Mp3FileHandler implements FileHandler {

	private final static String MP3_FILE_EXTENSION = ".mp3";
	private final static Charset MP3_TAG_CHARSET = StandardCharsets.ISO_8859_1;
	private final static String MP3_ID3_TAG = "TAG";
	private final static int MP3_ID3V1_LENGTH = 128;

	private List<Mp3File> mp3FileList;

	public Mp3FileHandler() {
		this.mp3FileList = new ArrayList<Mp3File>();
	}

	/**
	 * Get mp3 file list after directory scan.
	 * 
	 * @return List of mp3 files
	 */
	public List<Mp3File> getMp3FileList() {
		return mp3FileList;
	}

	/**
	 * Handle file in directory.
	 */
	@Override
	public void handle(File file) {
		if (file.getName().toLowerCase().endsWith(MP3_FILE_EXTENSION)) {
			Mp3File mp3File;
			try {
				// Create mp3 file with tag
				mp3File = this.createMp3File(file);
			} catch (IOException e) {
				// Skip this file
				System.out.println(e.getMessage());
				return;
			}
			// Create mp3 file list
			mp3FileList.add(mp3File);
		}
	}

	/**
	 * Create mp3 file with some ID3v1 information (title, interpreter, genre).
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private Mp3File createMp3File(File file) throws IOException {
		Mp3File mp3File = new Mp3File(file);
		if (mp3File.getFile().length() >= MP3_ID3V1_LENGTH) {
			this.mapID3v1ToMp3File(mp3File, this.getID3v1(mp3File));
		}
		return mp3File;
	}

	/**
	 * Get ID3v1 tag of mp3 file as byte array.
	 * 
	 * @param mp3File
	 *            Check tag in this file
	 * @return ID3v1 tag as byte array
	 * @throws IOException
	 *             Input output error (skip this file)
	 */
	private byte[] getID3v1(Mp3File mp3File) throws IOException {
		byte[] id3v1 = new byte[MP3_ID3V1_LENGTH];
		// Get ID3v1 tag of mp3 file
		FileInputStream fileInputStream = new FileInputStream(mp3File.getFile());
		fileInputStream.skip(mp3File.getFile().length() - MP3_ID3V1_LENGTH);
		fileInputStream.read(id3v1);
		fileInputStream.close();
		return id3v1;
	}

	/**
	 * Map ID3v1 to mp3 file if tag exists.
	 * 
	 * @param mp3File Add ID3v1 to this mp3 file
	 * @param id3v1 ID3v1 as byte array
	 */
	private void mapID3v1ToMp3File(Mp3File mp3File, byte[] id3v1) {
		mp3File.setTag(this.createString(id3v1, 0, 3).equals(MP3_ID3_TAG));
		if (mp3File.isTag()) {
			mp3File.setTitle(this.createString(id3v1, 3, 30));
			mp3File.setInterpreter(this.createString(id3v1, 33, 30));
			mp3File.setGenre(ID3v1GenreFinder.find(new Byte(id3v1[MP3_ID3V1_LENGTH - 1]).intValue()));
		}
	}

	/**
	 * Create string by bytes with default charset and trim it.
	 * 
	 * @param bytes The bytes to be decoded into characters
	 * @param offset The index of the first byte to decode
	 * @param length The number of bytes to decode
	 * @return Created string of subarray of bytes
	 */
	private String createString(byte[] bytes, int offset, int length) {
		String string = new String(bytes, offset, length, MP3_TAG_CHARSET);
		string = string.trim();
		return string;
	}
}