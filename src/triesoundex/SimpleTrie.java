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

public class SimpleTrie {

    private static final char[] NUMBERS = {'1', '2', '3', '4', '5', '6'};
    public static int SIZE = indexOf('Z') + 9;
    private Map<String, Integer> map = null;
    private SimpleTrie[] tries = null;

    private static int indexOf(char letter) {
        int res = -1;
        if (letter == '\'') {
            res = 'Z' + 1 - 'A';
        } else if (Character.digit(letter, 10) >= 0) {
//            System.out.println("digit : " + Character.digit(letter, 10));
            res = Character.digit(letter, 10) + 'Z' + 1 - 'A';
//            return Character.digit(letter, 10);
        } else {
            res = (int) (letter - 'A');
        }
//        System.out.println("size of array : " + SIZE);
//        System.out.println("returning index value of [" + letter + "] as [" + res + "]");
        return res;
    }

    public void put(String word, String data) {
//        System.out.println("word is: " + word);
        if (word.isEmpty()) {

            if (this.map == null) {
                this.map = new HashMap();
            }
//                System.out.println("created hashmap");
            int oldVal = map.getOrDefault(data, 0);
            this.map.put(data, oldVal + 1);

        } else {
            char letter = word.charAt(0);

            int index = indexOf(letter);
//            System.out.println("letter: " + letter + " index: " + index);
            if (tries == null) {
                tries = new SimpleTrie[SIZE];
            }
            try {
                if (tries[index] == null) {
                    tries[index] = new SimpleTrie();
                }
            } catch (Exception e) {
                e.printStackTrace();
//                System.out.println(e.getLocalizedMessage() + "word [" + word + "] data [" + data + "]");
            }
            SimpleTrie trie = tries[index];
            trie.put(word.substring(1), data);
        }

    }

    public Map get(String word) {
//        System.out.println("sdex val is " + word);
        if (word.isEmpty()) {
            return map;
        }
        if (tries == null) {
            return null;
        }
        char letter = word.charAt(0);
        int index = indexOf(letter);
        SimpleTrie trie = tries[index];
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
            for (SimpleTrie trie : tries) {
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
