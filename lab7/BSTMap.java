import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B <K, V> {
    private Node root;
    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    public BSTMap() {

    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public  boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalCallerException();
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return get(root,key);
    }

    private V get(Node root, K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (root == null) {
            return null;
        }

        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            return get(root.left, key);
        } else if(cmp > 0) {
            return get(root.right, key);
        } else {
            return root.value;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null) {
            return 0;
        }
        return root.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        root = put(root, key, value);
    }

    private Node put(Node root, K key, V value) {
        if (root == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            root.left = put(root.left, key, value);
        } else if (cmp > 0) {
            root.right = put(root.right, key, value);
        } else {
            root.value = value;
        }

        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    public void printOrder() {
        for (int i = 0; i < size(); i++) {
            System.out.println(select(i).key + " " + select(i).value);
        }
    }

    private Node select(int k) {
        if (k < 0 || k >= size()) {
            throw  new IllegalArgumentException();
        }
        return select(root, k);
    }

    private Node select(Node root, int k) {
        if (root == null) {
            return null;
        }
        int t = size(root.left);
        if (t > k) {
            return  select(root.left, k);
        } else if (t < k) {
            return select(root.right, k - t - 1);
        } else {
            return root;
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> BSTSet = new HashSet<>();
        for (int i = 0; i < size(); i++) {
            BSTSet.add(select(i).key);
        }
        return BSTSet;
    }

    @Override
    public V remove(K key) {
        if (!containsKey(key)) {
            return null;
        }
        V toRemove = get(key);
        root = remove(root, key);
        return toRemove;
    }

    private Node remove(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = remove(x.left, key);
        } else if (cmp > 0) {
            x.right = remove(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node temp = x;
            x = min(temp.right);
            x.right = deleteMin(temp.right);
            x.left = temp.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }


    @Override
    public V remove(K key, V value) {
        if (!containsKey(key)) {
            return null;
        }
        if (!get(key).equals(value)) {
            return null;
        }
        root = remove(root, key);
        return value;
    }

    public Iterator<K> iterator() {
        return new BSTIterator(root);
    }

    private class BSTIterator implements Iterator<K> {
        private Stack<Node> stack = new Stack<>();

        public BSTIterator(Node root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            Node curr = stack.pop();

            if (curr.right != null) {
                Node temp = curr.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return curr.key;
        }
    }
}