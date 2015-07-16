package edu.missouri.hadoop.CalculateAttributes;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordReducer extends Reducer<Text, DoubleWritable, Text, Text> {

	@Override
	public void reduce(Text key, Iterable<DoubleWritable> values,
			Context context) throws IOException, InterruptedException {
		double sumVal = 0;
		double val = 0;
		double maxVal = -Double.MAX_VALUE;
		double minVal = Double.MAX_VALUE;
		double meanVal = 0;
		int count = 0;
		Iterator<DoubleWritable> it = values.iterator();

		while (it.hasNext()) {
			val = it.next().get();
			sumVal += val;
			count += 1;
			if (val > maxVal) {
				maxVal = val;
			}
			if (val < minVal) {
				minVal = val;
			}

		}
		meanVal = sumVal / count;
		double tempSum = 0;
		while (it.hasNext()) {
			val = it.next().get();
			tempSum += (val - meanVal) * (val - meanVal);
		}
		double sigma = Math.sqrt((tempSum / count));
		int w = 1;
		double upperBound = meanVal + w * sigma;
		double lowerBound = meanVal - w * sigma;
		context.write(key, new Text("maxVal:" + maxVal + " minVal:" + minVal
				+ " count:" + count + " sum:" + sumVal + " mean:" + meanVal
				+ " sigma:" + sigma + " upper bound:" + upperBound
				+ " lower bound:" + lowerBound));
	}
}
