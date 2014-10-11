package nl.arthurvlug.hadoop.anagram.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AnagramGroupComparatorTest {
	private AnagramGroupComparator anagramGroupComparator = new AnagramGroupComparator();

	@Test
	public void simpleTest() {
		WordIdentifier wordIdentifier1 = new WordIdentifier("a");
		WordIdentifier wordIdentifier2 = new WordIdentifier("b");
		int compare = anagramGroupComparator.compare(wordIdentifier1, wordIdentifier2);
		assertEquals(-1, compare);
	}

	@Test
	public void simpleReverseTest() {
		WordIdentifier wordIdentifier1 = new WordIdentifier("b");
		WordIdentifier wordIdentifier2 = new WordIdentifier("a");
		int compare = anagramGroupComparator.compare(wordIdentifier1, wordIdentifier2);
		assertEquals(1, compare);
	}
	
	@Test
	public void sameCharactersTest() {
		WordIdentifier wordIdentifier1 = new WordIdentifier("ab");
		WordIdentifier wordIdentifier2 = new WordIdentifier("ba");
		int compare = anagramGroupComparator.compare(wordIdentifier1, wordIdentifier2);
		assertEquals(0, compare);
	}
	
	@Test
	public void countBiggerThanOneTest() {
		WordIdentifier wordIdentifier1 = new WordIdentifier("abc");
		WordIdentifier wordIdentifier2 = new WordIdentifier("bcaa");
		int compare = anagramGroupComparator.compare(wordIdentifier1, wordIdentifier2);
		assertEquals(1, compare); // aabc before abc
	}
	
	@Test
	public void noASameWordTest() {
		WordIdentifier wordIdentifier1 = new WordIdentifier("bb");
		WordIdentifier wordIdentifier2 = new WordIdentifier("bb");
		int compare = anagramGroupComparator.compare(wordIdentifier1, wordIdentifier2);
		assertEquals(0, compare);
	}
	
	@Test
	public void noAOtherWordTest() {
		WordIdentifier wordIdentifier1 = new WordIdentifier("bb");
		WordIdentifier wordIdentifier2 = new WordIdentifier("cc");
		int compare = anagramGroupComparator.compare(wordIdentifier1, wordIdentifier2);
		assertEquals(-2, compare);
	}
	
	@Test
	public void noSubsetWordTest() {
		WordIdentifier wordIdentifier1 = new WordIdentifier("abc");
		WordIdentifier wordIdentifier2 = new WordIdentifier("ac");
		int compare = anagramGroupComparator.compare(wordIdentifier1, wordIdentifier2);
		assertEquals(-1, compare);
	}
}
