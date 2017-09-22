import java.io.*;
import java.util.*;

public class Solution3 extends Twothree {

    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      System.out.println("Please enter your input");
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	String input = reader.readLine();
    	int num_input = Integer.parseInt(input);
    	Solution3 tree = new Solution3();
    	TwoThreeTree root = new TwoThreeTree();


    	for (int i = 0; i < num_input; i++){
    		input = reader.readLine();
    		String [] words = input.split(" ");
        //if first is 1, insert
        if ()

        //if first is 2, range update

        //if first is 3, return entrance fee from planet

        //add to database
    		tree.insert(words[0], Integer.parseInt(words[1]), root);
    	}

      input = reader.readLine();
      num_input = Integer.parseInt(input);
      for (int i = 0; i < num_input; i++){
        input = reader.readLine();
        String [] words = input.split(" ");
        rangeFind(root, words[0], words[1]);
      }
    }

    static void rangeFind(TwoThreeTree tree, String start, String end){
      int h = tree.height;

      if (h == -1){
        return;
      }
      else{
        if (start.compareTo(end) > 0 ){
          String tmp = start;
          start = end;
          end = tmp;
        }
        doRangeFind(tree.root, start, end, h);
      }
    }

    static void doRangeFind (Node p, String start, String end, int h){
      if (h == 0) {
         // we're at the leaf level

         LeafNode leaf = (LeafNode) p;
         int cmp = end.compareTo(leaf.guide);

         if (cmp >= 0) {

            System.out.println(leaf.guide + ' ' + leaf.value);
         }
      }
      else {
         InternalNode q = (InternalNode) p;
         int pos;

         if (start.compareTo(q.child0.guide) <= 0) {
            
            doRangeFind(q.child0, start, end, h-1);
         }
         if (start.compareTo(q.child1.guide) <= 0) {
          
            doRangeFind(q.child1, start, end, h-1);
         }
         if (q.child2 != null && start.compareTo(q.child2.guide) <= 0) {
       
            doRangeFind(q.child2, start, end, h-1);
         }
      }
    }
}