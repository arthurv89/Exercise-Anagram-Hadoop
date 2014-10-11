package nl.arthurvlug.hadoop.anagram;

import java.io.IOException;

import nl.arthurvlug.hadoop.anagram.domain.WordIdentifier;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AnagramGrouper extends Configured implements Tool {
    @Override
    public int run(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = getConf();
        
        Job job = new Job(conf);
        job.setJarByClass(AnagramGrouper.class);

        job.setMapperClass(AnagramGrouperMapper.class);
        job.setMapOutputKeyClass(WordIdentifier.class);
        job.setMapOutputValueClass(Text.class);
        
        job.setReducerClass(CombineReducer.class);
        job.setOutputKeyClass(TextArrayWritable.class);
        job.setOutputValueClass(NullWritable.class);
        
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        return (job.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new AnagramGrouper(), args);
        System.exit(res);
    }
}
