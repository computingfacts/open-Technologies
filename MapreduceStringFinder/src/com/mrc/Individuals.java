/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mrc;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapred.InputSplit;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.JobConfigurable;
import org.apache.hadoop.mapred.RecordReader;
import org.apache.hadoop.mapred.Reporter;

/**
 *
 * This class extends FileInputFormat which is a base class for file-based .
 * it  override the isSplitable(FileSystem, Path)} method to ensure input-files are
 * not split-up and are processed as a whole by  Mappers.
 *
 * Text is the input file to be processed
 * Population is the candidates of solution to evolve
 *
 * @author Joseph Onwubiko
 */
public class Individuals extends FileInputFormat<Text, Population> implements JobConfigurable  {



    // A CompressionCodecFactory object that will find the correct codec for a given filename
    private CompressionCodecFactory compressionCodecs = null;

    public void configure(JobConf conf) {
        compressionCodecs = new CompressionCodecFactory(conf);

    }

    @Override
   public RecordReader<Text, Population> getRecordReader(InputSplit split, JobConf job, Reporter reporter) throws IOException {
           reporter.setStatus(split.toString());
    return new IndividualReader(job, (FileSplit)split);// returns a byte oriented view of the file
    }



}
