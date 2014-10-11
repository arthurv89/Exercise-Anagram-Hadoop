package nl.arthurvlug.hadoop.anagram.domain;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class AnagramGrouperTest {
	public AnagramGrouper anagramGrouper = new AnagramGrouper();

	@Test
	public void testAnagramGrouper() {
		List<String> words = Arrays.asList("cba", "aCb");
		List<AnagramGroup> expectedGroups = Arrays.asList(new AnagramGroup("aCb", "cba"));

		List<AnagramGroup> actualGroups = anagramGrouper.group(words);
		sort(actualGroups);
		sort(expectedGroups);

		assertEquals(expectedGroups, actualGroups);
	}

	@Test
	public void testAnagramGrouperBigger() {
		List<String> words = Arrays.asList("xx", "ab", " ", null, "!", "cba", "ac", "aCb", "\u20ac", "XX");
		List<AnagramGroup> expectedGroups = Arrays.asList(new AnagramGroup("aCb", "cba"), new AnagramGroup("ab"), new AnagramGroup("ac"), new AnagramGroup("xx", "XX"));

		List<AnagramGroup> actualGroups = anagramGrouper.group(words);
		sort(actualGroups);
		sort(expectedGroups);

		assertEquals(expectedGroups, actualGroups);
	}

	private void sort(List<AnagramGroup> expectedGroups) {
		Collections.sort(expectedGroups, new Comparator<AnagramGroup>() {
			@Override
			public int compare(AnagramGroup o1, AnagramGroup o2) {
				return WordIdentifierUtils.compare(o1.getWordIdentifier().getCountArray(), o2.getWordIdentifier().getCountArray());
			}
		});
	}
}
