package nl.arthurvlug.hadoop.anagram.domain;

public class WordIdentifierUtils {
	public static int compare(Integer[] countArray, Integer[] countArray2) {
		for(int i = 0; i < 26; i++) {
			Integer v1 = countArray[i];
			Integer v2 = countArray2[i];
			
			if(v1 != v2) {
				return v2 - v1;
			}
		}
		return 0;
	}
}
