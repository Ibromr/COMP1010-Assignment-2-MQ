package stage3;

import stage1.Artist;
import stage2.Song;

public class Playlist {
	public Song[] songs;

	/**
	 * Count the number of Song objects which contain an artist with the exact
	 * same name as the string passed. If the passed object is null, then the
	 * method should return 0.
	 * 
	 * @param artistName
	 *            the name to check
	 * @return the number of songs with an artist whose name is equal to the
	 *         passed parameter
	 */
	public int countSongsBy(String artistName) {
		if(artistName == null) {
			return 0;
		}

		int count = 0;
		for(int i=0; i<songs.length; i++) {
			if(songs[i].artist.name.equals(artistName)) {
				count++;
			}

		}
		return count;
	}

	/**
	 * Add the passed song object to the end of the songs array. If the passed
	 * object is null, then the method should not modify the songs array. This
	 * method should make a reference copy of the passed song object.
	 * 
	 * @param song
	 *            the Song object to add
	 */
	public void addSong(Song song) {		// did not get the question
		if(song != null ) {
			Song[] song1 = new Song[songs.length+1];
			for(int i=0; i<songs.length; i++) {
				song1[i]=songs[i];
			}
			song1[song1.length-1] = song;
			songs = song1; 
		}
	}

	/**
	 * Remove all Song objects from the songs instance array which contain an
	 * artist with the exact same name as the string passed. If the passed
	 * object is null, then the method should not modify the songs array.
	 * 
	 * @param artistName
	 *            the artist name to search for
	 */
	public void removeAllSongsBy(String artistName) {
		if( artistName != null) {
			int idx = 0;
			for(int i=0; i<songs.length; i++) {
				if(songs[i].artist.name.equals(artistName)) {
					idx = i;
					remove(idx, artistName );
				}			 
			}
		}					
	}

	public int remove(int idx, String artistName) {
		if(idx < 0 || idx >= songs.length) {
			return idx; //error code
		}	

		// Create another array of size one less 
		Song[] anotherArray = new Song[songs.length - 1]; 

		// Copy the elements except the index 
		// from original array to the other array 
		for (int i = 0, k = 0; i < songs.length; i++) { 	

			// if the index is the removal element index 
			if (i == idx) { 
				continue; 
			} 

			// if the index is not the removal element index 
			anotherArray[k++] = songs[i]; 

		} 

		songs = anotherArray;

		// To make sure other indices are not the same using for loop and recursive operation

		for(int i=0; i<songs.length; i++) {    		
			if(songs[i].artist.name.equals(artistName)) {
				idx = i;
				remove(idx, artistName );
			}

		}

		return idx; 
	}

	/**
	 * DO NOT MODIFY
	 */
	public Playlist() {
		songs = new Song[0];
	}
}
