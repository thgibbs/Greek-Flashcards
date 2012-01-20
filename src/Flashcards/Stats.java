package Flashcards;

import java.util.HashMap;
import java.util.Map;

public class Stats {
	class Pair {
		public int first;
		public int second;
		public Pair(int f, int s)
		{
			first = f;
			second = s;
		}
	}
	private int total;
	private int correct;
	private Map<String, Pair> byTag;
	
	public Stats()
	{
		total = 0;
		correct = 0;
		byTag = new HashMap<String, Pair>();
	}
	
	public void Add(QuestionAugmentations qa, boolean right)
	{
		++total;
		if (right)
		{
			++correct;
		}
		for (String tag : qa.GetTags())
		{
			if (byTag.containsKey(tag))
			{
				Pair toUpdate = byTag.get(tag);
				toUpdate.first++;
				if (right)
				{
					toUpdate.second++;
				}
			}
			else
			{
				byTag.put(tag, new Pair(1, right ? 1 : 0));
			}
		}
	}
	
	public int GetTotalQuestions() 
	{
		return total;
	}
	
	public int GetCorrectAnswers()
	{
		return correct;
	}
	
	public Map<String, Pair> GetTagInfo()
	{
		return byTag;
	}
}
