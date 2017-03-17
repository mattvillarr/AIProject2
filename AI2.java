/*****************************************************
CS 4365.001
Colleen Cousins
Matthew Villarreal
******************************************************/
import java.util.*;
import java.io.*;

public class AI2 {
  public static void main(String[] args) {

    Boolean fc = false;

    File varF = new File(args[0]);
    File conF = new File(args[1]);

    Scanner vScan = null;
    Scanner cScan = null;
    Map<String, List<Integer>> vList = new HashMap<String, List<Integer>>();

    List<char[]> constr = new ArrayList<>();

    try {
      vScan = new Scanner(varF);

      while(vScan.hasNext()) {
        String key = vScan.next();
        List<Integer> tempList = new ArrayList<Integer>();

        while(vScan.hasNextInt())
          tempList.add(vScan.nextInt());

          vList.put(key.substring(0, 1), tempList);
      } //end while

    } //end try
    catch(FileNotFoundException g) {
      g.printStackTrace();
    } //end catch

    try { //con file parsing
      cScan = new Scanner(conF);
      while(cScan.hasNextLine()) {
        char in[] = cScan.nextLine().toCharArray();
        char arr[] = {in[0], in[2], in[4]};
        constr.add(arr);
      } //end while

    } //end try
    catch(FileNotFoundException f) {
      f.printStackTrace();
    } //end catch

    if(args[2].equals("fc"))
      fc = true;

    Node head = new Node(vList);

    Node next = head;

    Stack<Node> searchSTK = new Stack<>();
    Comparator<Node> comparator = new CSPCompare();
    PriorityQueue<Node> pq = new PriorityQueue<>(500, comparator);

    //System.out.println("Now doing backtracking...");
    searchSTK.push(head);

	  int i = 0;
    while(i < 30) {
    	char nextVariable = next.varHuer(constr);

    	//System.out.println("Now checking Variable: " + nextVariable);
    	Node child = null;

		if(next.vars.containsKey(Character.toString(nextVariable)))
		{
			for(int j = 0 ; j < next.vars.get(Character.toString(nextVariable)).size();j++)
			{
				//System.out.println("Creating new node.");
				String var = Character.toString(nextVariable);
				//System.out.println(tempList.get(i));
				child = new Node(next, var, next.vars.get(Character.toString(nextVariable)).get(j));
	    	child.valHuer(constr, nextVariable);
	    	pq.offer(child);
			}
		}

		boolean flag = false;

		while(!pq.isEmpty())
		{
			child = pq.poll();
		 	if(child.constrCheck(constr) ==  true)
		 	{
		 		//System.out.println("Debug 1");
        if(fc) {
          if(!next.forwardCheck(constr, nextVariable)) {
            i++;
            System.out.print(i + ". ");
    		 		child.printAssignment();
    		 		System.out.print(" failure \n");
            break;
          }
        }
		 		searchSTK.push(next); //will next on the stack be changed to child??
		 		next = child;
		 		flag = true;
		 		pq.clear();
		 		break;
		 	}
		 	else
		 	{
		 		i++;
        System.out.print(i + ". ");
		 		child.printAssignment();
		 		System.out.print(" failure \n");
		 	}
		}

		if(flag == false)
		{
			if(!searchSTK.isEmpty()) {
				Node temp = searchSTK.pop();
				String sk = next.getKeyVal();
        List<Integer> rm = temp.vars.get(sk);
        int v = next.getValUsed();
        if(v != -1) {
          for(int j = 0; j < rm.size(); j++) {
            if(rm.get(j) == v)
              rm.remove(j);
          } //end for
          temp.vars.remove(sk);

          next = new Node(temp);
          next.vars.put(sk, rm);
        }
      }
      else {
				System.out.println("There is no solution.");
				return;
			}
		}

    if(flag) {
      Boolean correct = true;
      for(List<Integer> answer : next.vars.values()) {
        if(answer.size() != 1)
          correct = false;
      }
      if(correct) {
        System.out.print((i+1) + ". ");
        next.printAssignment();
        System.out.println(" solution\n");
        return;
      }
    }

    } //end for
  } //end main
} //end class AI2
