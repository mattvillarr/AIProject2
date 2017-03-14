/*****************************************************
CS 4365.001
Colleen Cousins
Matthew Villarreal
******************************************************/
import java.util.*;
public class Node {

  public Map<String, Set<Integer>> vars = new HashMap<String, Set<Integer>>();
  public List<Integer> assignment = new ArrayList<Integer>();
  int lcv = -1;
  int maxvals = -1;

  public Node(Map<String, Set<Integer>> original) { //constructor
    for(Map.Entry<String, Set<Integer>> orig : original.entrySet()) //copy map
      vars.put(orig.getKey(), new HashSet<Integer>(orig.getValue()));

  } //end Node(2)

  public Node(Node other) {
    for(Map.Entry<String, Set<Integer>> orig : other.vars.entrySet()) //copy map
      this.vars.put(orig.getKey(), new HashSet<Integer>(orig.getValue()));

    this.assignment = new ArrayList<Integer>(other.assignment);
  } //end Node(1)

  public Node(Node parent, String key, int val) {
    for(Map.Entry<String, Set<Integer>> entry : parent.vars.entrySet()) {
      String check = entry.getKey();
      if(key.equals(check))
        this.vars.put(entry.getKey(), new HashSet<Integer>(val));
      else
        this.vars.put(entry.getKey(), new HashSet<Integer>(entry.getValue()));
    } //end for
  } //end Node(3)

  private int varHuer() {
    return 0;
  } //end varHuer

  private int valHuer() {
    return 0;
  } //end valHuer

  public void getAssign() {
    int var = varHuer();
    int val = valHuer();
    //check to make sure var is already not in the list
    //either replace it in the list with new assignment or add to end of list
  } //end getAssign

  public boolean constrCheck() {
    return false;
  } //end constrCheck

  public void printAssignment() {
    for (int i = 0; i < assignment.size(); i++) {
      System.out.print((char)65 + i);
      System.out.print(" : " + assignment.get(i) + " \n");
    } //end for
  } //end printAssignment














} //end Node
