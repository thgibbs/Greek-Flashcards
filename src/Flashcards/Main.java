package Flashcards;

import java.io.*;

public class Main {
	public static void main(String args[]) throws IOException
	{
		QuestionReader reader = new QuestionReader("QuestionFile.txt");
		QuestionAugmentations augments;
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);		
		while ((augments = reader.ReadQuestion()) != null)
		{
			System.out.println(augments.GetQuestion().GetQuestion() + ": ");
			String answer = in.readLine();
			if (augments.GetQuestion().IsCorrect(answer))
			{
				System.out.println("Correct");
			}
			else
			{
				System.out.println("Incorrect");
			}
		}
	}
}
