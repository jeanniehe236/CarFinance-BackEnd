package carfinance.server.responseGenerator;

public class GrammarHelper {
	public static String getArticle(String str) {
		return isVowel(str.charAt(0)) ? "an" : "a";
	}
	
	private static boolean isVowel(char c) {
		return c =='a'||c =='e'||c=='i'||c=='o'||c=='u'||c=='A'||c=='E'||c=='I'||c=='O'||c=='U';
	}

}
