package Flashcards;

import java.io.*;

public class QuestionReader {
	private BufferedReader reader;
	public QuestionReader(String filename) throws IOException
	{
		this(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
	}
	public QuestionReader(Reader r) throws IOException
	{
		reader = new BufferedReader(r);
	}
	
	public QuestionAugmentations ReadQuestion() throws IOException
	{
		String line = reader.readLine();
		if (line == null)
		{
			return null;
		}
		String[] fields = line.split("\\t");
		QuestionAugmentations augment = new QuestionAugmentations(new Question(fields[0], fields[1]));
		String[] tags = fields[2].split(",");
		for (String t : tags)
		{
			augment.AddTag(t);
		}
		return augment;
	}
}
