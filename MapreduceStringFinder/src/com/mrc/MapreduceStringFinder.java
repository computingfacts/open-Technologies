/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrc;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Logger;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BooleanWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;

import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.lib.MultithreadedMapRunner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.PopulationData;

/**
 *this class extends configured so that it can be configurable with this {@link Configuration}.
 * it also implements the tool interface that supports handling of generic command-line options
 *
 * @author Joseph Onwubiko
 */
public class MapreduceStringFinder extends Configured implements Tool {

    private static final Logger log = Logger.getLogger("ComputationalIntelligence");// logging

    /**
     *   This is the Mapper class that executes the map task
     *  @param key in = Text - this is the input file
     *  @param value in = Population - candidates of solutions
     * @param key out = Text - best found solutions and other evolution informations
     * @param value out = BooleanWritable - true if solution is found or false if no solution found
     */
    public static class CIMapper extends MapReduceBase implements Mapper<Text, Population, Text, BooleanWritable> {

        private static int popsize; // population size
        private String solution; // best solution found
        private String result; // evolution result
        private boolean found = false;
        private static String data; // input data (problem)
        private static Population population;

        /**
         *  passing data and popsize so that they would be available before a call to the mapper
         * @param job this mapreduce job
         */
        @Override
        public void configure(JobConf job) {

            popsize = job.getInt("popsize", popsize);
            data = job.get("dataset", data);

            new Population(data, popsize);

        }

        /**
         *
         * @param key the input file
         * @param value population of candiates
         * @param output output to reducer
         * @param reporter for reporting purposes
         * @throws IOException
         */
        @Override
        public void map(Text key, Population value, OutputCollector<Text, BooleanWritable> output, Reporter reporter) throws IOException {
            try {

                try {
                   
                    /**
                     * evolve the population of candidates of size (popsize) untill there is a match to the key (problem)
                     */
                    solution = Population.evolveSolution(key.toString(), popsize, Population.generatedpopulation);

                    /**
                     * return the result of the best solution found
                     */
                    result = EvolutionStalker.populationUpdates(EvolutionStalker.output);

                    /**
                     * if solution found, output the best solution to reducer
                     */
                    if (solution.contentEquals(key.toString())) {
                        found = true; // set found to true
                        result = EvolutionStalker.populationUpdates(EvolutionStalker.output);
                        output.collect(new Text(result), new BooleanWritable(found));// emit best solution to reducer

                    } else {

                        result = EvolutionStalker.populationUpdates(EvolutionStalker.output);
                        output.collect(new Text(result), new BooleanWritable(found));

                    }
                } catch (NumberFormatException e) {
                    log.warning("Unable to parse key," + key + " " + value + "" + e);

                    // return;

                }


            } catch (Throwable e) {
                log.severe("unexpected exception in mapper for key," + "value " + key + "," + value + "" + e);


                if (e instanceof IOException) {
                    throw (IOException) e;
                }
                if (e instanceof RuntimeException) {
                    throw (RuntimeException) e;
                }
                throw new IOException("unknown Exception occured", e);
            }


        }
    }

    /**
     * this class observes the evolution process
     */
    public static class EvolutionStalker implements EvolutionObserver<String> {

        private static PopulationData<? extends String> output;

        /**
         *
         * @param data Statistics about the state of the current generation
         * @return final statistics of the generation of solutions
         */
        public static String populationUpdates(PopulationData<? extends String> data) {

            String time = getElapsedTime(data.getElapsedTime()); //converting the time to H:M:SS:Mil

            EvolutionStalker.output = data;

            return "Gen: " + data.getGenerationNumber() + " |" + "fitness: " + data.getBestCandidate() + " |" + "pop size" + data.getPopulationSize() + " | " + "Time" + time + "|" + "Time (Millisec)" + data.getElapsedTime();

        }

        /**
         * @return the output
         */
        public PopulationData<? extends String> getOutput() {
            return output;
        }

        /**
         * @param output the output to set
         */
        public void setOutput(PopulationData<? extends String> output) {
            EvolutionStalker.output = output;
        }

        // for printing purpose
        public void populationUpdate(PopulationData<? extends String> data) {
            EvolutionStalker.output = data;
            // System.out.println(" Gen: "+ data.getGenerationNumber() + " |" +"fitness: "+ data.getBestCandidate() +" |"+ "pop size"+ data.getPopulationSize() + " | "+ "Time"+ data.getElapsedTime());
        }
    }

