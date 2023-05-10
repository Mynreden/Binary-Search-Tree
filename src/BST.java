import java.util.*;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.KeyValueContainer> {
    private Node root;
    public class Node{
        private K key;
        private V value;
        private int size = 1;
        private Node left, right;
        private Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", size=" + size +
                    '}';
        }
    }

    public boolean put(K key, V value){
        Node newNode = new Node(key, value);
        if (this.root == null){
            this.root = newNode;
            return true;
        }
        recursiveAdd(newNode, root);
        return true;
    }

    private Node recursiveAdd(Node node, Node parent){
        if (parent == null){
            return node;
        }
        if (node.key.compareTo(parent.key) > 0){
            parent.right = recursiveAdd(node, parent.right);
        }
        else{
            parent.left = recursiveAdd(node, parent.left);
        }
        parent.size ++;
        return parent;
    }

    public V get(K key){
        return getNode(key, root).value;
    }

    private Node getNode(K key, Node head){
        int comparison = key.compareTo(head.key);
        if (comparison == 0){
            return head;
        }
        if (comparison > 0){
            return getNode(key, head.right);
        }
        else{
            return getNode(key, head.left);
        }

    }

    public int size(){
        return root.size;
    }

    public void delete(K key){
        root = delete(root, key, true);
    }

    private Node delete(Node head, K key, boolean changeSize){
        if (changeSize){
            head.size --;
        }
        int comparison = key.compareTo(head.key);
        if (comparison == 0) {
            if (head.size == 0) { // has no child
                return null;
            }
            if (head.size == 1){ // has one child
                if (head.left == null) head.right = null;
                else if (head.right == null) head.left = null;
            }
            else { // has two or more
                Node temp;
                if (head.left.size < head.right.size){
                    temp = findMax(head.left);
                }
                else{
                    temp = findMin(head.right);
                }
                delete(head, temp.key, false);
                head.key = temp.key;
                head.value = temp.value;
            }
        } else if (comparison > 0) {
            head.right = delete(head.right, key, changeSize);
        } else {
            head.left = delete(head.left, key, changeSize);
        }
        return head;
    }

    private Node findMax(Node head){
        if (head.right != null){
            return findMax(head.right);
        }
        return head;
    }

    private Node findMin(Node head) {
        if (head.left != null){
            return findMax(head.left);
        }
        return head;
    }

    public Iterator<BST.KeyValueContainer> iterator(){
        return new MyIterator();
    }

    private class MyIterator implements Iterator<BST.KeyValueContainer>{

        private final Stack<KeyValueContainer> stack;

        public MyIterator(){
            this.stack = new Stack<>();
            inOrder(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public KeyValueContainer next() {
            return stack.pop();
        }

        private void inOrder(Node node){
            if (node == null){
                return;
            }
            inOrder(node.right);
            this.stack.push(new KeyValueContainer(node.key, node.value));
            inOrder(node.left);
        }
    }

    public class KeyValueContainer {
        private K key;
        private V value;

        public KeyValueContainer(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return '(' +
                    "key=" + key +
                    ", value=" + value +
                    ')';
        }
    }
}