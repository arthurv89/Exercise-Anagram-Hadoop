package nl.arthurvlug.hadoop.anagram.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class AnagramGroup {
	@Getter
	private final WordIdentifier wordIdentifier;
	private final List<String> words;


	public AnagramGroup(String... aWords) {
		words = new ArrayList<String>(Arrays.asList(aWords));
		wordIdentifier = new WordIdentifier(words.get(0));
	}


	public List<String> values() {
		return words;
	}
	
	public void add(String word) {
		words.add(word);
	}
	
	@Override
	public String toString() {
		return wordIdentifier + ": " + words;
	}
	
	@Override
	public boolean equals(Object obj) {
		AnagramGroup anagramGroup = (AnagramGroup) obj;
		return wordIdentifier.equals(anagramGroup.getWordIdentifier());
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(wordIdentifier.getCountArray());
	}
}
