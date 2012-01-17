package Flashcards;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class TestQuestion {
	
	@Test
	public void testMultiAnswer() 
	{
		Question q = new Question("singular masculine", "ς,ος");
		
		assertEquals("Checking Greek Text", true, q.IsCorrect("ς"));
		assertEquals("Checking Greek Text", true, q.IsCorrect("ος"));
	}
	
	@Test
	public void testReader()
	{
		String examples =
				"Σἰμων\tSimon\tbeginner,word,common,name,greek to english\n" +
				"nominative singular masculine\tς,ος\tbeginner,ending\n" +
				"nominative singular feminine\t-,η,α\tbeginner,ending\n" +
				"nominative singular neuter\tν,ον\tbeginner,ending\n";
		StringReader sr = new StringReader(examples);
		try {
			QuestionReader qr = new QuestionReader(sr);
			QuestionAugmentations qa = qr.ReadQuestion();
			assertEquals(qa.GetQuestion().GetQuestion(), "Σἰμων");
			assertEquals(qa.GetQuestion().GetAnswer(), "Simon");
			qa = qr.ReadQuestion();
			assertEquals(qa.GetQuestion().GetQuestion(), "nominative singular masculine");
			assertEquals(qa.GetQuestion().GetAnswer(), "ς,ος");
			assertTrue(qa.GetQuestion().IsCorrect("ς"));
		} catch (IOException e) {
			assertTrue(false);
		}
		
	}

}
