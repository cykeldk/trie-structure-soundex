/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triesoundex;

/**
 *
 * @author mulle
 */
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SoundexTrie {

    public static int SIZE = indexOf('Z') + 2;
    private Map<String, Integer> map;
    private SoundexTrie[] tries = null;
    private static char[] numbers = {'1', '2', '3', '4', '5', '6'};
    private static int indexOf(char letter) {
        if (letter == '\'') {
            return 'Z' + 1 - 'A';
        }
        
        else if (Arrays.binarySearch(numbers, letter)>=0){
            return Character.digit(letter, 10)+'Z'+2;
        }
            
            
        return (int) (letter - 'A');
    }

    public void put(String word, String data) {
//        System.out.println("word is: " + word);
        if (word.isEmpty()) {

            if (this.map == null) {
                this.map = new HashMap();
//                System.out.println("created hashmap");
                if (this.map.containsKey(data)) {
//                    System.out.println("key exists");
                    this.map.put(data, this.map.get(data) + 1);
                } else {
//                    System.out.println("added new key");
                    this.map.put(data, 1);
//                    System.out.println("data added " + data);

                }
            }
//            System.out.println("data added: " + data);
        } else {
            char letter = word.charAt(0);
            int index = indexOf(letter);
            if (tries == null) {
                tries = new SoundexTrie[SIZE];
            }
            try {
                if (tries[index] == null) {
                    tries[index] = new SoundexTrie();
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getLocalizedMessage() + "word [" + word + "] data [" + data + "]");
            }
            SoundexTrie trie = tries[index];
            trie.put(word.substring(1), data);
        }

    }

    public Map get(String word) {
        if (word.isEmpty()) {
            return map;
        }
        if (tries == null) {
            return null;
        }
        char letter = word.toUpperCase().charAt(0);
        int index = indexOf(letter);
        SoundexTrie trie = tries[index];
        if (trie == null) {
            return null;
        }
        return trie.get(word.substring(1));
    }

    public void print(PrintStream out) {
        if (map != null) {
            out.println(map);
        }
        if (tries != null) {
            for (SoundexTrie trie : tries) {
                if (trie == null) {
                    continue;
                }
                trie.print(out);
            }
        }
    }
//
//  public static void main(String[] args) {
//    SimpleTrie trie = new SimpleTrie();
//    trie.put("ANNA", "Anna");
//    trie.put("AND", "And");
//    trie.put("ANDERS", "Anders");
//    
//    System.out.println(trie.get("ANNA"));
//    System.out.println(trie.get("ANDERS"));
//    System.out.println(trie.get("ANDERSINE"));
//    
//    trie.print(System.out);
//    }

}
