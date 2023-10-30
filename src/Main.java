import hanoi.Hanoi;
import hanoi.gui.JHanoi;

/**
 * Main class for testing the Hanoi game.
 * <p>
 * <b>Usage</b>
 * {@code java Main [nbDisks]}
 * If nbDisks is not given, a GUI opens for visualization.
 */
public class Main {

  public static void main(String[] args) {
    if (0 < args.length) {
      try {
        Hanoi hanoi = new Hanoi(Integer.parseInt(args[0]));
        hanoi.solve();
        return;
      } catch (IllegalArgumentException e) {
        System.err.println(""
            + "Invalid number of disks: \""
            + args[0]
            + "\", please enter a numeric strictly-positive value.");
        System.exit(1);
        return;
      }
    }

    new JHanoi();
  }
}
