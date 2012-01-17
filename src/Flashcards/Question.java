package Flashcards;

public class Question {
	private String question;
	private String answer;
	
	public Question(String q, String a)
	{
		question = q;
		answer = a;
	}
	
	public String GetQuestion()
	{
		return question;
	}
	
	public String GetAnswer()
	{
		return answer;
	}

	public boolean IsCorrect(String guess) {
		String[] answers = answer.split(",");
		for(String a : answers)
		{
			if (guess.equals(a)) 
			{
				return true;
			}
		}
		return false;
	}
}
