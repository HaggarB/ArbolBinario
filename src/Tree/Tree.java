package Tree;

import Excepciones.isEmptyException;
import Node.Node;

public interface Tree<T extends Comparable<T>> {

    boolean add (T value);
    
    boolean add (Node<T> node);

    int between(T start, T end);

    T biggest() throws isEmptyException;
    
    int width () throws isEmptyException;

    int heightUp() throws isEmptyException; //altura en ascenso
    
    int heightDown()throws isEmptyException; //altura en descenso

    boolean isEmpty() throws isEmptyException;

    void lvlUpdate(Node<T> root);

    public T minor() throws isEmptyException;

    Node<T> minor(Node<T> node) throws isEmptyException;

    void inOrder() throws isEmptyException;
    
    void postOrder() throws isEmptyException;

    void preOrder() throws isEmptyException;

    boolean remove(T value) throws isEmptyException;

    Node<T> search(T value) throws isEmptyException;
    
    void breadthFirstTraversal();
}
