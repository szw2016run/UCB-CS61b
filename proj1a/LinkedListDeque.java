public class LinkedListDeque<Item> {
//    private static class IntNode{
//        public int first;
//        public IntNode rest;
//
//        public IntNode(int first,IntNode rest){
//            this.first = first;
//            this.rest = rest;
//        }
//    }
//
//    public IntNode prev;
//    public int item;
//    public IntNode next;
    private class Node {
        private Item item;
        private Node next, prev;

        Node(Node prev, Item item, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private int size = 0;
    private Node sentinel;

    //creates an empty linked list deque.
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(Item item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next = newNode;
        sentinel.next.next.prev = newNode;
        size += 1;
    }

    public void addLast(Item item) {
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = newNode;
        sentinel.prev.prev.next = newNode;
        size += 1;
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> L = new LinkedListDeque<>();
//        L.addFirst(3);
//        L.addLast(5);
//        L.addFirst(1);
//        System.out.println(L.removeLast());
//        System.out.println(L.removeLast());
//        System.out.println(L.removeLast());
//        System.out.println(L.removeLast());
//        //System.out.println(L.get(2));
//        //L.printDeque();
//    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node L = this.sentinel.next;
        while (L.next != this.sentinel) {
            System.out.print(L.item + " ");
            L = L.next;
        }
        System.out.println(L.item);
    }

    public Item removeFirst() {
        if (size == 0) {
            return null;
        } else {
            Item x = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return x;
        }

    }

    public Item removeLast() {
        if (size == 0) {
            return null;
        } else {
            Item x = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return x;
        }
    }

    public Item get(int index) {
        if (index > size - 1) {
            return null;
        } else {
            Node p = sentinel.next;
            while (index > 0) {
                p = p.next;
                index -= 1;
            }
            return p.item;

        }
    }







}
