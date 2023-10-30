package util;

import java.util.NoSuchElementException;

import static test.TestFramework.*;

/**
 * Tests for the {@link Stack} class.
 */
public class StackTest {

  public static void main(String[] args) {
    // test empty stack
    final Stack<Integer> stack = new Stack<>();
    assertEquals(0, stack.size());
    assertArrayEquals(new Integer[0], stack.state(Integer[]::new));
    assertEquals("[ ]", stack.toString());
    assertThrows(NoSuchElementException.class, stack::pop);

    // test push
    stack.push(1);
    assertEquals(1, stack.size());
    assertEquals(1, stack.pop());
    assertEquals(0, stack.size());
    assertThrows(NoSuchElementException.class, stack::pop);

    // test push multiple
    stack.push(1);
    stack.push(2);
    stack.push(3);
    assertEquals(3, stack.size());
    assertEquals(3, stack.pop());
    assertArrayEquals(new Integer[] { 2, 1 }, stack.state(Integer[]::new));

    // test push null
    assertDoesNotThrow(() -> stack.push(null));
    assertArrayEquals(new Integer[] { null, 2, 1 }, stack.state(Integer[]::new));

    // test toString
    assertEquals("[ null 2 1 ]", stack.toString());

    // test iterator
    MyIterator<Integer> it = stack.iterator();
    assertTrue(it.hasNext());
    assertEquals(null, it.next());
    assertTrue(it.hasNext());
    assertEquals(2, it.next());
    assertTrue(it.hasNext());
    assertEquals(1, it.next());
    assertFalse(it.hasNext());
    assertThrows(NullPointerException.class, it::next);

    System.out.println("All tests passed!");
  }
}
