package com.design.pattern.Iterator;

import java.util.Iterator;
import java.util.Stack;

class Node<T>
{
    public T value;
    public Node<T> left, right, parent;

    public Node(T value)
    {
        this.value = value;
    }

    public Node(T value, Node<T> left, Node<T> right)
    {
        this.value = value;
        this.left = left;
        this.right = right;

        left.parent = right.parent = this;
    }

    public Iterator<Node<T>> preOrder()
    {
        Stack<Node<T>> stack = new Stack<>();
        iterativePreorder(this, stack);
        return stack.iterator();
        // todo
    }

    public void iterativePreorder(Node<T> node, Stack<Node<T>> stack){
        stack.add(node);
        while (!stack.isEmpty()){
            Node<T> temp = stack.pop();
            if (temp.right != null){
                stack.push(temp.right);
            }
            if (temp.left != null){
                stack.push(temp.left);
            }
        }
    }
}

public class ExerciseIterator {
}
