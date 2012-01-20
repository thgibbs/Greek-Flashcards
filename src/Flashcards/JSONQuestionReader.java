package Flashcards;

import java.io.*;
import org.json.*;

public class JSONQuestionReader {
	private JSONArray root;
	private int currentIdx = 0;
	public JSONQuestionReader(String filename) throws UnsupportedEncodingException, FileNotFoundException, IOException, JSONException
	{
		this(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
	}
	public JSONQuestionReader(Reader r) throws IOException, JSONException
	{
		BufferedReader reader = new BufferedReader(r);
		String line;
		StringBuilder builder = new StringBuilder(2048);
		while ((line = reader.readLine()) != null)
		{
			builder.append(line);
			builder.append(" ");
		}
		root = new JSONArray(builder.toString());
	}
	public QuestionAugmentations ReadQuestion() throws JSONException
	{
		if (currentIdx >= root.length())
		{
			return null;
		}
		JSONObject question = root.getJSONObject(currentIdx);
		JSONArray term = question.getJSONArray("term");
		JSONArray defn = question.getJSONArray("definition");
		Question q = new Question(term.getString(0), CreateAnswer(defn));
		QuestionAugmentations qa = new QuestionAugmentations(q);
		JSONArray tags = question.getJSONArray("tags");
		for(int i = 0; i < tags.length(); ++i)
		{
			qa.AddTag(tags.getString(i));
		}
		++currentIdx;
		return qa;
	}
	
	private String CreateAnswer(JSONArray defn) throws JSONException {
		StringBuilder builder = new StringBuilder(2048);
		builder.append(defn.getString(0));
		for (int i = 1; i < defn.length(); ++i)
		{
			builder.append(',');
			builder.append(defn.getString(i));
		}
		return builder.toString();
	}
}
