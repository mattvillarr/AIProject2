import java.util.Comparator;

public class CSPCompare implements Comparator<Node> {

  @Override
    public int compare(Node a, Node b) {
      Integer a2 = new Integer(a.getMaxVals());
      Integer b2 = new Integer(b.getMaxVals());
      return a2.compareTo(b2);
    } //end compare
} //end CSPCompare
