
public class LinkedList {

    private ListNode head;
    private ListNode tail;
    private int count;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void addToHead(Object item) {
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            head = new ListNode(item, head);
        }
        count++;
    }

    public void addToTail(Object item) {
        if (isEmpty()) {
            head = tail = new ListNode(item);
        } else {
            tail.next = new ListNode(item);
            tail = tail.next;
        }
        count++;
    }

    public Object removeFromHead() throws EmptyListException {
        Object item = null;
        if (isEmpty()) {
            throw new EmptyListException();
        }
        item = head.data;
        if (head == tail) // there's only one single node
        {
            head = tail = null;
        } else {
            head = head.next;
        }
        count--;
        return item;
    }

    public Object removeFromTail() throws EmptyListException {
        if (isEmpty()) {
            throw new EmptyListException();
        }
        Object item = tail.data;
        if (head == tail) {   // there is only one node
            head = tail = null;
            count--;
            return item;
        }

        ListNode current = head;
        while (current.next != tail) {
            current = current.next;
        }
        tail = current;
        tail.next = null;
        count--;
        return item;
    }

    public String toString() {
        String s = "[ ";
        ListNode current = head;
        while (current != null) {
            s += current.data + " ";
            current = current.next;
        }
        return s + "]";
    }

    public int getCount() {
        return count;
    }

    public Object getItemAt(int n) {
        if (n < 0 || n >= count) {
            throw new IndexOutOfBoundsException();
        }
        int currentPos = 0;
        ListNode current = head;
        while (currentPos < n) {
            current = current.next;
            currentPos++;
        }
        return current.data;
    }

    public Object removeItemAt(int n) {
        if (n < 0 || n >= count) {
            throw new IndexOutOfBoundsException();
        }
        if (n == 0) {
            return removeFromHead();
        }
        int currentPos = 0;
        ListNode current = head;
        while (currentPos < n - 1) { // locate the node preceding the one to be removed
            current = current.next;
            currentPos++;
        }
        if (current.next == tail) {
            tail = current;
        }
        Object item = current.next.data;
        current.next = current.next.next;
        count--;
        return item;
    }

    public void addItemAt(Object item, int n) {
        if (isEmpty() || n == 0) {
            addToHead(item);
            return;
        }
        if (n >= count) {
            throw new IndexOutOfBoundsException();
        }
        int currentPos = 0;
        ListNode current = head;
        while (currentPos < (n - 1)) { // locate the node preceding the one to be removed
            current = current.next;
            currentPos++;
        }
        ListNode newNode = new ListNode(item); // create a new node
        newNode.next = current.next;
        current.next = newNode;
        count++;
    }

    public int findIndew(int card) {
        int i = 0;
        ListNode current = head;
        while (!current.data.equals(card)) {
            current = current.next;
            i++;
        }
        return i;
    }
}

class EmptyListException extends NullPointerException {

    public EmptyListException() {
System.out.print("no Itemin the list ");
    }
}

class ItemNotFoundException extends NullPointerException {

    public ItemNotFoundException() {
        System.out.print("Item do Not Found");
    }

}

class ListNode {

    public Object data;
    public ListNode next;

    public ListNode(Object data) {
        this.data = data;
        this.next = null;
    }

    public ListNode(Object data, ListNode next) {
        this.data = data;
        this.next = next;
    }
}
