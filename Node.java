/*****************************************************
CS 4365.001
Colleen Cousins
Matthew Villarreal
******************************************************/
import java.util.*;
public class Node {

  public Map<String, List<Integer>> vars = new HashMap<String, List<Integer>>();
  public List<Integer> assignment = new ArrayList<Integer>();
  private int maxvals = -1;
  private String keyVal = " ";
  private int valUsed = -1;


  public Node(Map<String, List<Integer>> original) { //constructor
    for(Map.Entry<String, List<Integer>> orig : original.entrySet()) //copy map
      this.vars.put(orig.getKey(), new ArrayList<Integer>(orig.getValue()));

  } //end Node(2)

  public Node(Node other) {
    for(Map.Entry<String, List<Integer>> orig : other.vars.entrySet()) //copy map
      this.vars.put(orig.getKey(), new ArrayList<Integer>(orig.getValue()));

    this.assignment = new ArrayList<Integer>(other.assignment);
  } //end Node(1)

  public Node(Node parent, String key, int val) {
	    for(Map.Entry<String, List<Integer>> entry : parent.vars.entrySet()) {
	      String check = entry.getKey();
	      if(key.equals(check))
	        this.vars.put(entry.getKey(), new ArrayList<Integer>(val));
	      else
	        this.vars.put(entry.getKey(), new ArrayList<Integer>(entry.getValue()));
	      setKeyVal(key);
	      setValUsed(val);
	    } //end for
	    int k = (int)key.charAt(0);
	    this.assignment = new ArrayList<Integer>(parent.assignment);
	    assignment.add(k);
	    assignment.add(val);
	  } //end Node(3)


  	public int getMaxVals(){
  	  return maxvals;
    }
  	public String getKeyVal() {
  	    return keyVal;
  	  }
  	  public int getValUsed() {
  	    return valUsed;
  	}

    public void setMaxVals(int max) {
        this.maxvals = max;
      } //end setMaxVals
      public void setKeyVal(String k) {
        this.keyVal = k;
      }
      public void setValUsed(int v) {
        this.valUsed = v;
    }
  List<String> pastVariables = new ArrayList<>();
  public char varHuer(List<char[]> constraint) {
    int maxSize = -1;
    int mostCon1 = 0;
    int mostCon2 = 0;
    char constVar = 'z';
    char constVar2 = 'z';
    char returnVar = 'z';


    int mrv = 1000;
    char returnChar = 'z';
    Map<String, Integer> remainingVariables = new HashMap<String, Integer>();

    //**********************************************************
    //				FIND MAX SET 							   *
    //**********************************************************
    for(Map.Entry<String, List<Integer>> entry : vars.entrySet()) {
        List<Integer> v = entry.getValue();
        if(v.size() > maxSize) {
          maxSize = v.size();
          //constVar = entry.getKey().charAt(0);
        } //end if
        if(v.size() == maxSize)
        	remainingVariables.put(entry.getKey(),0);
    }

    System.out.println("Max Length is :" + maxSize);

    //**********************************************************
    //				MOST CONSTRAINED VARIABLE				   *
    //**********************************************************
    for(Map.Entry<String, List<Integer>> entry : vars.entrySet())
    {
    	List<Integer> varList = entry.getValue();
    	//System.out.println("Var: " + entry.getKey() + " size: " + varList.size());

    	if(checkAssignment(entry.getKey())){
    		continue;
    	}


		if(varList.size() < mrv ){
    		mrv = varList.size();
    		constVar = entry.getKey().charAt(0);
    		System.out.println("Current Variable is: " + constVar);
    	}

    }
    if(mrv < maxSize){
    	 pastVariables.add(Character.toString(constVar));
    	 System.out.println("Returning Variable: " + constVar);
    	 return constVar;
    }
    //**********************************************************
    //				MOST CONSTRAINING VARIABLE				   *
    //**********************************************************

    	//List<Integer> varList = entry.getValue();
    	//System.out.println("Var: " + entry.getKey() + " size: " + varList.size());
    int max = 0;
	if(mrv == maxSize)
	{
        //constVar2 = entry.getKey().charAt(0);
		for(Map.Entry<String, Integer> entry : remainingVariables.entrySet())
		{
			for(char c[]: constraint)
			{
				if(constraint.contains(entry.getKey()))
					remainingVariables.put(entry.getKey(), entry.getValue() + 1);
				System.out.println("remaining vaiable in most constraining.");
			}

			if(entry.getValue() > max)
			{
				max = entry.getValue();
				constVar = entry.getKey().charAt(0);
				System.out.println("Most constraining.");
			}
		}

	}
	pastVariables.add(Character.toString(constVar));
    System.out.println("Returning Variable: " + constVar);
    return constVar;

    /*
    for(Map.Entry<String, List<Integer>> entry : vars.entrySet()) {
      List<Integer> v = entry.getValue();
      if(v.size() > maxSize) {
        maxSize = v.size();
        constVar = entry.getKey().charAt(0);
      } //end if
      if(v.size() == maxSize) {
        constVar2 = entry.getKey().charAt(0);
        for(char c[] : constraint) {
          for(int i = 0; i < c.length; i++) {
            if(c[i] == constVar)
              mostCon1++;
            if(c[i] == constVar2)
              mostCon2++;
          } //end nested for
        } //end nested for
        if(mostCon2 > mostCon1)
          returnVar = constVar2;
        else
          returnVar = constVar;
      } //end if
    } //end for
    return returnVar;*/
  } //end varHuer

