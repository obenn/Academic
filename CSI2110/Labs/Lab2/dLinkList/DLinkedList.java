/*  CSI2114 Lab 2 - DLinkedList.java
 *  
 *  Class doubly linked list   
 *  
 *  by Jeff Souza
 *
 */

class DLinkedList {

    ListNode firstNode;
    ListNode lastNode;

    // Appends a node to the end of the list
    void AppendNode(ListNode nNode) {
        InsertNode(nNode,lastNode);
    }

    // Inserts a node into the list after pAfter
    void InsertNode(ListNode nNode, ListNode pAfter) {
        for (ListNode t = firstNode; t != null; t = t.next) {
            if (t == pAfter) {
                nNode.next = t.next;
                nNode.previous = t;
                if (t.next != null) t.next.previous = nNode;
                else lastNode = nNode;
                t.next = nNode;
                break;
            }
        }
    }

    // Removes the specified node from the list
    void RemoveNode(ListNode nNode) {
        for (ListNode t = firstNode; t != null; t = t.next) {
            if (t == nNode) {
                if (t.previous == null && t.next != null) {
                    firstNode = t.next;
                    t.next.previous = null;
                    break;
                }
                if (t.next == null ) {
                    lastNode = t.previous;
                    t.previous.next = null;
                }
            }
        }
    }

    // print the content of the list
    void print() {
        ListNode nNode = null;
        System.out.print("Current list: ");
        for (nNode = firstNode; nNode != null; nNode = nNode.next) {
            System.out.print(nNode.data +  " ");
        }
        System.out.println("");
    } 
  
}