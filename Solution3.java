import java.io.*;
import java.util.*;

public class Solution3 extends Twothree {

    public static void main(String[] args) throws Exception{
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	String input = reader.readLine();
    	int num_input = Integer.parseInt(input);
    	Solution3 extendedTree = new Solution3();
    	TwoThreeTree tree = new TwoThreeTree();
      int query;


    	for (int i = 0; i < num_input; i++){
    		input = reader.readLine();
    		String [] words = input.split(" ");
        //if first is 1, insert
        query = Integer.parseInt(words[0]);
        if (query == 1){
          //insert
          extendedTree.insert(words[1], Integer.parseInt(words[2]), tree);
        }
        else if(query == 2){
          int value = Integer.parseInt(words[3]);
          String left = words[1].compareTo(words[2]) <=0 ? words[1] : words[2];
          String right = words[1].compareTo(words[2]) <= 0 ? words[2] : words [1];
          extendedTree.updateRange ("", tree.root, left, right, tree.height, value);
        }
        else{
          //return entrance fee
          find(words[1], tree);
        }
    	}
    }

  static void updateRange (String low, Node n, String left, String right, int height, int value){
    System.out.println(left.compareTo(""));
    System.out.println(low + ' ' + left + ' ' + n.guide + ' '+ right);
    if (height == 0){
      if (n.guide.compareTo(left) >= 0 && n.guide.compareTo(right) <= 0){
        n.value= n.value + value;
      }
      return;
    }
    //fully in range
    if ((low.equals("") || left.compareTo(low) <= 0 ) && right.compareTo(n.guide) >= 0){
      System.out.println("covers");
  
      n.value+=value;
      //add print path of root
    }

    //intersects
    else if(right.compareTo(low) > 0 && left.compareTo(n.guide) <= 0){
      InternalNode q = (InternalNode) n;
      updateRange(low, q.child0, left, right, height - 1, value);
      updateRange(q.child0.guide, q.child1, left, right, height - 1, value);
      if (q.child2 != null){
        updateRange(q.child1.guide, q.child2, left, right, height - 1, value);
      }
    }
    else{
      System.out.println("Goes to else");
    }

    //do nothing if not in range
  }
  static void find(String key, TwoThreeTree tree) {
      int h = tree.height;

      if (h == -1) {
          return;
      }
      else {
        int cost = doFind(key, tree.root, h, 0);
        System.out.println(cost);
      }

   }
   static int doFind(String key, Node p, int h, int total) {

      if (h == 0) {
         // we're at the leaf level

         int cmp = key.compareTo(p.guide);

         if (cmp == 0) {
            return p.value + total;
         }
         return -1;
      }
      else {
         InternalNode q = (InternalNode) p;
         int pos;
         int val;

         if (key.compareTo(q.child0.guide) <= 0) {
            pos = 0; 
            val = doFind(key, q.child0, h-1, total + q.value);
         }
         else if (key.compareTo(q.child1.guide) <= 0 || q.child2 == null) {
            pos = 1;
            val = doFind(key, q.child1, h-1, total + q.value);
         }
         else {
            pos = 2; 
            val = doFind(key, q.child2, h-1, total + q.value);
         }
         return val;
      }
    }
}
