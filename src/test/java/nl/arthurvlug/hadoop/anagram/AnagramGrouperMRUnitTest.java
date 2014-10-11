package nl.arthurvlug.hadoop.anagram;

import java.io.IOException;
import java.util.Arrays;

import nl.arthurvlug.hadoop.anagram.domain.WordIdentifier;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

public class AnagramGrouperMRUnitTest {
	private MapReduceDriver<LongWritable, Text, WordIdentifier, Text, TextArrayWritable, NullWritable> mapReduceDriver;
	private MapDriver<LongWritable, Text, WordIdentifier, Text> mapDriver;
	private ReduceDriver<WordIdentifier, Text, TextArrayWritable, NullWritable> reduceDriver;

	@Before
	public void setup() {
		Configuration conf = new Configuration();
		
		AnagramGrouperMapper mapper = new AnagramGrouperMapper();
		mapDriver = new MapDriver<LongWritable, Text, WordIdentifier, Text>();
		mapDriver.setMapper(mapper);
		mapDriver.setConfiguration(conf);
		
		CombineReducer reducer = new CombineReducer();
		reduceDriver = new ReduceDriver<WordIdentifier, Text, TextArrayWritable, NullWritable>();
		reduceDriver.setReducer(reducer);
		reduceDriver.setConfiguration(conf);
		
		mapReduceDriver = new MapReduceDriver<LongWritable, Text, WordIdentifier, Text, TextArrayWritable, NullWritable>();
		mapReduceDriver.setMapper(mapper);
		mapReduceDriver.setReducer(reducer);
		mapReduceDriver.setConfiguration(conf);
	}

	@Test
	public void testMapper() throws IOException {
		mapDriver.withInput(new LongWritable(1), new Text("abc"));
		mapDriver.withOutput(new WordIdentifier("cba"), new Text("abc"));
		mapDriver.runTest();
	}

	@Test
	public void testReducer() throws IOException {
		reduceDriver.withInput(new WordIdentifier("bca"), Arrays.asList(new Text("abc"), new Text("cba"), new Text("acb")));
		reduceDriver.withOutput(new TextArrayWritable(new Text[] {new Text("abc"), new Text("cba"), new Text("acb")}), NullWritable.get());
		reduceDriver.runTest();
	}

	@Test
	public void testMapReduce() throws IOException {
		mapReduceDriver.withInput(new LongWritable(1), new Text("abc"));
		mapReduceDriver.withInput(new LongWritable(2), new Text("cba"));
		mapReduceDriver.withInput(new LongWritable(3), new Text("xa"));
		mapReduceDriver.withOutput(new TextArrayWritable(new Text[] {new Text("abc"), new Text("cba")}), NullWritable.get());
		mapReduceDriver.withOutput(new TextArrayWritable(new Text[] {new Text("xa")}), NullWritable.get());
		mapReduceDriver.runTest();
	}
}
