/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrc;




import com.mrc.MapreduceStringFinder.EvolutionStalker;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.hadoop.io.Writable;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.operators.StringCrossover;
import org.uncommons.watchmaker.framework.operators.StringMutation;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

/**
 *This class implements Writable so that it's fields can be Serialized
 *
 * @author Joseph Onwubiko
 *
 */
public class Population implements Writable {

    public static Candidates candidates;// candidates of solutions
    private static final int elites = 5; // elitism
    public static List<String> generatedpopulation; // a list of populations
    private static int popsize; // population size
    private static String problem; // the problem
    private Random random;
    public static char[] ALPHABET = new char[27]; // alphabeth characters

    static {
        for (char c = 'a'; c <= 'z'; c++) {
            ALPHABET[c - 'a'] = c;
        }
        ALPHABET[26] = ' ';
    }

    /**
     *
     * @param problem the target string
     * @param psize population size
     */
    public Population(String problem, int psize) {

        Population.popsize = psize;
        Population.problem = problem;
        random = new Random();
        random.setSeed(psize);
        candidates = new Candidates(ALPHABET, problem.length());
        generatedpopulation = candidates.generateInitialPopulation(popsize, new ArrayList<String>(), random);

    }

    /**
     *
     * @param psize population size
     */
    public Population(int psize) {
        Population.popsize = psize;
        random = new Random();
        random.setSeed(psize);
        candidates = new Candidates(ALPHABET, getProblem().length());
        generatedpopulation = candidates.generateInitialPopulation(popsize, new ArrayList<String>(), random);
    }

    /**
     *
     * @param out to serialize this object into
     * @throws IOException
     */
    public void write(DataOutput out) throws IOException {
        List<String> initialpopulation = candidates.generateInitialPopulation(popsize, new ArrayList<String>(), random);
        for (String individuals : initialpopulation) {
            out.writeBytes(individuals);
        }
    }

    /**
     *
     * @param in to deseriablize this object from
     * @throws IOException
     */
    public void readFields(DataInput in) throws IOException {
        List<String> initialpopulation = candidates.generateInitialPopulation(popsize, new ArrayList<String>(), random);
        for (String individuals : initialpopulation) {
            individuals = in.readLine();
        }
    }

    /**
     * @return the problem
     */
    public static String getProblem() {
        return problem;
    }

    /**
     * @param aProblem the problem to set
     */
    public static void setProblem(String aProblem) {
        problem = aProblem;
    }

    /**
     * @return the popsize
     */
    public static int getPopsize() {
        return popsize;
    }

    /**
     * @param aPopsize the popsize to set
     */
    public static void setPopsize(int aPopsize) {
        popsize = aPopsize;
    }

    /**
     *
     * @param problem the target string to find
     * @param popsize the population size
     * @param candidates the candidates of solutions
     * @return the best solution found
     */
    public static String evolveSolution(String problem, int popsize, List<String> generatedpopulation) {
        candidates = new Candidates(ALPHABET, problem.length());// pass the alphabeth and problem to the candidate factory
        

        //perform genetic operations, cross over and mutation using Evolutionary operator
        //An evolutionary operator is a function that takes a population of candidates as an argument
        //and returns a new population that is the result of applying a transformation to the original population
        List<EvolutionaryOperator<String>> operators = new ArrayList<EvolutionaryOperator<String>>(2);
        operators.add(new StringMutation(ALPHABET, new Probability(0.02d)));
        operators.add(new StringCrossover());

        //Creates a pipeline consisting of the specified operators in the order that they are supplied
        EvolutionaryOperator<String> pipeline = new EvolutionPipeline<String>(operators);

        /** Creates a new evolution engine by specifying the various components required by a generational evolutionary algorithm
         * candidates - Factory used to create the initial population that is iteratively evolved.
         * pipeline - The combination of evolutionary operators used to evolve the population at each generation.
         * StringEvaluator(problem) - A function for assigning fitness scores to candidate solutions.
         * RouletteWheelSelection() - A strategy for selecting which candidates survive to be evolved.
         * MersenneTwisterRNG() - The source of randomness used by all stochastic processes (including evolutionary operators and selection strategies).
         */
        EvolutionEngine<String> engine = new GenerationalEvolutionEngine<String>(candidates,
                pipeline,
                new StringEvaluator(problem),
                new RouletteWheelSelection(),
                new MersenneTwisterRNG());


        engine.addEvolutionObserver(new EvolutionStalker());// add an observer to display the evolution  process
        String result = engine.evolve(popsize,  elites,generatedpopulation, new TargetFitness(0, false)); // terminate once a candidate equals to the problem

        return result;// return the best solution found
    }
    @Override
    public String toString(){
        return generatedpopulation.toString();
    }

    public void setInitialPopulation(String in){
        for(String i: generatedpopulation)
            in = i;
        
    }
}

