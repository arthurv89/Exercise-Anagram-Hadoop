package nl.arthurvlug.hadoop.anagram.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


public class AnagramGrouper {
	List<AnagramGroup> group(List<String> words) {
		Map<WordIdentifier, AnagramGroup> anagramGroups = new HashMap<WordIdentifier, AnagramGroup>();
		
		for(String word : words) {
			if(word == null || !StringUtils.isAlpha(word)) {
				continue;
			}
			
			AnagramGroup anagramGroup = anagramGroup(anagramGroups, word);
			if(anagramGroup == null) {
				anagramGroup = new AnagramGroup(word);
				anagramGroups.put(anagramGroup.getWordIdentifier(), anagramGroup);
			} else {
				anagramGroup.add(word);
			}
		}
		
		return new ArrayList<AnagramGroup>(anagramGroups.values());
	}

	private AnagramGroup anagramGroup(Map<WordIdentifier, AnagramGroup> anagramGroups, String word) {
		return anagramGroups.get(new WordIdentifier(word));
	}
}
