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
      int query;


    	for (int i = 0; i < num_input; i++){
    		input = reader.readLine();
    		String [] words = input.split(" ");
        //if first is 1, insert
        query = Integer.parseInt(words[0]);
        if (query == 1){
          //insert
          tree.insert(words[1], Integer.parseInt(words[2]), root);
        }
        else if(query == 2){
          
          tree.updateRange (root.root.guide, root.root, words[1], words[2], root.height, words[3]);
          System.out.println(root.root.guide);
        }
        else{
          //return entrance fee
          System.out.println("query is 3");
        }
    	}
    }

  static void updateRange (String low, Node n, String left, String right, int height, String value){
    System.out.println("value is: " + value);

    if (height == 0){
      if (n.guide.compareTo(left) >= 0 && n.guide.compareTo(right) <= 0){
        System.out.println("guide is: " + n.guide);
        n.guide+= value;
      }
      return;
    }
    //fully in range
    if (left.compareTo(low) <= 0 && right.compareTo(n.guide) >= 0){
      System.out.println("original n.guide: " + n.guide);
      n.guide+=value;
      System.out.println("part 2: " + n.guide);
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

    //do nothing if not in range
  }
}
