package com.acadgild;
/*
 * Write a program that finds all anagram sets from a list of words. Anagrams are those words in
 * which there are same characters jumbled. For example -> GOD - DOG ARE ANAGRAMS. Both words
 * have same characters but in jumbled order.
 * 
 * Input: listen, pot, part, opt, trap, silent, top, this, hello, hits, what, shit
 *  
 * Output: { listen, silent } { pot, opt, top } { part, trap } { this, hits, shit }
 */
public class Anagrams {
	/*
	 * Strategy is to find anagrams of the first word (by comparing to all words after it)
	 * Remove anagrams from the pool of words.
	 * And Append a new StringBuffer with word and found anagram(s) (according to format)
	 * Then...
	 * Repeat same process for all next non-removed words till the end...
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String input = "listen, pot, part, opt, trap, silent, top, this, hello, hits, what, shit";
		
		//Storing words into a String[] for easier comparison 
		String[] inputArray = input.split(", ");
		
		int aCount=0;
		/*
		 * Strings will be added at many times as the output is being prepared.
		 * StringBuffer will be better than String for efficiency.
		 */
		StringBuffer output = new StringBuffer("");
		
		for(int i=0; i<inputArray.length; i++){
			/*
			 * As anagrams in the array are replaced with "", they must be skipped
			 * during future comparisons.
			 */
			if(inputArray[i].isEmpty())continue; 
			//Compare word at 'i' to all words after it
			for(int j=i+1; j<inputArray.length; j++){
								
				if( isAnagram(inputArray[i],inputArray[j]) ){
					aCount++;//Tracks whether or not this is the first found anagram
					
					if(aCount == 1)//If first found anagram this format is required
						output.append("{ "+inputArray[i]);

					//for every found anagram, this format is required
					output.append(", "+inputArray[j]);
					inputArray[j] = "";//Remove word by replacing with Empty String
				}
			}
			/*
			 * At this point, if anagrams were found, aCount will be more than 0, which
			 * means the end of the anagram list must be formatted
			 */
			if(aCount>0){
				output.append(" } ");
				aCount=0;
			}
		}
		
		//Displaying input and output
		System.out.println("Input: "+input);
		System.out.println("\nOutput: "+output.toString());

	}
	
	/*
	 * To check whether 2 strings are anagrams.
	 */
	private static boolean isAnagram(String a, String b){
		//It's impossible for words to be anagrams if one is longer than the other
		if(a.length()!= b.length()) return false;
		
		//using StringBuffers because of String manipulation (delete chars from words)
		StringBuffer aSB = new StringBuffer(a);
		StringBuffer bSB = new StringBuffer(b);
		
		//Every char from 'a' will be compared
		for(int i=0; i<aSB.length(); i++){
			/*
			 * Every remaining char from 'b' will be compared to char at current index
			 * of aSB
			 */
			for(int j=0; j<bSB.length(); j++){
				
				//Comparing chars from both StringBuffers
				if(aSB.charAt(i) == bSB.charAt(j)){
					/*
					 * If there is a match, it must be deleted. This will prevent
					 * an invalid match if 'String a' has recurring char(s)
					 */
					bSB.deleteCharAt(j);
					//needed to avoid multiple deletions based on recurring char(s)
					break;
				}
			}
			/*
			 * After comparing char of 'String a' to all remaining chars of 'String b'
			 * we must make sure there was a match. If there was a match, the length of
			 * bSB should be exactly (i+1) less than aSB.
			 * 
			 * (i) accounts for the fact that at every matched iteration, bSB must
			 * delete a matched char.
			 * 
			 * (+1) accounts for the iteration at i=0;
			 * 
			 */
			if((aSB.length()-bSB.length()) != i+1)
				return false;
		}
		//If not proven false by this point, Strings a and b are anagrams
		return true;
		
	}

}
