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
      /*try { //var file parsing

      } //end try
      catch(FileNotFoundException e) {
        e.printStackTrace();
      } //end catch */

      List<char[]> constr = new ArrayList<char[]>();

      try { //con file parsing
        cScan = new Scanner(conF);
        while(cScan.hasNextLine()) {
          char[] in = cScan.nextLine().toCharArray();
          char[] arr = {in[0], in[2], in[4]};
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

    List<Node> space = new LinkedList<Node>();
  } //end main
} //end class AI2
