/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

/**
 *
 * @author joseph
 */
public class Testing {
    
    private String test;

    public Testing(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }
    public static void main(String []args){
        Testing testing = new Testing("ia m testing");
        System.out.println("print test "+ testing.getTest());
    }
    
}
