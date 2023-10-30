package util;

/**
 * A simple iterator implementation.
 *
 * @param <T> The type of the elements to iterate over.
 */
public interface MyIterator<T> {

  /**
   * Returns {@code true} if the iteration has more elements.
   *
   * @return {@code true} if the iteration has more elements.
   */
  boolean hasNext();

  /**
   * Iterates to the next element and returns the current data.
   *
   * @return The current element.
   */
  T next();
}
