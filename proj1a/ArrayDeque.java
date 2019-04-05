public class ArrayDeque<T> {
    private int size;
    private T[] arr;
    private int nextFirst;
    private int nextLast;
    private int first;
    private static final int CAPACITY = 8;
    /*
    Create an empty list
     */
    public ArrayDeque() {
        arr = (T[]) new Object[CAPACITY];
        nextFirst = (arr.length - size) / 2;
        nextLast = nextFirst + 1;
        first = nextFirst;
    }
    private int length() {
        return arr.length;
    }
    public ArrayDeque(ArrayDeque other) {
        arr = (T[]) new Object[other.length()];
        nextFirst = (arr.length - size) / 2;
        nextLast = nextFirst + 1;
        first = nextFirst;
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }

    /*
    Resizes the underlying array to the target capacity.
     */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int tempLength = arr.length - first;
        System.arraycopy(arr, first, a, (a.length / 2 - 1), tempLength);
        if (tempLength != arr.length) {
            System.arraycopy(arr, 0, a, ((a.length / 2) - 1) + tempLength, first);
        }
        arr = a;
        first = a.length / 2 - 1;
        nextFirst = minusOne(a.length / 2 - 1);
        nextLast = first + size;

    }

    /*
    Resizes the underlying array to down to the target capacity.
     */
    private void resizedown(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (first + size > arr.length) {
            int left = (first + size) - arr.length;
            int tempLength = arr.length - first;
            System.arraycopy(arr, first, a, (a.length / 2) - 1, tempLength);
            System.arraycopy(arr, 0, a, ((a.length / 2) - 1) + tempLength, left);
        } else {
            System.arraycopy(arr, first, a, (a.length / 2) - 1, size);
        }
        arr = a;
        nextFirst = minusOne((a.length / 2) - 1);
        first = plusOne(nextFirst);
        nextLast = first + size;
    }

    /*
    Checks whether the array is full and needs to be resized.
     */
    private boolean isFull() {
        return size == arr.length;
    }

    /*
    Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        first = nextFirst;
        arr[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }
    /*
    Adds an item of type T to the front of the deque.
     */
    public void addLast(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        arr[nextLast] = item;
        if (arr[first] == null) {
            first = nextLast;
        }
        nextLast = plusOne(nextLast);
        size += 1;
    }

    /*
    Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkResize() {
        if ((double) size / arr.length < 0.25 && (arr.length >= 16)) {
            resizedown(arr.length / 2);
        }
    }

    /*
    Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /*
    Removes and returns the item at the first of the deque.If no
    such item exits,return null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T item = arr[nextFirst];
        arr[nextFirst] = null;
        first = plusOne(first);//zy
        size -= 1;
        checkResize();
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T item = arr[nextLast];
        size -= 1;
        arr[nextLast] = null;
        checkResize();
        return item;
    }



    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addLast(0);
        L.addLast(1);
        L.addLast(2);
        L.removeFirst();//     ==> 0
        L.addFirst(4);
        L.removeLast();//      ==> 2
        L.removeFirst();//     ==> 4
        L.addLast(7);
        L.addLast(8);
        L.removeFirst();  //   ==> 1
        L.get(1);      //==> 8
        L.removeFirst();   // ==> 7
        L.removeLast();    //  ==> 8
        L.addLast(13);
        L.removeLast();      //==> 13
        L.addFirst(15);
        L.removeFirst();    // ==> 15
        L.addLast(17);
        L.addLast(18);
        L.removeFirst();//     ==> 17
        L.get(0);     // L
    }

    /*
    Returns x + 1 for any given integer x.
    Used primary for cleanliness.
     */
    private int plusOne(int x) {
        if (x == arr.length - 1) {
            return 0;
        }
        return x + 1;
    }

    private int minusOne(int x) {
        if (x == 0) {
            return arr.length - 1;
        }
        return x - 1;
    }

    /*
    Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, return null
     */
    public T get(int index) {
        if (index > size) {
            return null;
        } else if (first + index < arr.length) {
            return arr[first + index];
        } else {
            return arr[first + index - arr.length];
        }
    }




    /*
    Prints the items in the deque from first to last .separated by a space.
     */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}
