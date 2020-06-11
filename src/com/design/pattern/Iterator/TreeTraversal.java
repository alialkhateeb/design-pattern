package com.design.pattern.Iterator;

import java.text.NumberFormat;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/*
 *
 * In this example, we will build a binary tree in term of node
 */
class Node<T> {
    public T value;
    public Node<T> left, right, parent;

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;

        left.parent = right.parent = this;
    }
}

//now we are going to build iterator which allows for in-order traversal of this binary tree
class InOrderIterator<T> implements Iterator<T> {

    //in order to be able to traverse the binary tree, we need couple of references
    private Node<T> current, root;

    /*
     * tell us whether or not we've yielded the start element
     * the reason why for the flag - iteration does not start from the root, it start from the leftmost node
     *
     */
    private boolean yieldedStart;

    public InOrderIterator(Node<T> root) {
        this.root = this.current = root;
        //current will be changed to point to the leftmost element in the tree
        while (current.left != null) {
            this.current = current.left;
        }
    }

    private boolean hasRightmostParent(Node<T> node) {
        if (node.parent == null) return false;
        else {
            return (node == node.parent.left) || hasRightmostParent(node.parent);
        }
    }

    @Override
    public boolean hasNext() {
        return current.left != null || current.right != null || hasRightmostParent(current);
    }

    @Override
    public T next() {
        if (!yieldedStart) {
            yieldedStart = true;
            return current.value;
        }
        if (current.right != null) {
            current = current.right;
            while (current.left != null) {
                current = current.left;
            }
            return current.value;
        } else {
            Node<T> temp = current.parent;
            while (temp != null && current == temp.right) {
                current = temp;
                temp = temp.parent;
            }
            current = temp;
            return current.value;
        }
    }
}

class BinaryTree<T> implements Iterable<T> {

    private Node<T> root;

    public BinaryTree(Node<T> root) {
        this.root = root;
    }

    @Override
    public Iterator<T> iterator() {
        return new InOrderIterator<>(root);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        for (T item : this) {
            action.accept(item);
        }
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}

public class TreeTraversal {
    public static void main(String[] args) {
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(true);
        Node<Integer> root = new Node<>(1,
                new Node<>(2, new Node<>(4), new Node<>(5)),
                new Node<>(3, new Node<>(6), new Node<>(7)));

        BinaryTree<Integer> tree = new BinaryTree<>(root);

        System.out.println("\nforeach loop");
        long start2 = System.nanoTime();
        tree.forEach(temp -> {
            System.out.print(" " + temp + ", ");
        });
//        for (Integer temp :tree) {
//            System.out.print(" " + temp + ", ");
//        }
        long finish2 = System.nanoTime();

        System.out.println( "\ntime elapse foreach loop = " + format.format(finish2-start2));

        InOrderIterator<Integer> iterator = new InOrderIterator<>(root);

        System.out.println("While loop");
        long start = System.nanoTime();
        while (iterator.hasNext()) {
            System.out.print(" "+ iterator.next() + ", ");
        }
        long end = System.nanoTime();
        System.out.println( "\ntime elapse while loop = " + format.format(end - start));



    }
}
