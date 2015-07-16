package edu.missouri.hadoop.CalculateAttributes;

import java.io.IOException;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMapper extends Mapper<Object, Text, Text, DoubleWritable> {
	
	
	@Override
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
		String attributes[]=value.toString().split(",");
		context.write(new Text("open"), new DoubleWritable(Double.parseDouble(attributes[1])));
		context.write(new Text("high"), new DoubleWritable(Double.parseDouble(attributes[2])));
		context.write(new Text("low"), new DoubleWritable(Double.parseDouble(attributes[3])));
		context.write(new Text("close"), new DoubleWritable(Double.parseDouble(attributes[4])));
		context.write(new Text("volumn"), new DoubleWritable(Double.parseDouble(attributes[5])));
		
	}
}
