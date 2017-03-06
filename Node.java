/*****************************************************
CS 4365.001
Colleen Cousins
Matthew Villarreal
******************************************************/
import java.util.*;
public class Node {

  Map<String, List<Integer>> vars = new HashMap<String, List<Integer>>();
  List<Integer> assignment = new ArrayList<Integer>();

  public Node(Map<String, List<Integer>> original) { //constructor
    for(Map.Entry<String, List<Integer>> orig : original.entrySet()) //copy map
      vars.put(orig.getKey(), new ArrayList<Integer>(orig.getValue()));

  } //end Node(2)

  public Node(Node other) {
    for(Map.Entry<String, List<Integer>> orig : other.vars.entrySet()) //copy map
      this.vars.put(orig.getKey(), new ArrayList<Integer>(orig.getValue()));

    this.assignment = new ArrayList<Integer>(other.assignment);
  } //end Node(1)

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

  } //end printAssignment














} //end Node
