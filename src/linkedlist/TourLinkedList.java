package linkedlist;

public class TourLinkedList {
    private Node head;

    public TourLinkedList() {
        head = null;
    }

    public void addFirst(Location location) {
        Node newNode = new Node(location);

        if (head == null) {
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }
}
