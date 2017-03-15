/*****************************************************
CS 4365.001
Colleen Cousins
Matthew Villarreal
******************************************************/
import java.util.*;
public class Node {

  public Map<String, List<Integer>> vars = new HashMap<String, List<Integer>>();
  public List<Integer> assignment = new ArrayList<Integer>();
  int maxvals = -1;

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
    } //end for
    int k = (int)key.charAt(0);
    assignment.add(k);
    assignment.add(val);
  } //end Node(3)

  public char varHuer(List<char[]> constraint) {
    int maxSize = -1;
    int mostCon1 = 0;
    int mostCon2 = 0;
    char constVar = 'z';
    char constVar2 = 'z';
    char returnVar = 'z';

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
    return returnVar;
  } //end varHuer

  public int valHuer(List<char[]> constraint) {

    Map<String, List<Integer>> check = new HashMap<String, List<Integer>>();

    for(Map.Entry<String, List<Integer>> m : vars.entrySet()) //deep copy map
      check.put(m.getKey(), new ArrayList<Integer>(m.getValue()));

    //check with constraints and remove values from each var that don't work

    int amount = 0;
    for (List<Integer> vals : check.values())
      amount += vals.size();
    maxvals = amount;
    return amount;
  } //end valHuer


  public boolean constrCheck() { //do this now
    return false;
  } //end constrCheck

  public void printAssignment() {
    for (int i = 0; i < assignment.size(); i++) {
      int var = assignment.get(i);
      System.out.print((char)var);
      i++;
      System.out.print(" : " + assignment.get(i) + ", ");
    } //end for
  } //end printAssignment














} //end Node
