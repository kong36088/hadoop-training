package cn.jwlchina.eletric;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by JIANGWEILONGGGG on 2017/11/1.
 */
public class PriceReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int maxavg = 30, val;

        for (IntWritable value : values) {
            if ((val = value.get()) > maxavg) {
                context.write(key, new IntWritable(val));
            }
        }
    }
}
