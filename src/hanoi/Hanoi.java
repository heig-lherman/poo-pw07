package hanoi;

import util.Stack;

/**
 * A class able to solve a Hanoi game given an amount of disks.
 */
public class Hanoi {

  private static final int NUM_NEEDLES = 3;

  private final Stack<Integer>[] needles;
  private final HanoiDisplayer displayer;

  private int turns = 0;

  /**
   * Creates a new Hanoi game with the given number of disks.
   * The game will be printed to the standard output.
   * 
   * @param disks The number of disks to use.
   */
  public Hanoi(int disks) {
    this(disks, new HanoiDisplayer());
  }

  /**
   * Creates a new Hanoi game with the given number of disks.
   * The game will be printed using the given displayer.
   * 
   * @param disks     The number of disks to use.
   * @param displayer The displayer to use.
   * @throws IllegalArgumentException If the number of disks is not stricly
   *                                  positive.
   */
  @SuppressWarnings("unchecked")
  public Hanoi(int disks, HanoiDisplayer displayer) {
    if (disks < 1) {
      throw new IllegalArgumentException(
          "The number of disks must be strictly positive");
    }

    this.displayer = displayer;
    needles = new Stack[NUM_NEEDLES];

    // initialize needles
    for (int i = 0; i < NUM_NEEDLES; ++i) {
      needles[i] = new Stack<>();
    }

    // create disks
    for (int j = disks; j > 0; --j) {
      needles[0].push(j);
    }
  }

  /**
   * Returns the current turn count.
   * 
   * @return The current turn count.
   */
  public int turn() {
    return turns;
  }

  /**
   * Recursively solves the Hanoi game.
   */
  public void solve() {
    displayer.display(this);
    solveRecursively(needles[0].size(), needles[0], needles[1], needles[2]);
  }

  /**
   * Returns the current status of all needles indexed in their own separate
   * arrays
   * 
   * @return The current status of all needles indexed in their own separate
   *         arrays
   */
  public int[][] status() {
    int[][] res = new int[3][];
    for (int i = 0; i < needles.length; ++i) {
      Integer[] state = needles[i].state(Integer[]::new);
      res[i] = new int[state.length];
      for (int j = 0; j < state.length; ++j) {
        res[i][j] = state[j];
      }
    }

    return res;
  }

  /**
   * Returns whether the game is solved or not.
   * 
   * @return Whether the game is solved or not.
   */
  public boolean finished() {
    return 0 == needles[0].size() && 0 == needles[1].size();
  }

  private void solveRecursively(
      int disks,
      Stack<Integer> start,
      Stack<Integer> mid,
      Stack<Integer> end) {
    if (0 < disks) {
      solveRecursively(disks - 1, start, end, mid);
      swapDisks(start, end);
      solveRecursively(disks - 1, mid, start, end);
    }
  }

  private void swapDisks(Stack<Integer> start, Stack<Integer> end) {
    ++turns;
    end.push(start.pop());
    displayer.display(this);
  }
}
