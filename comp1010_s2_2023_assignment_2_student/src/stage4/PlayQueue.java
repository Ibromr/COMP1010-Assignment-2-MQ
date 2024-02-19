package stage4;

import doNotModify.SongLink;
import doNotModify.Review;
import stage1.Artist;
import stage2.Song;
import stage3.Playlist;


/*
 * For all questions below:
 * - You may assume the PlayQueue is in a valid state when each method is
 *   called.
 * 
 * - You must ensure that firstSong, lastSong, and nowPlaying remains in a
 *   valid state after the execution of each method.
 *   	- firstSong must refer to the first song link in the queue
 *      - lastSong must refer to the last song link in the queue
 *      - nowPlaying should refer to the currently playing song.
 *      
 * - To make life MUCH easier for yourself, draw diagrams on paper
 *   to see how your code affects the objects (instances AND references)
 *   
 *    Drawing diagram has statistically been proven to 
 *    assist in assignment success by 1729% (j/k, but true)
 */

public class PlayQueue {
	public SongLink nowPlaying; // The currently playing song link
	public SongLink firstSong; // The first song link in the play queue
	public SongLink lastSong; // The last song link in the play queue

	/**
	 * @return True if the queue is empty, false otherwise.
	 */
	public boolean isEmpty() {
		if (firstSong == null ) { 
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Set nowPlaying to the first song link in the queue
	 */
	public void startQueue() {


		nowPlaying = firstSong;


	}

	/**
	 * Add the passed song to the start of the PlayQueue. This method should use
	 * a reference copy when copying the song. You will need to make a new song
	 * link.
	 * 
	 * @param song
	 *            the song to add to the start of the PlayQueue
	 */
	public void addToStart(Song song) {

		SongLink temp = new SongLink(song, firstSong);
		firstSong = temp; 
		if(lastSong == null) {  //there is NO last song... this HAS TO BE the first song added
			lastSong = temp;
		}

	}

	/**
	 * Add the passed song to the end of the PlayQueue.
	 * 
	 * @param song
	 *            the song to add to the end of the PlayQueue
	 */
	public void addToEnd(Song song) {

		SongLink temp = new SongLink(song, null);
		if(lastSong != null) {
			lastSong.link = temp;
			lastSong = temp;
		}
		else { //
			firstSong = temp;
			lastSong = temp;
		}
	}

	/**
	 * Move the nowPlaying link forwards.
	 * 
	 * - If no song is being played, this function should set nowPlaying to the
	 * first link in the queue.
	 * 
	 * - If there are no songs / links remaining in the queue, nowPlaying should
	 * be set to null.
	 * 
	 * The new song should also be returned.
	 * 
	 * @return The next song in the queue
	 */
	public Song nextSong() {


		if (nowPlaying != null) { 

			nowPlaying = nowPlaying.link; //moving into next song in the queue
		} else {

			nowPlaying = firstSong; //if no playing song, setting nowPlaying to the first song
		}

		if(nowPlaying != null  ) {
			return nowPlaying.song;
		}

		return null; // return null if all these conditions do not meet
	}

	/**
	 * @param num
	 *            the song number to look for
	 * @return the song object associated with the number in the queue. The
	 *         first song in the queue is number 1, the second is number 2, and
	 *         so on. Return null if num does not represent a valid song in the
	 *         PlayQueue.
	 */
	public Song getSong(int num) {
		if(firstSong == null ) {
			return null;
		}

		//guaranteed num >= 1 and firstSong is not null

		if(num == 1) {	//return if num is 1
			return firstSong.song;
		}

		Song song;				// Creating return song if there is
		if(firstSong.link != null) { 
			song = getNode(firstSong, num); // Going into recursive function to find correct link
		} else {
			return null;
		}
		return song; 
	}

	public Song getNode(SongLink firstSong, int num) {
		if(firstSong != null && num == 1 ) {  // if true, Guaranteed num is equals to link number so it is the song number 
			return firstSong.song;			  // and it is not null.
		}
		else if (firstSong == null) {		// if the found firstSong link number is null
			return null;
		}
		firstSong = firstSong.link;
		return getNode(firstSong, num-1);
	}




	/**
	 * @return the total length of all songs in the PlayQueue from the start.
	 */
	public int totalPlaytime() {
		if(firstSong == null ) {
			return 0;
		}

		int total = 0;
		SongLink current = firstSong;	
		if(current.link != null) {
			total = getTotalPlaytime(current, total);		//Recursive function for summing total play time
		} else {
			return current.song.length;
		}


		return total;
	}

	public int getTotalPlaytime(SongLink current, int total) { //Recursive function for summing total play time

		if(current.link != null) {
			total += current.song.length;
		} 

		if(current.link == null) {
			total += current.song.length;
			return total;
		}

		current = current.link;
		return  getTotalPlaytime(current, total);
	}

	/**
	 * @return the total length of all songs in the PlayQueue from the currently
	 *         playing song (inclusive). If nowPlaying is null, return 0.
	 */
	public int remainingPlaytime() {
		if(nowPlaying == null) {
			return 0;
		}

		int remaining = 0;
		SongLink current = firstSong;	
		if(current.link != null) {
			current = nowPlaying;
			remaining = getremainingPlaytime(current, remaining);		
		} else {
			return current.song.length;
		}

		return remaining;
	}

	public int getremainingPlaytime(SongLink current, int remaining) { // Recursive function for summing remaining 
		//  play time.
		if(current.link != null) {
			remaining += current.song.length;
		} 

		if(current.link == null) {
			remaining += current.song.length;
			return remaining;
		}

		current = nowPlaying.link;
		return  getTotalPlaytime(current, remaining);
	}

	/**
	 * Add all songs from the playlist to the end of the PlayQueue
	 * 
	 * @param playlist
	 *            the playlist of songs to add from
	 */
	public void addToQueue(Playlist playlist) {

		Song[] makingSongs = playlist.songs;
		addingQueue(makingSongs, 0);

	}

	private void addingQueue(Song[] makingSongs, int index) {

		if(index>=makingSongs.length) {  // Base case
			return;
		}
		Song song = makingSongs[index];	            
		SongLink newLink = new SongLink(song, null);

		if (firstSong == null) { // If the queue is empty, set both first and last song to the new node	                
			firstSong = newLink;
			lastSong = newLink;
		} 
		else {  // Add the new node to the end of the queue	            
			lastSong.link = newLink;
			lastSong = newLink;
		}
		addingQueue(makingSongs, index + 1); // Recursive call for the next song

	}

	/**
	 * Add all songs from the playlist to the end of the PlayQueue. Only songs
	 * which have different names, lengths and artists should be added. The
	 * songs should be copied using a reference copy. In other words, only songs
	 * not already in PlayQueue should be added. You can ignore reviews.
	 * 
	 * @param playlist
	 *            the playlist of songs to add from
	 */
	public void addToQueueFiltered(Playlist playlist) {

		addingToQueueFiltered(playlist.songs, 0);
	}

	private void addingToQueueFiltered(Song[] playlistSongs, int idx) {
		
		if (idx >= playlistSongs.length) { // Base case
			return;
		}
			Song songToAdd = playlistSongs[idx];

			if (!isDuplicate(songToAdd, firstSong)) { // Only songs which have different names

				addToEnd(songToAdd);
			}

			addingToQueueFiltered(playlistSongs, idx + 1);
		
	}

	private boolean isDuplicate(Song song, SongLink current) {
		if (current == null) {
			return false;  // The song was not found in the PlayQueue
		}

		if (current.song.name.equals(song.name) &&
				current.song.length == song.length &&
				current.song.artist.equals(song.artist)) {
			return true;  // The song is already in the PlayQueue
		}
		
		return isDuplicate(song, current.link);  // Recursively check the next song link in the PlayQueue
	}

	/**
	 * Reverse the order of song links in the PlayQueue. The original SongLink
	 * objects should be reused i.e. you should not make any new objects. The
	 * firstSong and lastSong should be updated. nowPlaying should be retained.
	 */
	public void reversePlayQueue() {

		if (firstSong != null) {
			reversingPlayQueue(firstSong);
		}
	}

	private void reversingPlayQueue(SongLink current) {

		if (current.link == null) { // If the current song link is the last one, update firstSong
			firstSong = current;
			return;
		}	  
		reversingPlayQueue(current.link);  // Recursively reverse the rest of the links

		current.link.link = current; // Set the link of the current song link to the last song link
		current.link = null;

		lastSong = current; // Update lastSong after reversing
	}

	/**
	 * 
	 * @return a Playlist object containing all the songs in the PlayQueue, in
	 *         reversed order. The original list should not be modified.
	 */
	public Playlist reversed() {

		int countingSongs = countSongs(this.firstSong); // Use a helper method to count the songs
		Playlist playlistToReverse = new Playlist();
		playlistToReverse.songs = new Song[countingSongs];
		reversing(playlistToReverse, this.firstSong, countingSongs - 1);
		return playlistToReverse;
	}

	private int countSongs(SongLink current) {
		if (current == null) {
			return 0;
		}
		return 1 + countSongs(current.link);
	}
	
	private int reversing(Playlist playlist, SongLink current, int index) {
		if (current == null) {
			return index;
		}
		playlist.songs[index] = current.song;
		index--; // Decrement the index here
		return reversing(playlist, current.link, index);
	}

	/**
	 * Modify the PlayQueue to keep only the highest rated song by each distinct
	 * artist. The order of links should be based on the original order of the
	 * PlayQueue. YOU MUST NOT USE ANY ARRAYS IN YOUR SOLUTION. This will be
	 * manually checked after the assignment submission date.
	 * 
	 * For an extra challenge, complete this question while maintaining the
	 * original link objects associated with each song. The test
	 * "bestOfInPlace()" will check for this and is worth 0 marks.
	 */
	public void bestOf() {
		// TODO To be completed
	}

	public String toString() {
		/**
		 * DO NOT MODIFY
		 */
		return "PlayQueue:" + toString(firstSong, 1);
	}

	public String toString(SongLink link, int idx) {
		/**
		 * DO NOT MODIFY
		 */
		if(link == null)
			return "";
		return "\n--- Song #" + idx + "---\n" + link.song + toString(link.link, idx + 1);
	}
}
