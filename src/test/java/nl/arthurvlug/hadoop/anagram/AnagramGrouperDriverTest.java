package nl.arthurvlug.hadoop.anagram;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class AnagramGrouperDriverTest {
	private Configuration conf;
	private Path input;
	private Path output;
	private FileSystem fs;

	@Before
	public void setup() throws IOException {
		conf = new Configuration();
		conf.set("fs.default.name", "file:///");
		conf.set("mapred.job.tracker", "local");

		fs = FileSystem.getLocal(conf);
	}

	@Test
	public void testSmall() throws Exception {
		String datasetName = "small";
		input = new Path("src/test/resources/" + datasetName + "-input");
		output = new Path("target/" + datasetName + "-output/");
		fs.delete(output, true);
		
		AnagramGrouper anagramGrouper = new AnagramGrouper();
		anagramGrouper.setConf(conf);

		int exitCode = anagramGrouper.run(new String[] { input.toString(), output.toString() });
		assertEquals(0, exitCode);

		validateOuput(datasetName);
	}

	@Test
	public void testBig() throws Exception {
		String datasetName = "big";
		input = new Path("src/test/resources/" + datasetName + "-input");
		output = new Path("target/" + datasetName + "-output/");
		fs.delete(output, true);
		
		AnagramGrouper anagramGrouper = new AnagramGrouper();
		anagramGrouper.setConf(conf);

		int exitCode = anagramGrouper.run(new String[] { input.toString(), output.toString() });
		assertEquals(0, exitCode);

		validateOuput(datasetName);
	}

	private void validateOuput(String datasetName) throws IOException {
		InputStream in = null;
		InputStream inExpected = null;
		try {
			in = fs.open(new Path(output, "part-r-00000"));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			inExpected = fs.open(new Path("src/test/resources/" + datasetName + "-expected"));
			BufferedReader brExpected = new BufferedReader(new InputStreamReader(inExpected));
			
			String line;
			while((line = brExpected.readLine()) != null) {
				String expectedLine = br.readLine();
				assertEquals(expectedLine, line);
			}
		} finally {
			IOUtils.closeStream(in);
			IOUtils.closeStream(inExpected);
		}
	}
}