  public boolean checkAssignment(String key)
  {
		  for(int i = 0; i < assignment.size(); i++)
		  	{
		  		int var = assignment.get(i);
		  		if((char)var == key.charAt(0))
		  		{
		  			return true;
		  		}
		  		i++;
		  	}
	  return false;

  }

  public void valHuer(List<char[]> constraint) {

    Map<String, List<Integer>> check = new HashMap<String, List<Integer>>();

    for(Map.Entry<String, List<Integer>> m : vars.entrySet()) //deep copy map
      check.put(m.getKey(), new ArrayList<Integer>(m.getValue()));

    //check with constraints and remove values from each var that don't work

    int amount = 0;
    for (List<Integer> vals : check.values())
      amount += vals.size();
    maxvals = amount;
  } //end valHuer


  public boolean constrCheck(List<char[]> constraints) { //do this now

	  Map<String, Integer> assign = new HashMap<String, Integer>();

	  // Initialize the 'assign' map, so that every variable is null
	  for(Map.Entry<String, List<Integer>> entry : vars.entrySet()) {
	        	assign.put(entry.getKey(),-1);
	  }

	  // Update the 'assign' map
	  for(int i=0; i < assignment.size();i++)
	  {
		  int key = assignment.get(i);
		  char key2 = (char)key;
		  i++;
		  assign.put(Character.toString(key2), assignment.get(i));
	  }

    boolean flag = true;
	  // Start checking
	  for(Map.Entry<String, Integer> entry : assign.entrySet())
	  {
		  for(Map.Entry<String, Integer> entry2 : assign.entrySet())
		  {
			  for(int i=0; i < constraints.size();i++)
			  {

				  char [] got = constraints.get(i);
				  //System.out.println("Var1: " + entry.getKey() +" Var2: " + entry2.getKey() + " Con0: " + got[0] + " Con2: " + got[2]);

				  if(entry.getValue() == -1 || entry2.getValue() == -1) {
			            continue;
			          } //end if

				  if(entry.getKey().equals(Character.toString(got[0])) && entry2.getKey().equals(Character.toString(got[2])))
				  {
					  if(got[1] == '<' && entry.getValue() >= entry2.getValue())
			          	  flag = false;
			            else if(got[1] == '>' && entry.getValue() <= entry2.getValue())
			          	  flag = false;
			            else if(got[1] == '=' && entry.getValue() != entry2.getValue())
			          	  flag = false;
			            else if(got[1] == '!' && entry.getValue() == entry2.getValue())
			          	  flag = false;
			            else { } //do nothing

					  //System.out.println("Flag1: " + flag);
				  }
				  else if(entry2.getKey().equals(Character.toString(got[2])) && entry.getKey().equals(Character.toString(got[0])))
				  {
					  if(got[1] == '<' && entry2.getValue() >= entry.getValue())
			          	  flag = false;
			            else if(got[1] == '>' && entry2.getValue() <= entry.getValue())
			          	  flag = false;
			            else if(got[1] == '=' && entry2.getValue() != entry.getValue())
			          	  flag = false;
			            else if(got[1] == '!' && entry2.getValue() == entry.getValue())
			          	  flag = false;
			            else { } //do nothing
					  //System.out.println("Flag2: " + flag);
				  }
				  else
				  {
					  //System.out.println("No flag: " + flag);
				  }

          if(!flag)
            return false;
			  }
		  }

	  }

    return true;
} //end constrCheck

  public void printAssignment() {
    for (int i = 0; i < assignment.size(); i++) {
      int var = assignment.get(i);
      System.out.print((char)var);
      i++;
      System.out.print(" : " + assignment.get(i) + ", ");
    } //end for
    System.out.println();
  } //end printAssignment



} //end Node
