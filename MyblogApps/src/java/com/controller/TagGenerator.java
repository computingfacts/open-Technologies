/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mcavallo.opencloud.Cloud;
import org.mcavallo.opencloud.Cloud.Case;
import org.mcavallo.opencloud.Tag;
import org.mcavallo.opencloud.filters.DictionaryFilter;
import org.mcavallo.opencloud.filters.Filter;

/**
 *
 * @author josephonwubiko
 */
public class TagGenerator {

    private static final Double MAX_WEIGHT = 38.0;
    private static final Integer MAX_DISPLAY = 10;
   // private static final String PROP_FILE = "dictionary_blacklist.properties";
    private static Filter<Tag> filter = new DictionaryFilter(ResourceBundle.getBundle("com.controller.dictionary_blacklist"));

    public static void main(String[] args) throws IOException {
        //String data = " this is the java tag and java in hotel of the hotel";
        String text = null;
        InputStream file = null;
        //Tag t = new Tag(data,"www.yhoo.com");

        try {
             //file = new FileInputStream(new File("text.txt"));
            //InputStream in = new FileInputStream(new File("text.txt"));
           // BufferedReader bufRead = new BufferedReader(new InputStreamReader(in));
             FileReader reader = new FileReader("text.txt");
             BufferedReader bufRead = new BufferedReader(reader);
           // text = bufRead.readLine();
            if (reader != null) {
                
                text = bufRead.readLine();
                 //System.out.println("files are "+ text);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TagGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
        // System.out.println("files are "+ buffer.toString());
        String l = " blogging";
        String link = String.format("www.myblog.jsp/?title=%s", l); //"www.google.com/?title=%s";
        Cloud cloud = new Cloud(Case.PRESERVE_CASE, Locale.ENGLISH);
        cloud.setMaxWeight(MAX_WEIGHT);
        cloud.setTagCase(Case.UPPER);
        cloud.setDefaultLink("http://www.flickr.com/photos/tags/%s/");
        cloud.setMaxTagsToDisplay(MAX_DISPLAY);
       // Pattern wordBreakPattern = Pattern.compile("[\\p{Punct}\\s}]");
       
 //System.out.println(text);

        //cloud.setWordPattern("[A-Za-z0-9\\.\\@_\\-~#]+");
        
        cloud.addInputFilter(filter); 
        
        cloud.addText(text, link);
        
        //cloud.setThreshold(0.5); 
         cloud.tags(new Tag.NameComparatorAsc()); 
       // cloud.tags(new Tag.ScoreComparatorDesc());
       // cloud.tags();
       // ResourceBundle rb = ResourceBundle.getBundle("com.cloud.dictionary_blacklist", Locale.UK);
//        Enumeration keys = rb.getKeys();
//
//        while (keys.hasMoreElements()) {
//            System.out.println(keys.nextElement());
//        }
        //InputStream is = TagGenerator.class.getResourceAsStream(PROP_FILE);
        //Properties prop = new Properties();
       // prop.load(is);

        // Filter<Tag> filter = new DictionaryFilter(is);
        // DictionaryFilter filter = new DictionaryFilter(prop.stringPropertyNames());
        
       // filter.filter(cloud.allTags()); 
       // cloud.addInputFilter(filter); 
        cloud.addOutputFilter(filter); 
        // for(String s : filter.){
          //System.out.println(filter.accept(new Tag("in")));

        // }
                 
        
        for (Tag tag : cloud.tags()) {
            
            //System.out.println(tag.getLink() +" : "+ tag.getWeight() + ":  "+ tag.getName()); 
   
        }



    }
}
