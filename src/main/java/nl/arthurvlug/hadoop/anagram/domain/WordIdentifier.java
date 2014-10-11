package nl.arthurvlug.hadoop.anagram.domain;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.apache.hadoop.io.WritableComparable;

@NoArgsConstructor(access=AccessLevel.PRIVATE)
public class WordIdentifier implements Comparable<WordIdentifier>, WritableComparable<WordIdentifier> {
	@Getter
	private final Integer[] countArray = new Integer[26];
	
	public WordIdentifier(String word) {
		for(int i = 0; i < 26; i++) {
			countArray[i] = 0;
		}
		for(int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if(c <= 'Z') {
				// make lowercase
				c = (char) (c - 'A' + 'a');
			}
			countArray[c - 'a']++;
		}
	}
	
	@Override
	public String toString() {
		return Arrays.toString(countArray);
	}
	
	@Override
	public int hashCode() {
		return Arrays.hashCode(countArray);
	}
	
	@Override
	public boolean equals(Object obj) {
		WordIdentifier wordIdentifier = (WordIdentifier) obj;
		boolean equals = Arrays.equals(countArray, wordIdentifier.countArray);
		return equals;
	}

	@Override
	public int compareTo(WordIdentifier o) {
		return WordIdentifierUtils.compare(countArray, o.countArray);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		for (int i = 0; i < 26; i++) {
			countArray[i] = in.readInt();
		}
	}

	@Override
	public void write(DataOutput out) throws IOException {
		for (int i = 0; i < 26; i++) {
			out.writeInt(countArray[i]);
		}
	}
}
