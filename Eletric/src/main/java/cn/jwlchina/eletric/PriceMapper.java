package cn.jwlchina.eletric;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by JIANGWEILONGGGG on 2017/11/1.
 */
public class PriceMapper extends Mapper
        <LongWritable,              /*Input key Type */
                Text,               /*Input value Type*/
                Text,               /*Output key Type*/
                IntWritable>        /*Output value Type*/ {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String lasttoken = "0";
        StringTokenizer s = new StringTokenizer(line, ",");
        String year = s.nextToken();

        while (s.hasMoreTokens()) {
            lasttoken = s.nextToken();
        }

        int avgprice = Integer.parseInt(lasttoken);
        context.write(new Text(year),new IntWritable(avgprice));
    }
}