    /**
     * @param Text (key in) =  input key from the mapper
     * @param BooleanWritable = input value from mapper
     * @param Text (key out) = output to HDFS
     * @param NullWritable for presentation purpose - output nothing
     */
    public static class CIReducer extends MapReduceBase implements Reducer<Text, BooleanWritable, Text, NullWritable> {

        private boolean matches = false;

        public void reduce(Text key, Iterator<BooleanWritable> values, OutputCollector<Text, NullWritable> output, Reporter reporter) throws IOException {


            // iterates through the list for a true value and output the associated key
            while (values.hasNext()) {
                boolean found = !matches == values.next().get();
                if (found == true) {

                    output.collect(key, NullWritable.get());// output to Hadoop file system
                } else {

                    output.collect(new Text("Solution Not Found!!"), NullWritable.get());
                }
            }



        }
    }

    public int run(String[] args) throws Exception {
        if (args.length != 3) {
            System.err.println("Usage: " + getClass().getName() + " <input> <output> <nPopulation>");
            // ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }


        // Create a JobConf using the processed <code>conf</code>
        final JobConf jobConf = new JobConf(getConf(), getClass());


        // Specify various job-specific parameters
        jobConf.setJobName(MapreduceStringFinder.class.getSimpleName());

        // setting the input format
        jobConf.setInputFormat(Individuals.class);

        // setting the output ke and value class
        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(BooleanWritable.class);

        // setting the mapper class
        jobConf.setMapperClass(CIMapper.class);
        jobConf.setNumMapTasks(3);//setting number of maptasks
        

        // setting the reducer class
        jobConf.setReducerClass(CIReducer.class);

       
        //setup input/output directories
        final String dataset = args[0];

        FileInputFormat.setInputPaths(jobConf, new Path(dataset));
        FileOutputFormat.setOutputPath(jobConf, new Path(args[1]));
        final int pop = Integer.parseInt(args[2]);

        // based on the configuration, make this job threadable
        if (jobConf.getInt("mapred.tasktracker.map.tasks.maximum", 2) == 1) {
            jobConf.setMapRunnerClass(MultithreadedMapRunner.class);
            jobConf.setInt("mapred.map.multithreadedrunner.threads", 100);
        }
        jobConf.setInt("mapred.map.multithreadedrunner.threads", 100);
        // for computation intensive data, do not allow the job to fail if the task tracker does not respond
        // with a heatbeat message before the timeout value
        final int timeout = 9000000;
        jobConf.setInt("mapred.task.timeout", timeout);

        // set the parameters to be available before a call to the mapper
        jobConf.setInt("popsize", pop);
        jobConf.setStrings("dataset", dataset);


        // int map = jobConf.getNumMapTasks();
        // System.out.println("Number of Maps"+ map);

        //start the  map/reduce job
        System.out.println("Starting Job");

        // get the start time for this job
        final long startTime = System.currentTimeMillis();


        // Submit the job, then poll for progress until the job is complete
        JobClient.runJob(jobConf);

        // get the end time for this job
        final long endTime = System.currentTimeMillis();

        // get the duration of this job
        final double duration = (endTime - startTime) / 1000.0;
        //System.out.println("Job Finished in " + duration + " seconds");
        //getElapsedTime(startTime - endTime);

        return 0;

    }

    /**
     *
     * @param milli the system time in milli
     * @return the time elasped in this format Hours:minutes:seconds:milli
     */
    private static String getElapsedTime(long milli) {
        long seconds = milli / 1000;
        milli %= 1000;

        long minutes = seconds / 60;
        seconds %= 60;

        long hours = minutes / 60;
        minutes %= 60;

        // log.info("Elapsed time (Hours:minutes:seconds:milli) : "+ hours + ":"+ minutes +":"+ seconds + ":"+ milli);
        return hours + ":" + minutes + ":" + seconds + ":" + milli;
    }

    /**
     * main method for running the mapreduce application.
     */
    public static void main(String[] args) throws Exception {
        // Let <code>ToolRunner</code> handle generic command-line options
        System.exit(ToolRunner.run(null, new MapreduceStringFinder(), args));
    }
}
