package nl.arthurvlug.hadoop.anagram;

import java.util.Arrays;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class TextArrayWritable extends ArrayWritable implements WritableComparable<TextArrayWritable> {
	private TextArrayWritable() {
		super(Text.class);
	}
	
	public TextArrayWritable(Text[] texts) {
		super(Text.class);
		Arrays.sort(texts);
		set(texts);
	}
	
	@Override
	public boolean equals(Object obj) {
		TextArrayWritable other = (TextArrayWritable) obj;
		return compareTo(other) == 0;
	}
	
	@Override
	public String toString() {
		return Arrays.toString(get());
	}

	@Override
	public int compareTo(TextArrayWritable other) {
		return TextArrayWritableUtils.compare(this, other);
	}
}
