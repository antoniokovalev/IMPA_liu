import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;


/**
 * Created by Anton on 2016-09-27.
 */
public class Main {


    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size_of_dict = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<String>> dictionary = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            ArrayList<String> list = new ArrayList<>();
            dictionary.add(list);
        }
        for (int i = 0; i < size_of_dict; i++) {
            String word = br.readLine();
            int word_length = word.length();
            dictionary.get(word_length-1).add(word);
        }
        String s ;
        while ((s = br.readLine()) != null) {
            String[] encrypt = s.split(" ");
      /*  for (int i = 0; i < encrypt.length; i++) {
            System.out.println(encrypt[i]);
        }*/
            ArrayList<ArrayList<String>> encr_buckets = new ArrayList<>();
            for (int i = 0; i < 16; i++) {
                ArrayList<String> list = new ArrayList<>();
                encr_buckets.add(list);
            }
            HashMap map = new HashMap();

            for (int i = 0; i < encrypt.length; i++) {
                int längd = encrypt[i].length();
                //System.out.println(encrypt[i]);
                if(check_duplicate(map, encrypt[i])) //This if is not necessary, it just removes duplicates of the same encr.word. It may be good if there are a lot duplicates.
                encr_buckets.get(längd - 1).add(encrypt[i]);
            }

            int longest = 0;
            // Find the first longest bucket
            for (int i = 15; i >= 0; i--) {
                if (!encr_buckets.get(i).isEmpty()) {
                    longest = i;
                    break;
                }
            }

            // Find the last shortest bucket(notice it can be the same as the longest bucket). Can there not be any bucket att all?
            int shortest = 0;
            for (int i = 0; i <= 15; i++) {
                if (!encr_buckets.get(i).isEmpty()) {
                    shortest = i;
                    break;
                }
            }


            HashMap<String, String> word_to_dict = new HashMap<>(26);
            HashMap<String, String> dict_to_word = new HashMap<>(26);
            word_to_dict.put("a", null);
            word_to_dict.put("b", null);
            word_to_dict.put("c", null);
            word_to_dict.put("d", null);
            word_to_dict.put("e", null);
            word_to_dict.put("f", null);
            word_to_dict.put("g", null);
            word_to_dict.put("h", null);
            word_to_dict.put("i", null);
            word_to_dict.put("j", null);
            word_to_dict.put("k", null);
            word_to_dict.put("l", null);
            word_to_dict.put("m", null);
            word_to_dict.put("n", null);
            word_to_dict.put("o", null);
            word_to_dict.put("p", null);
            word_to_dict.put("q", null);
            word_to_dict.put("r", null);
            word_to_dict.put("s", null);
            word_to_dict.put("t", null);
            word_to_dict.put("u", null);
            word_to_dict.put("w", null);
            word_to_dict.put("v", null);
            word_to_dict.put("x", null);
            word_to_dict.put("y", null);
            word_to_dict.put("z", null);

            dict_to_word.put("a", null);
            dict_to_word.put("b", null);
            dict_to_word.put("c", null);
            dict_to_word.put("d", null);
            dict_to_word.put("e", null);
            dict_to_word.put("f", null);
            dict_to_word.put("g", null);
            dict_to_word.put("h", null);
            dict_to_word.put("i", null);
            dict_to_word.put("j", null);
            dict_to_word.put("k", null);
            dict_to_word.put("l", null);
            dict_to_word.put("m", null);
            dict_to_word.put("n", null);
            dict_to_word.put("o", null);
            dict_to_word.put("p", null);
            dict_to_word.put("q", null);
            dict_to_word.put("r", null);
            dict_to_word.put("s", null);
            dict_to_word.put("t", null);
            dict_to_word.put("u", null);
            dict_to_word.put("v", null);
            dict_to_word.put("w", null);
            dict_to_word.put("x", null);
            dict_to_word.put("y", null);
            dict_to_word.put("z", null);
            boolean lika = true;
            for (int i = 0; i < 15; i++) {
                if (dictionary.get(i).isEmpty() && !encr_buckets.get(i).isEmpty()) {
                    lika = false;
                }
            }

            StringBuffer sBuffer = new StringBuffer("");
                if (solve(dictionary, encr_buckets, longest, word_to_dict, dict_to_word, longest, 0, shortest) && lika) {
                    for (int i = 0; i < encrypt.length; i++) {
                        String answer = encrypt[i];
                        for (int j = 0; j < answer.length(); j++) {
                            sBuffer.append(dict_to_word.get(Character.toString(answer.charAt(j))));
                            if (j == answer.length() - 1 && i != encrypt.length-1) {
                                sBuffer.append(" ");
                            }
                        }
                    }
                } else {
                for (int i = 0; i < encrypt.length; i++) {
                    String answer = encrypt[i];
                    for (int j = 0; j < answer.length(); j++) {
                        sBuffer.append("*");
                        if (j == answer.length() - 1 && i != encrypt.length-1) {
                            sBuffer.append(" ");
                        }
                    }
                }
            }
            System.out.println(sBuffer);
        }

    }

    public static boolean check_duplicate(HashMap map, String s){ // Function not necessary. See above when making buckets.
        double sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int ascii = (int) s.charAt(i);
             sum = sum + ascii * Math.pow(2,i);
        }
        if (map.containsKey(sum)) {
            return false;
        }
        else {
            map.put(sum,0);
            return true;
        }
    }

    public static boolean solve (ArrayList<ArrayList<String>> dictionary, ArrayList<ArrayList<String>> encrypt, int bucket_index, HashMap<String,String> word_to_dict, HashMap<String,String> dict_to_word, int encr_idx, int encr_start, int last_bucket){
        // lägg till en if-sats om att encr_idx bucket kan vara tom. I sådant fall, gå till nästa bucket osv. tills vi når bucket 0. OBS! nästa bucket kan också vara av storleken noll...
        //lägg till ett slutfall, när vi är på sista bucket och sista elemnetet och det passar
        boolean ok = false;
        if (encrypt.get(encr_idx).size() == 0 && encr_idx != last_bucket) { // the next bucket is empty but it is not the last bucket, then go to the next bucket.
            ok = solve(dictionary, encrypt, bucket_index-1, word_to_dict, dict_to_word, encr_idx-1, 0, last_bucket);
            return ok;
        }

        loop:for (int i = 0; i < dictionary.get(bucket_index).size(); i++) {
            String[] encr_mem = new String[dictionary.get(bucket_index).get(i).length()]; // to keep track of the new letters I insert instead of the null values in the dictionary.
            String[] dict_mem = new String[dictionary.get(bucket_index).get(i).length()]; // to keep track of the new letters I insert instead of the null values in the dictionary. Initially, all should be null.
            String encr_word = encrypt.get(encr_idx).get(encr_start);
            String dict_word = dictionary.get(bucket_index).get(i);
            if (match(encrypt.get(encr_idx).get(encr_start), dictionary.get(bucket_index).get(i), word_to_dict, dict_to_word, encr_mem, dict_mem)) {

                if (encr_idx == last_bucket && encrypt.get(encr_idx).size() == encr_start+1) {
                return true; //Vi har kommit till sista ordet och eftersom "match" returnerade true, har vi hittat den sista matchningen.
                }
                if (encr_start < encrypt.get(encr_idx).size()-1) {
                    ok = solve(dictionary, encrypt, bucket_index, word_to_dict, dict_to_word, encr_idx, encr_start+1, last_bucket);
                }
                else{
                    ok = solve(dictionary, encrypt, bucket_index-1, word_to_dict, dict_to_word, encr_idx-1, 0, last_bucket);        // the next encr_bucket can be empty....
                }

                if (ok) {
                    break loop;
                }

                if (!ok) {
                    remove_from_dictionary(encr_word, dict_word, word_to_dict, dict_to_word, encr_mem, dict_mem); // Remove the matching words in this iteration and continue with the new iteration.
                }
            }
            else{
                remove_from_dictionary(encr_word, dict_word, word_to_dict, dict_to_word, encr_mem, dict_mem);
                if (i == dictionary.get(bucket_index).size()-1) {
                    ok =false;
                    break loop;
                }
            }
        }
        return ok;

    }


    public static void remove_from_dictionary(String encr_word, String dict_word, HashMap<String,String> word_to_dict, HashMap<String,String> dict_to_word, String[] encr_mem, String[] dict_mem){
        for (int i = 0; i < encr_word.length(); i++) {
            if (encr_mem[i] != null) {
                char e = encr_word.charAt(i);
                dict_to_word.put(Character.toString(e), null);//cannot just remove things here, it might have been added because there was a match, not because the letter was free(null)...same in next loop.
            }
        }
        for (int i = 0; i < dict_word.length(); i++) {
            if (dict_mem[i] != null) {
                char d = dict_word.charAt(i);
                word_to_dict.put(Character.toString(d), null);
            }
        }
    }

    public static boolean match(String encr_word, String dict_word, HashMap<String,String> word_to_dict, HashMap<String,String> dict_to_word, String[] encr_mem, String[] dict_mem ) {
        boolean match = true;
        loop: for (int i = 0; i < encr_word.length(); i++) { //spelar ingen roll vilken längd, båda orden är lika långa.
            String e = Character.toString(encr_word.charAt(i));
            String d = Character.toString(dict_word.charAt(i));
            if (dict_to_word.get(e) == null) { // om det är tomt på den platsen --> vi kan matcha.
                if (word_to_dict.get(d) == null) {
                    word_to_dict.put(d, e);
                    dict_to_word.put(e, d);
                    // Lägg till "bokstäverna i minnet"
                    dict_mem[i] = d;
                    encr_mem[i] = e;
                }
                else if (word_to_dict.get(d).equals(e)){
                    dict_mem[i] = d;
                }
                else{
                    match =false;
                    break loop;
                }
            }
            else if (dict_to_word.get(e).equals(d)) {
                if (word_to_dict.get(d) == null) {
                    word_to_dict.put(d, e);
                    // Lägg till "bokstäverna i minnet"
                    encr_mem[i] = e;
                }
                else if (word_to_dict.get(d).equals(e)){
                }
                else{
                    match = false;
                    break loop;
                }


            }

            else{
                match = false;
                break loop;
            }
        }
        return match;
    }




}
