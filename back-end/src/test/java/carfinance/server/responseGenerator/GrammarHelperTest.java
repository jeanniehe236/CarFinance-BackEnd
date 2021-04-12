package carfinance.server.responseGenerator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GrammarHelperTest {
	
	
	@ParameterizedTest
	@DisplayName("Test article for lowercase words starting with a vowel.")
	@ValueSource(strings = {"apple", "elephant", "ion", "orange", "uncle"}) // six numbers
	void testArticleForLowerCaseWordsStartingWithVowel(String str) {
	    assertEquals("an", GrammarHelper.getArticle(str), 
	    		String.format("The article for an %s should be 'an'.", str));
	}
	
	@ParameterizedTest
	@DisplayName("Test article for lowercase words starting with a vowel.")
	@ValueSource(strings = {"bottle", "ceiling", "dog", "fish",
			"girl", "heaven", "jelly", "kick", "laugh", "mouth", "number", "pay",				
			"queen", "red", "sing", "tick", "view", "wonder", "xylan", "yellow", "zebra"})
	void testArticleForLowerCaseWordsStartingWithAConsonant(String str) {
		assertEquals("a", GrammarHelper.getArticle(str), 
	    		String.format("The article for a %s should be 'a'.", str));
	}
	
	@ParameterizedTest
	@DisplayName("Test article for lowercase words starting with a vowel.")
	@ValueSource(strings = {"Apple", "Elephant", "Ion", "Orange", "Uncle"}) // six numbers
	void testArticleForCapitalizedWordsStartingWithVowel(String str) {
	    assertEquals("an", GrammarHelper.getArticle(str), 
	    		String.format("The article for an %s should be 'an'.", str));
	}
	
	@ParameterizedTest
	@DisplayName("Test article for lowercase words starting with a vowel.")
	@ValueSource(strings = {"Bottle", "Ceiling", "Dog", "Fish",
			"Girl", "Heaven", "Jelly", "Kick", "Laugh", "Mouth", "Number", "Pay",				
			"Queen", "Red", "Sing", "Tick", "View", "Wonder", "Xylan", "Yellow", "Zebra"})
	void testArticleForCapitalizedWordsStartingWithAConsonant(String str) {
		assertEquals("a", GrammarHelper.getArticle(str), 
	    		String.format("The article for a %s should be 'a'.", str));
	}
}
