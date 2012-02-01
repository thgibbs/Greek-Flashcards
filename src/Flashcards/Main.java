package Flashcards;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

public class Main {
	public static void main(String args[]) throws IOException, JSONException
	{
		boolean shouldFilter = false;
		String filter = "";
		if (args.length > 0)
		{
			shouldFilter = true;
			filter = args[0];
		}
		JSONQuestionReader reader = new JSONQuestionReader("QuestionFile.json");
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
		
		Stats answerStats = new Stats();
		for (QuestionAugmentations augments : questions)
		{
			if (shouldFilter && !augments.GetTags().contains(filter))
			{
				continue;
			}
			out.write(augments.GetQuestion().GetQuestion() + ": \n");
			out.flush();
			String answer = in.readLine();
			if (augments.GetQuestion().IsCorrect(answer))
			{
				answerStats.Add(augments, true);
				out.write("Correct\n");
				out.flush();
			}
			else
			{
				answerStats.Add(augments, false);
				out.write("Incorrect\n");
				out.write("You gave: " + answer);
				out.write("\n");
				out.write("Answer is one of: " + augments.GetQuestion().GetAnswer());
				out.write("\n");
				out.flush();
			}
		}
		out.write("You answered " + answerStats.GetCorrectAnswers() + " / " + answerStats.GetTotalQuestions() + " correctly.\n");
		for (Map.Entry<String, Stats.Pair> entry : answerStats.GetTagInfo().entrySet())
		{
			out.write("For tag [" + entry.getKey() + "] you answered " + entry.getValue().second + " / " + entry.getValue().first + " correctly\n");
		}
		out.flush();
	}
}
