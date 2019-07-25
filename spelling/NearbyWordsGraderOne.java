package spelling;

import java.util.List;

import java.util.ArrayList;
import java.io.PrintWriter;

public class NearbyWordsGraderOne {
    public static void main(String args[]) {
        int tests = 0;
        int incorrect = 0;
        String feedback = "";
        PrintWriter out;
       
        try {
            out = new PrintWriter("grader_output/module5.part1.out");
        } catch (Exception e) {
            e.printStackTrace();
           
            return;
        }

        try {
            Dictionary d = new DictionaryHashSet();
            DictionaryLoader.loadDictionary(d, "test_cases/dict.txt");
            NearbyWords nw = new NearbyWords(d);
//            System.out.println("done");
            List<String> d1 = nw.distanceOne("word", true);
//            System.out.println("done");
            feedback += "** Test 1: distanceOne list size... ";
            feedback += "distanceOne returned " + d1.size() + " words.\n";

            feedback += "** Test 2: distanceOne words returned... ";
            for (String i : d1) {
                feedback += i + ", ";
            }
//            System.out.println("done");
            feedback += "\n** Test 3: distanceOne list size (allowing non-words)... ";
            d1 = nw.distanceOne("word", false);
            feedback += "distanceOne with non-words returned " + d1.size() + " words.\n";
//            System.out.println("checked for false");
            d1 = new ArrayList<String>();
//            System.out.println("want for test4 ");
            feedback += "** Test 4: deletions list size... ";
            nw.deletions("makers", d1, true);
//            System.out.println("returned something");
            feedback += "deletions returned " + d1.size() + " words.\n";
//            System.out.println("4 completed and 5 is started ");
            feedback += "** Test 5: deletions words returned... ";
            feedback += "deletions returned: ";
            for (String i : d1) {
                feedback += i + ", ";
            }
//            System.out.println("5 ended and 6 started");
            d1 = new ArrayList<String>();

            feedback += "\n** Test 6: insertions list size... ";
            nw.insertions("or", d1, true);
            feedback += "insertions returned " + d1.size() + " words.\n";
//            System.out.println("7 started");
            feedback += "** Test 7: insertions words returned... ";
            feedback += "insertions returned: ";
            for (String i : d1) {
                feedback += i + ", ";
            }
            feedback += "\n";
//            System.out.println("done bro");
        } catch (Exception e) {
            out.println("Runtime error: " + e);
            return;
        }

        System.out.println(feedback + "Tests complete. Check that everything looks right.");
        out.close();
    }
}
