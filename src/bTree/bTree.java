package bTree;

import Excepciones.isEmptyException;
import Node.Node;
import java.util.logging.Level;
import java.util.logging.Logger;
import Tree.Tree;


public class bTree<T extends Comparable<T>> implements Tree<T> {

    public Node<T> root;
    public int contNode;
    int height;

    public bTree(Node<T> node) {
        this(node.getValue());
    }

    public bTree(){
        root = new Node<>();
        root.setCont(0);
        root.setHeight(0);
        root.setLevel(0);
    }

    public bTree(T value) {
        root = new Node<>(value);
        root.setCont(0);
        root.setHeight(0);
        root.setLevel(0);
    }

    @Override
    public boolean isEmpty() throws isEmptyException {
        if (root == null) {
            throw new isEmptyException("Tree is empty");
        } else {
            return false;
        }
    }

    @Override
    public boolean add(T value) {
        if (value == null) return false;
        else if (root.getValue() == null) {
            root.setValue(value);
            return true;
        } else add(root, value, root.getLevel());
        contNode++;
        return true;
    }

    public Node<T> add(Node<T> node, T value, int level) {
        if (node == null) {
            Node<T> nod = new Node(value);
            nod.setLevel(level);
            nod.setCont(0);
            return nod;
        }
        if (value.compareTo(node.getValue()) < 0) {
            node.setPrev(add(node.getPrev(), value, level + 1));
        } else if (value.compareTo(node.getValue()) > 0) {
            node.setNext(add(node.getNext(), value, level + 1));
        } else {
            node.setCont(node.getCont() + 1);
        }
        lvlUpdate(node);
        return node;
    }

    public void lvlUpdate(Node<T> node) {
        int PrevNodeHeight = (node.getPrev() == null) ? -1 : node.getPrev().getHeight();
        int NextNodeHeight = (node.getNext() == null) ? -1 : node.getNext().getHeight();
        node.setHeight(1 + Math.max(PrevNodeHeight, NextNodeHeight));
        node.setbreadthFirst(NextNodeHeight - PrevNodeHeight);
    }

    @Override
    public boolean add(Node<T> node) {
        return add(node.getValue());
    }

    @Override
    public boolean remove(T value) {
        boolean isThere = false;
        Node<T> tmp = search(value);
        if (tmp != null) {
            isThere = true;
            remove(tmp, value);
            contNode--;
        }
        return isThere;
    }

    public Node<T> remove(Node<T> current, T value) {
        if (current.getCont() > 0) {
            current.setCont(current.getCont() - 1);
            return current;
        } else {
            if (current.getPrev() == null && current.getNext() == null) {
                return null;
            }
            if (current.getNext() == null) {
                return current.getPrev();
            }
            if (current.getPrev() == null) {
                return current.getNext();
            }
            T smallest = minor(current.getNext()).getValue();
            current.setValue(smallest);
            current.setNext(remove(current.getNext(), smallest));
            lvlUpdate(current);
            return current;
        }
    }

    @Override
    public Node<T> search(T value) {
        return search(root, value);
    }

    public Node<T> search(Node<T> node, T value) {
        if (node == null) return null;
        if (value.equals(node.getValue())) return node;
        return value.compareTo(node.getValue()) < 0 ? search(node.getPrev(), value) : search(node.getNext(), value);
    }

    @Override
    public T biggest() {
        return biggest(root);
    }

    public T biggest(Node<T> node) {
        return node.getNext() == null ? node.getValue() : biggest(node.getNext());
    }

    @Override
    public T minor() {
        return minor(root).getValue();
    }

    public Node<T> minor(Node<T> node) {
        return node.getPrev() == null ? node : minor(node.getPrev());
    }

    @Override
    public void preOrder() {
        preOrder(root);
    }

    @Override
    public void postOrder() {
        postOrder(root);
    }

    @Override
    public void inOrder() {
        inOrder(root);
    }

    public void preOrder(Node<T> node) {
        if (node != null) {
            System.out.print(" " + node.getValue() + "{" + node.getLevel() + "," + node.getCont() + "}");
            preOrder(node.getPrev());
            preOrder(node.getNext());
        }
    }

    public void postOrder(Node<T> node) {
        if (node != null) {
            postOrder(node.getPrev());
            postOrder(node.getNext());
            System.out.print(" " + node.getValue() + "{" + node.getLevel() + "," + node.getCont() + "}");
        }
    }

    public void inOrder(Node<T> node) {
        if (node != null) {
            inOrder(node.getPrev());
            System.out.print(" " + node.getValue() + "{" + node.getLevel() + "," + node.getCont() + "}");
            inOrder(node.getNext());
        }
    }
   
    @Override
    public int heightUp() {
        height = 0;
        height(root, 1);
        return height;
    }

    @Override
    public int heightDown() {
        return root.getHeight();
    }
    
    public void printLevel(int level){
        printLevel(root,level);
    }

    public void height(Node<T> reco, int nivel) {
        if (reco != null) {
            reco.setLevel(nivel - 1);
            height(reco.getPrev(), nivel + 1);
            if (nivel > height) {
                height = nivel;
            }
            reco.setLevel(nivel - 1);
            height(reco.getNext(), nivel + 1);
        }
    }

    public void printPorNivel()
    {
        int a = heightUp();
        int i;
        for (i=1; i<=a; i++) {
            System.out.print("Nivel " + i + " :");
            printLevel(root, i);
            System.out.println();
        }
    }

    public void printLevel (Node<T> root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.getValue() + " ");
        else if (level > 1)
        {
            printLevel(root.getPrev(), level-1);
            printLevel(root.getNext(), level-1);
        }
    }

    @Override
    public int width() {
        return this.contNode;
    }

    @Override
    public int between(T start, T end) {
        return getCont(root, start, end);
    }

    public int getCont(Node<T> node, T low, T high) {
        if (node == null) {
            return 0;
        }
        if (node.getValue().compareTo(low) > 0 || node.getValue().equals(low)
                && node.getValue().compareTo(high) < 0 || node.getValue().equals(high)) {
            return (1 + node.getCont()+ this.getCont(node.getPrev(), low, high)
                    + this.getCont(node.getNext(), low, high));
        } else if (node.getValue().compareTo(low) < 0) {
            return this.getCont(node.getNext(), low, high);
        } else {
            return this.getCont(node.getPrev(), low, high);
        }
    }

    @Override
    public String toString() {
        return TreePrinter.getTreeDisplay(root);
    }
    
    @Override
    public void breadthFirstTraversal() {
        printPorNivel();
    }

}