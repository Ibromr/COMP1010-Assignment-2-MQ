package stage2;

import doNotModify.Review;
import stage1.Artist;

public class Song {
	public String name;
	public int length;
	public Artist artist;
	public Review[] reviews;

	/**
	 * Set the instance variables using the passed parameters. This constructor
	 * should use a reference copy when setting any objects.
	 * 
	 * @param name
	 *            for name
	 * @param length
	 *            for length
	 * @param artist
	 *            for artist
	 */
	public Song(String name, int length, Artist artist, Review[] reviews) {
		// TODO To be completed
		this.name = name;
		this.length = length;
		this.artist = artist;
		this.reviews = reviews;
	}
	
	/**
	 * @return the average rating across all song reviews
	 */
	public double averageRating() {
		// TODO To be completed
		
	
		
		double sum = 0;
		
		for(int i=0; i<reviews.length; i++) { // Summing rating in reviews
			sum += reviews[i].rating;    	
		}								
		
		return sum/reviews.length; 		// Average rating
		
	}

	/**
	 * 
	 * @param other
	 *            the object to compare against
	 * @return 1 if the calling object has a larger length, -1 if the calling
	 *         object has a smaller length, 0 if the lengths are the same.
	 */
	public int compareTo(Song other) {
		// TODO To be completed
		if(this.length > other.length) {  // is calling object this.length ?
			return 1;
		}
		
		else if(this.length < other.length) {
			return -1;
		}
		
		else {
			return 0;
		}
	}

	/**
	 * @return the string representation of the calling object. Refer to the
	 *         unit tests for the exact format to output.
	 * 
	 *         HINT: To make a new line, you should use the \n character in a
	 *         string.
	 */
	public String toString() {
		// TODO To be completed
		
		
		return "Song name: "+name +"\n"+"length: "+length+"s"+"\n"+artist;
	}

}
