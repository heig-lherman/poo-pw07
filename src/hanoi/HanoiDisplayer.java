package hanoi;

/**
 * Base implementation of a displayer for the Hanoi solver.
 * Prints the state of the game to the standard output.
 */
public class HanoiDisplayer {

  private static final String[] NEEDLE_NAMES = new String[] { "One", "Two", "Three" };

  /**
   * Prints the current state of the game.
   * 
   * @param hanoi The game to print.
   */
  public void display(Hanoi hanoi) {
    System.out.println("-- Turn: " + hanoi.turn());
    int[][] status = hanoi.status();
    for (int i = 0; i < status.length; ++i) {
      System.out.printf(
          "%-6s %s%n",
          NEEDLE_NAMES[i] + ":",
          formatNeedleOutput(status[i]));
    }
  }

  private String formatNeedleOutput(int[] needle) {
    StringBuilder sb = new StringBuilder();

    sb.append("[ ");
    for (int j : needle) {
      sb.append("<").append(j).append("> ");
    }
    sb.append("]");

    return sb.toString();
  }
}
