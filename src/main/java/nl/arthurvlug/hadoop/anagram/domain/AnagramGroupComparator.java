package nl.arthurvlug.hadoop.anagram.domain;

import java.util.Comparator;

public class AnagramGroupComparator implements Comparator<WordIdentifier> {
	@Override
	public int compare(WordIdentifier wordIdentifier1, WordIdentifier wordIdentifier2) {
		return WordIdentifierUtils.compare(wordIdentifier1.getCountArray(), wordIdentifier2.getCountArray());
	}
}
