// ==========================================================================
// $Id: addTemplate.cpp,v 1.1 2005/11/02 23:13:32 jlang Exp $
// CSI2110 Lab code Node List skeleton class
// ==========================================================================
// (C)opyright:
//
//   Jochen Lang
//   SITE, University of Ottawa
//   800 King Edward Ave.
//   Ottawa, On., K1N 6N5
//   Canada. 
//   http://www.site.uottawa.ca
// 
// Creator: jlang (Jochen Lang)
// Email:   jlang@site.uottawa.ca
// ==========================================================================
// $Log: addTemplate.cpp,v $
//
// ==========================================================================
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class NodeList<E> {
  // The linked list which is to be adapted
  protected LinkedList<E> linkedList = new LinkedList<E>();

  // Directly available
  public int size() {
    return linkedList.size();
  }

  // Directly available
  public boolean isEmpty() {
     return linkedList.isEmpty();
  }

  // Fixed
  public boolean isFirst(E element) {
    return element.equals(linkedList.peekFirst()); 
  }

  // Fixed
  public boolean isLast(E element) {
    return element.equals(linkedList.peekLast());
  }

  // Directly available
  public E first() 
    throws NoSuchElementException {
    return linkedList.element();
  }


  // Directly available
  public E last() 
    throws NoSuchElementException {
    return linkedList.getLast();
  }

  // Fixed
  E prev(E element)
    throws NoSuchElementException {
    if (!linkedList.contains(element) || element == linkedList.peekFirst()) throw new NoSuchElementException();
    return(linkedList.get(linkedList.indexOf(element)-1));
  }


  // Fixed
  E next(E element)
    throws NoSuchElementException {
    if (!linkedList.contains(element) || element == linkedList.peekLast()) throw new NoSuchElementException();
    return(linkedList.get(linkedList.indexOf(element)+1));
  }

  // Fixed
  public void swapElements(E element1, E element2)
    throws NoSuchElementException {
      if (!linkedList.contains(element1) || !linkedList.contains(element2)) {
        throw new NoSuchElementException();
      }
      int index1 = linkedList.indexOf(element1);
      int index2 = linkedList.indexOf(element2);
      if (index1 < index2) {
        linkedList.add(index2, element1);
        linkedList.remove(index1);
      }
      else if (index2 < index1) {
        linkedList.add(index1, element2);
        linkedList.remove(index2);
      }
  }
  

  // Fixed
  public void set(E currElement, E repElement)
    throws NoSuchElementException {
    if (!linkedList.contains(currElement)) throw new NoSuchElementException();
    int index = linkedList.indexOf(currElement);
    linkedList.add(index, repElement);
    linkedList.remove(index+1);
  }


  // Directly available
  public void addFirst(E element) {
    linkedList.addFirst(element);
    return;
  }

  // Directly available
  public void addLast(E element) {
    linkedList.addLast(element);
    return;
  }

  // Fixed
  public void addBefore(E currElement,E addElement)
    throws NoSuchElementException {
    if (!linkedList.contains(currElement)) throw new NoSuchElementException();
    linkedList.add(linkedList.indexOf(currElement), addElement);
  }

  // Fixed
  public void addAfter(E currElement,E addElement) 
    throws NoSuchElementException {
    if (!linkedList.contains(currElement)) throw new NoSuchElementException();
    linkedList.add(linkedList.indexOf(currElement)+1, addElement);
  }
  
  // Fixed
  public E remove(E element) 
    throws NoSuchElementException {
    if (!linkedList.contains(element)) throw new NoSuchElementException();
    E elem = element;
    linkedList.remove(element);
    return elem;
  }
}
