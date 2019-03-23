package List;


import java.util.Iterator;
import Node.Node;
import Excepciones.isEmptyException;


public class linkedList<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> head;
    private long length = 0;

    public linkedList(Node<T> _new) {
        this();
//        this.head.setNext(_new);
    }

    public linkedList() {
        this.head = new Node<>();
    }

     /**
     * 
     * @param value Valor para agregarlo en la lista
     * @return true falso si no se agrego verdadero si se agrego
     */    

   
     public boolean add(T value) {
        Node<T> _new = new Node<>(value);
        try {
            Node<T> tmp = null;
            isEmpty();
            tmp = getLastElement(this.head);
            tmp.setNext(_new);
            length++;
        } catch (isEmptyException e) {
            head.setNext(_new);
            length++;
        }
        return true;
    }

    private Node<T> getLastElement(Node<T> tmp) {
        if (tmp.getNext() == null) {
            return tmp;
        } else {
            return getLastElement(tmp.getNext());
        }
    }

    public boolean add(Node<T> node) {
        return add(node.getValue());
    }

    public boolean addAtStart(T value) {
        Node<T> _new = new Node<>(value);
        try {
            Node<T> tmp;
            isEmpty();
            tmp = head.getNext();
            head.setNext(_new);
            _new.setNext(tmp);
            length++;
        } catch (isEmptyException e) {
            head.setNext(_new);
            length++;
        }
        return true;
    }

    public boolean addAtStart(Node<T> node) {
        return addAtStart(node.getValue());
    }

    public boolean addAt(int position, T value) {
        try {
            isEmpty();
            if (position >= length) {
                return false;
            } else {
                Node<T> _new = new Node<>(value);
                Node<T> next = getElementAt(position);
                Node<T> prev = getElementAt((position - 1));
                prev.setNext(_new);
                _new.setNext(next);
                length++;
                return true;
            }
        } catch (isEmptyException e) {
            return false;
        }
    }

    public boolean addAt(Node<T> value, int position) {
        return addAt(position, value.getValue());
    }

    public boolean addAfter(T after,T value){
            try{
                isEmpty();
                Node<T> tmp,_new;
                if ( (tmp  = getPrevElement(head, after)) != null){
                    _new = new Node<T>(value);
                    _new.setNext(tmp.getNext().getNext());
                    tmp.getNext().setNext(_new);
                     length++;
                    System.gc();
                    return true;
                }else
                    return false;
            }catch(isEmptyException e){
                return false;
            }
        
    }

    public boolean addBefore(T before, T value) {
        try {
            if (isThere(head, value)) {
                isEmpty();
                Node<T> _new = new Node<>(value);
                Node<T> prev = getPrevElement(head, before);
                Node<T> next = prev.getNext();
                prev.setNext(_new);
                _new.setNext(next);
                length++;
                System.gc();
                return true;
            } else {
                return false;
            }
        } catch (isEmptyException e) {
            return false;
        }
    }

    public boolean removeAll(T value) {
        try {
            isEmpty();
            while (isThere(head, value)) {
                Node<T> tmp = getPrevElement(head, value);
                Node<T> tmp2 = tmp.getNext();
                tmp2.setValue(null);
                tmp.setNext(tmp.getNext().getNext());
                length--;
                System.gc();
            }
            return true;
        } catch (isEmptyException e) {
            return false;
        }
    }

    public boolean removeBefore(Node<T> node) {
        return removeBefore(node.getValue());
    }

    public boolean removeBefore(T value) {
        try {
            isEmpty();
            if (isThere(head, value))
                return remove(getPrevElement(head, value));
            return false;
        } catch (isEmptyException e) {
            return false;
        }
    }

    public boolean removeAfter(T value) {
        try {
            isEmpty();
            if (isThere(head, value)) {
                return remove(getPrevElement(head, value).getNext());
            } else {
                return false;
            }
        } catch (isEmptyException e) {
            return false;
        }
    }


    private boolean isThere(Node<T> node, T value) {
        if (node.getNext() == null) {
            return false;
        } else if (node.getNext().getValue().equals(value)) {
            return true;
        } else {
            return isThere(node.getNext(), value);
        }
    }

    private Node<T> getPrevElement(Node<T> node, T value) {
        if (node.getNext().getValue().equals(value)) {
            return node;
        } else {
            if (node.getNext() == null) {
                return null;
            } else {
                return getPrevElement(node.getNext(), value);
            }
        }
    }

    public boolean remove(T value) {
        try {
            if(value == null) return false;
            isEmpty();
            Node<T> tmp = getPrevElement(head, value);
            if(tmp.getValue() == null) return false;
            if (tmp != null) {
                tmp.setNext(tmp.getNext().getNext());
                length--;
                System.gc();
            }
            return true;
        } catch (isEmptyException e) {
            return false;
        }
    }

    public boolean remove(Node<T> node) {
        return remove(node.getValue());

    }

    public Node<T> getElementAt(int value) {
        return getElementAt(head, 0, value);
    }


    private Node<T> getElementAt(Node<T> node, int index, int value) {
        if (node.getNext() == null) {
            return null;
        } else {
            if (value == index) {
                return node.getNext();
            } else {
                if (index >= value) {
                    return null;
                } else {
                    return getElementAt(node.getNext(), ++index, value);
                }
            }
        }
    }

    public boolean isEmpty() throws isEmptyException {
        if (head.getNext() == null) {
            throw new isEmptyException("La lista está vacía");
        } else {
            return false;
        }
    }

    public long getLength() {
        return this.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            Node<T> cpy = head.getNext(), sub_head;

            @Override
            public boolean hasNext() {
                if (cpy == null) {
                    return false;
                } else {
                    sub_head = cpy;
                    cpy = cpy.getNext();
                    return true;
                }
            }

            @Override
            public T next() {
                return sub_head.getValue();
            }
        };
    }
}