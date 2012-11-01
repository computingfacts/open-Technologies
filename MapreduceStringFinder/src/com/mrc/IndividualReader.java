/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrc;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.LineRecordReader;
import org.apache.hadoop.mapred.RecordReader;

/**
 *
 * <code>IndividualReader</code> reads &lt;Text, Population&gt; pairs from an InputSplit
 * 
 * <p><code>RecordReader</code>, typically, converts the byte-oriented view of
 * the input, provided by the <code>InputSplit</code>, and presents a
 * record-oriented view for the {@link Mapper} & {@link Reducer} tasks for
 * processing. It thus assumes the responsibility of processing record
 * boundaries and presenting the tasks with keys and values.</p>
 *
 * @author Joseph Onwubiko
 */
public class IndividualReader implements RecordReader<Text, Population> {

    private LineRecordReader lineReader;//A class that provides a line record reader from an input stream.
    private LongWritable lineKey;//A WritableComparable for longs to handle each line of the input as a key
    private Text lineValue;// the value of the input
    private int popsize; // population size
    private String splitsize;
    String[] data2process ;

    public IndividualReader(JobConf job, FileSplit split) throws IOException {
        lineReader = new LineRecordReader(job, split); // read the split as a record

        lineKey = lineReader.createKey(); //create key from the input
        lineValue = lineReader.createValue();// create value from the population object
        splitsize = job.get("dataset", splitsize);
        popsize = job.getInt("popsize", popsize);
        
    }

    /**
     *
     * @param key the input file
     * @param value the population of solutions
     * @return true if there is next key value pair or false if none
     * @throws IOException
     */
    public boolean next(Text key, Population value) throws IOException {

    
        // get the next line
        if (!lineReader.next(lineKey, lineValue)) {
            return false;
        }

        // parse the lineValue which is in the format: Population
         List<String> record = Population.generatedpopulation;
        for(String task : record){

       data2process = lineValue.toString().split(task, popsize);
      // System.out.println("the task :"+ task);
        }

       

        if (data2process.length != 1) {
            throw new IOException("Invalid record received");
        }



        // overwrite the output objects

       // Text is the output key.
        key.set(data2process[0].trim());

        Population.generatedpopulation = record;
        value.setInitialPopulation(data2process[0].trim());



        return true;

    }

    /**
     *
     * @return the input ras the key
     */
    public Text createKey() {
        return new Text("");

    }

    /**
     *
     * @return new population of solutions
     */
    public Population createValue() {


        return new Population(Population.getProblem(), Population.getPopsize());
    }

    /**
     *
     * @return position of the line reader
     * @throws IOException
     */
    public long getPos() throws IOException {
        return lineReader.getPos();

    }

    /**
     * close the line reader
     * @throws IOException
     */
    public void close() throws IOException {
        lineReader.close();//close the line reader

    }

    /**
     *
     * @return progress of the line reader
     * @throws IOException
     */
    public float getProgress() throws IOException {
        return lineReader.getProgress();

    }
}
