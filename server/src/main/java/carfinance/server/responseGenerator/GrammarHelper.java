package carfinance.server.responseGenerator;

/**
 * A grammar helper to return the correct article 
 * for a certain word.
 *
 */
public class GrammarHelper {
	
	/**
	 * Finds the article for a word through a naive approach: by checking 
	 * whether the first character is a vowel.
	 * @param word: the word to be checked
	 * @return the article for the given word.
	 */
	public static String getArticle(String word) {
		return isVowel(word.toLowerCase().charAt(0)) ? "an" : "a";
	}
	
	private static boolean isVowel(char c) {
		return c =='a'||c =='e'||c=='i'||c=='o'||c=='u';
	}

}
