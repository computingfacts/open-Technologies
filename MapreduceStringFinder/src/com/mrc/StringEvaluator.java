/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mrc;

import java.util.List;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 * Evaluates strings and assigns a fitness score based on how many characters
 * differ from the equivalent positions in a given target string.
 * @author Daniel Dyer
 */
public class StringEvaluator implements FitnessEvaluator<String> {

    private final String targetString;

    /**
     * Creates a {@link FitnessEvaluator} that calculates scores
     * for Strings based on how close they are to a target String.
     * @param targetString The target of the evolution.
     */
    public StringEvaluator(String targetString) {
        this.targetString = targetString;
    }

    /**
     * Assigns one "penalty point" for every character in the candidate
     * string that differs from the corresponding position in the target
     * string.
     * @param candidate The evolved string to evaluate.
     * @param population {@inheritDoc}
     * @return The fitness score (how many characters are wrong) of the
     * specified string.
     */
    public double getFitness(String candidate,
            List<? extends String> population) {
        // int errors = 0;
        int matches = 0;
        for (int i = 0; i < candidate.length(); i++) {
            if (candidate.charAt(i) != targetString.charAt(i)) {

                ++matches;
            }
        }

        return matches;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isNatural() {
        return false;
    }
}
