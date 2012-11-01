/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrc;

import java.util.Collection;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.factories.StringFactory;

/**
 *
 * @author Joseph Onwubiko
 * this class creates new populations of candidates
 */
public class Candidates implements CandidateFactory<String> {

    public char[] alphabet;
    public int stringLength;

    /**
     *
     * @param alpha alphabeth characters
     * @param length the length of the problem
     */
    public Candidates(char[] alpha, int length) {

        this.alphabet = alpha;
        this.stringLength = length;
    }

    /**
     *
     * @param pops population size (number of candidates to create).
     * @param ran random number generator to use when creating the initial candidates
     * @return initial population of candidate solutions
     */
    public List<String> generateInitialPopulation(int pops, Random ran) {
        ran.setSeed(pops);
        CandidateFactory factory = new StringFactory(getAlphabet(), getStringLength());
        return factory.generateInitialPopulation(pops, ran);
    }

    /**
     *
     * @param pops size of the initial population
     * @param seed Candidates to seed the population with
     * @param ran ran random number generator to use when creating the initial candidates
     * @return initial population of candidate solutions, including the specified seed candidates
     */
    public List<String> generateInitialPopulation(int pops, Collection<String> seed, Random ran) {
        seed = new ArrayList<String>();
        ran.setSeed(pops);
        CandidateFactory factory = new StringFactory(getAlphabet(), getStringLength());
        return factory.generateInitialPopulation(pops, seed, ran);
    }

    /**
     *
     * @param arg0 random number generator to use when creating the random candidate
     * @return randomly-initialised candidate
     */
    public String generateRandomCandidate(Random arg0) {
        CandidateFactory factory = new StringFactory(getAlphabet(), getStringLength());
        return factory.generateRandomCandidate(arg0).toString();
    }

    /**
     * @return the alphabet
     */
    public char[] getAlphabet() {
        return alphabet;
    }

    /**
     * @param alphabet the alphabet to set
     */
    public void setAlphabet(char[] alphabet) {
        this.alphabet = alphabet;
    }

    /**
     * @return the stringLength
     */
    public int getStringLength() {
        return stringLength;
    }

    /**
     * @param stringLength the stringLength to set
     */
    public void setStringLength(int stringLength) {
        this.stringLength = stringLength;
    }
}
