package nl.arthurvlug.hadoop.anagram;

import org.apache.hadoop.io.Text;

public class TextArrayWritableUtils {
	public static int compare(TextArrayWritable taw1, TextArrayWritable taw2) {
		if(taw1.get().length != taw2.get().length) {
			return taw1.get().length - taw2.get().length;
		}
		
		for (int i = 0; i < taw1.get().length; i++) {
			Text v1 = (Text) taw1.get()[i];
			Text v2 = (Text) taw2.get()[i];

			int diff = v1.compareTo(v2);
			if(diff != 0) {
				return diff;
			}
		}
		return 0;
	}
}
