package util;

import java.util.NoSuchElementException;
import java.util.function.IntFunction;

/**
 * A simple stack implementation using a singly linked list.
 *
 * @param <T> The type of the elements to store.
 */
public final class Stack<T> {

  private Element<T> head = null;
  private int size = 0;

  /**
   * @return The number of elements in the stack.
   */
  public int size() {
    return size;
  }

  /**
   * Adds an element to the top of the stack.
   *
   * @param value The value to add.
   */
  public void push(T value) {
    head = new Element<>(value, head);
    ++size;
  }

  /**
   * Removes the element at the top of the stack and returns it.
   *
   * @return The element at the top of the stack.
   * @throws NoSuchElementException If the stack is empty.
   */
  public T pop() {
    if (null == head) {
      throw new NoSuchElementException("Cannot pop from an empty stack");
    }

    T val = head.value;
    head = head.next();
    --size;
    return val;
  }

  /**
   * Returns the current state of the stack by returning it in array form.
   *
   * @param arrayCtor The constructor of the array to return.
   * @return The current state of the stack.
   */
  public T[] state(IntFunction<T[]> arrayCtor) {
    T[] array = arrayCtor.apply(size);

    int i = 0;
    var it = iterator();
    while (it.hasNext()) {
      array[i++] = it.next();
    }

    return array;
  }

  /**
   * Returns an iterator over the elements in the stack.
   *
   * @return An iterator over the elements in the stack.
   */
  public MyIterator<T> iterator() {
    return new StackIterator<>(head);
  }

  /**
   * Allows to print the stack using the array format.
   *
   * @return The string representation of the stack.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[ ");
    var it = iterator();
    while (it.hasNext()) {
      sb.append(it.next()).append(" ");
    }
    sb.append("]");
    return sb.toString();
  }

  /**
   * A simple element holder of the stack's singly linked list.
   *
   * @param <T> The type of the element to store.
   */
  private static final class Element<T> {

    private final T value;
    private final Element<T> next;

    /**
     * @param value The value to store, nullable.
     * @param next  The next element, nullable if last.
     */
    private Element(T value, Element<T> next) {
      this.value = value;
      this.next = next;
    }

    /**
     * Returns the data stored in the element.
     * 
     * @return The data stored in the element.
     */
    public T value() {
      return value;
    }

    /**
     * Returns the next element.
     * 
     * @return The next element.
     */
    public Element<T> next() {
      return next;
    }

    /**
     * Returns the string representation of the element.
     * 
     * @return The string representation of the element.
     */
    @Override
    public String toString() {
      return value.toString();
    }
  }

  /**
   * A simple iterator implementation.
   * <p>
   * Uses {@link MyIterator} as java's built-in iterator has optional methods that
   * would make the implementation untidy.
   * 
   * @param <T> The type of the elements to iterate over.
   */
  private static final class StackIterator<T> implements MyIterator<T> {

    private Element<T> current;

    /**
     * @param head The head of the stack's singly linked list.
     */
    private StackIterator(Element<T> head) {
      current = head;
    }

    @Override
    public boolean hasNext() {
      return null != current;
    }

    @Override
    public T next() {
      T o = current.value();
      current = current.next();
      return o;
    }
  }
}
