package nl.arthurvlug.hadoop.anagram;

import java.io.IOException;

import nl.arthurvlug.hadoop.anagram.domain.WordIdentifier;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AnagramGrouperMapper extends Mapper<LongWritable, Text, WordIdentifier, Text> {
	@Override
	protected void map(LongWritable key, Text tWord, Context context) throws IOException, InterruptedException {
		String word = tWord.toString();
		WordIdentifier wordIdentifier = new WordIdentifier(word);
		context.write(wordIdentifier, tWord);
	}
}
