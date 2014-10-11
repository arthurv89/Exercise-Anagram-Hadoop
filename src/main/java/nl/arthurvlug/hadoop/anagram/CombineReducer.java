package nl.arthurvlug.hadoop.anagram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nl.arthurvlug.hadoop.anagram.domain.WordIdentifier;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CombineReducer extends Reducer<WordIdentifier, Text, TextArrayWritable, NullWritable> {
	
	@Override
	protected void reduce(WordIdentifier key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		List<Text> list = new ArrayList<Text>();
		for(Text t : values) {
			list.add(new Text(t.toString()));
		}
		Text[] array = list.toArray(new Text[list.size()]);
		TextArrayWritable textArrayWritable = new TextArrayWritable(array);
		context.write(textArrayWritable, NullWritable.get());
	}
}
