package Flashcards;

import java.util.HashSet;
import java.util.Set;

public class QuestionAugmentations {
	private Question question;
	private Set<String> tags;
	private Set<String> comments;
	
	public QuestionAugmentations(Question q)
	{
		question = q;
		tags = new HashSet<String>();
		comments = new HashSet<String>();
	}
	
	public void AddTag(String t)
	{
		tags.add(t);
	}
	
	public void AddComment(String c)
	{
		tags.add(c);
	}
	
	public Question GetQuestion()
	{
		return question;
	}
	
	public Set<String> GetTags()
	{
		return tags;
	}
	
	public Set<String> GetComments()
	{
		return comments;
	}
}
