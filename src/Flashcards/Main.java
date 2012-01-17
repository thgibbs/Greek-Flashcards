package Flashcards;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String args[]) throws IOException
	{
		QuestionReader reader = new QuestionReader("QuestionFile.txt");
		InputStreamReader converter = new InputStreamReader(System.in, "UTF-8");
		BufferedReader in = new BufferedReader(converter);	
		OutputStreamWriter oconv = new OutputStreamWriter(System.out, "UTF-8");
		BufferedWriter out = new BufferedWriter(oconv);
		List<QuestionAugmentations> questions = new ArrayList<QuestionAugmentations>();
		QuestionAugmentations qa;
		while ((qa = reader.ReadQuestion()) != null)
		{
			questions.add(qa);
		}
		java.util.Collections.shuffle(questions);
		for (QuestionAugmentations augments : questions)
		{
			out.write(augments.GetQuestion().GetQuestion() + ": \n");
			out.flush();
			String answer = in.readLine();
			if (augments.GetQuestion().IsCorrect(answer))
			{
				out.write("Correct\n");
				out.flush();
			}
			else
			{
				out.write("Incorrect\n");
				out.write("You gave: " + answer);
				out.write("\n");
				out.write("Answer is one of: " + augments.GetQuestion().GetAnswer());
				out.write("\n");
				out.flush();
			}
		}
	}
}
