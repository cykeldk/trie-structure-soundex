/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triesoundex;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mulle
 */
public class Deleteme {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String pathToHere = System.getProperty("user.dir");
        System.out.println("current path: " + pathToHere);
        System.out.println(Soundex.sdex("shit"));
        String[] shakeWords = {"me", "you", "other", "othher", "you", "them"};

        try {
            //        char letter = 'a';
//        System.out.println("index of " + letter + " is " + PerfectHashExamples.indexOf('a'));

            shakeWords = FileUtility.toStringArray("src/deleteme/shakespeare.txt", "[^A-Za-z']+");
            
        } catch (IOException ex) {
            System.out.println("File Error");
            ex.printStackTrace();
        }
        SimpleTrie trie = new SimpleTrie();

        for (String w : shakeWords) {
            String sdex = Soundex.sdex(w);
            trie.put(sdex, w);
//            System.out.println(sdex + " " + w);
        }
        for (String w : shakeWords) {
            Map shit = trie.get(Soundex.sdex(w));
            System.out.println(w + ": ");
            for (Object key : shit.keySet()) {
                System.out.println("\t" + key + " " + shit.get(key));
            }
            

        }

    }

}
