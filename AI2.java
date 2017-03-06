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
      try { //var file parsing

      } //end try
      catch(FileNotFoundException e) {
        e.printStackTrace();
      } //end catch

      try { //con file parsing

      } //end try
      catch(FileNotFoundException f) {
        f.printStackTrace();
      } //end catch


      if(args[2].equals("fc"))
        fc = true;

  } //end main
} //end class AI2
