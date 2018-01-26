import java.io.*;
/**
 * Generic linked stack implementing the generic stack iterface
 *
 * @author Oliver Benning, 7798804, University of Ottawa
 */
public class GenericLinkedStack<E> implements Stack<E> {

    private static class Elem<T> {
        private T value;
        private Elem<T> next;

        private Elem(T value, Elem<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Elem<E> top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(E value) {
		if (value == null) {
			throw new NullPointerException();
		} else {
			top = new Elem<E>(value, top);
		}
    }

    public E peek() {
		if (top == null) {
			throw new EmptyStackException();
		} else {
        	return top.value;
		}
    }

    public E pop() {
		if (top == null) {
			throw new EmptyStackException();
		} else {
        	E saved = top.value;
        	top = top.next;
        	return saved;
		}
    }
}
