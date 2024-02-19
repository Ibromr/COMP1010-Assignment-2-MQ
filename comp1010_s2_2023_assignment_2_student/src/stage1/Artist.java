package stage1;

public class Artist {
	public String name;

	/**
	 * Set the instance variables using the passed parameters
	 * 
	 * @param n
	 *            for name
	 */
	public Artist(String n) {
		// TODO To be completed
		name = n;  
	}

	/**
	 * 
	 * @param s
	 * @return true if the calling objects name is valid, false otherwise. A
	 *         valid name must start with an upper case letter and be at least 2
	 *         characters long.
	 */
	public boolean validName() {
		// TODO To be completed
		
		if(name.length()>2 && Character.isUpperCase(name.charAt(0))) {  //Checking if all conditions is true
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 * @param other
	 *            the artist to compare against
	 * @return true if the calling object and parameter object have the same
	 *         name.
	 */
	public boolean equals(Artist other) {
		// TODO To be completed
		
		if(this.name != other.name) return false;		
			
		return true;
	}

	/**
	 * @return the string representation of the calling object. Refer to the
	 *         unit tests for the exact format to output.
	 */
	public String toString() {
		// TODO To be completed
		
		return "Artist: " +name;
	}

}
