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
        List<Integer> tempList = new ArrayList<>();

        while(vScan.hasNextInt())
          tempList.add(vScan.nextInt());

          vList.put(key.substring(0, 1), tempList);
      } //end while

        /*for(Map.Entry entry : vList.entryList()) {
          System.out.println(entry.getKey() + ", " + entry.getValue());
        } //end for */
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
        //for(int i = 0; i < constr.size(); i++) {
          //char[] got = constr.get(i);
          //System.out.println(got[0] + " " + got[1] + " " + got[2]);
        //} //end for
    } //end try
    catch(FileNotFoundException f) {
      f.printStackTrace();
    } //end catch

    if(args[2].equals("fc"))
      fc = true;

    //LinkedList<Node> space = new LinkedList<Node>();
    Node head = new Node(vList);
    //space.addFirst(head);

    Node next = head;
    
    Stack<Node> search = new Stack<>();
    Comparator<Node> comparator = new CSPCompare();
    PriorityQueue<Node> pq = new PriorityQueue<>(500, comparator);
    
    int i = 0;
    for(int i=0; i < 30; i++) {
      next = new Node(head);
    } //end forwhile(i < 30) {
    	char nextVariable = next.varHuer(constr);
    	Node child = null;
		if(vList.containsKey(nextVariable))
		{
			List<Integer> tempList = new ArrayList<Integer>();
    		tempList = vList.get(nextVariable);
			for(int j = 0 ; j < vList.get(nextVariable).size();j++)
			{
				String var = Character.toString(nextVariable);
	    		child = new Node(next, var, tempList.get(i));
	    		child.valHuer(constr);
	    		pq.offer(child);
			}
			// Add next Node to stack
		 	search.push(next);
		 	
		}
		
		boolean flag = false;
		
		while(!pq.isEmpty())
		{
			child = pq.poll();
		 	i++;
		 	if(child.constrCheck() ==  true)
		 	{
		 		next = child;
		 		flag = true;
		 		pq.clear();
		 		break;
		 	}
		 	else 
		 	{
		 		//i++;
		 		child.printAssignment();
		 		//child = pq.poll();
		 	}
		}
		
		if(flag == false)
		{
			if(!search.isEmpty()){
				next = search.pop();
				System.out.println("There is no solution.");
				return;
			}

		}
    	//next = new Node(head);

      
    } //end while
  } //end main
} //end class AI2
