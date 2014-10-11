package nl.arthurvlug.hadoop.anagram.domain;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class WordIdentifierTest {
	@Test
	public void testSameWord() {
		WordIdentifier wordIdentifier = new WordIdentifier("aCb");
		WordIdentifier wordIdentifier2 = new WordIdentifier("cba");
		assertArrayEquals(wordIdentifier.getCountArray(), wordIdentifier2.getCountArray());
	}
}
