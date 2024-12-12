import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> implements Deque<T> {
    private T[] arr;
    protected int nextFirst;
    protected int nextLast;
    private int size;
    final protected int RFACTOR = 2;

    public MaxArrayDeque() {
        arr = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    public MaxArrayDeque(Comparator<T> c) {
        arr = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    public MaxArrayDeque(T[] a, int nF, int nL) {
        arr = a;
        nextFirst = nF;
        nextLast = nL;
        size = a.length;
    }

    public void addFirst(T item) {
        arr[nextFirst] = item;
        size++;
        if (nextFirst == nextLast) resize();
        else {
            nextFirst = Math.floorMod(nextFirst - 1, arr.length);
//            if (nextFirst < 0) nextFirst = arr.length - 1;
        }
    }

    public void addLast(T item) {
        arr[nextLast] = item;
        size++;
        if (nextLast == nextFirst) resize();
        else {
            nextLast++;
            if (nextLast >= arr.length) nextLast = 0;
        }
    }

    public T removeFirst() {
        int index = nextFirst + 1;
        if (index >= arr.length) index = 0;
        T tmp = arr[index];
        arr[index] = null;
        nextFirst = index;
        return tmp;
    }

    public T removeLast() {
        int index = nextLast - 1;
        if (index < 0) index = arr.length - 1;
        T tmp = arr[index];
        arr[index] = null;
        nextLast = index;
        return tmp;
    }

    private void resize() {
        T[] newArr = (T[]) new Object[arr.length * RFACTOR];
        int index = arr.length / 2;
        newArr[index] = arr[nextFirst];
        nextFirst++;
        index++;
        while (nextFirst != nextLast) {
            if (nextFirst >= arr.length) {
                nextFirst = 0;
            }
            newArr[index] = arr[nextFirst];
            nextFirst++;
            index++;
        }
        nextFirst = arr.length / 2 - 1;
        nextLast = nextFirst + arr.length + 1;
        arr = newArr;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index >= size) return null;
        int target = (nextFirst + index + 1) % arr.length;
        return arr[target];
    }

    public void printDeque() {
        System.out.println(this);
    }

    public T max() {
        return null;
    }

    public T max(Comparator<T> c) {
        return null;
    }

    @Override
    public String toString() {
        String ret = "{";
        for (int i = 0; i < size(); i++) {
            ret += get(i) + ", ";
        }
        ret = ret.substring(0, ret.length() - 2);
        ret += "}";
        return ret;
    }

    @Override
    public boolean equals(Object other) {
        MaxArrayDeque Deque = (MaxArrayDeque)  other;
        if (size != Deque.size()) return false;
        for (int i = 0; i < size(); i++) {
            if (!get(i).equals(Deque.get(i))) return false;
        }
        return true;
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public class ArrayDequeIterator implements Iterator<T> {
        int pos;
        public ArrayDequeIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return (pos < size());
        }

        public T next() {
            return arr[(nextFirst + pos++ + 1) % arr.length];
        }
    }

    public static void main(String[] args) {
        MaxArrayDeque<Integer> array1 = new MaxArrayDeque<Integer>();

        array1.addFirst(3);
        array1.addFirst(2);
        array1.addFirst(1);

        array1.printDeque();

        array1.addLast(4);
        array1.addLast(5);
        array1.addLast(6);
        array1.addLast(7);

        array1.printDeque();

        array1.addFirst(0);
        array1.addLast(8);
        array1.addFirst(-1);

        array1.printDeque();

        System.out.println(array1.get(4));
        System.out.println(array1);
        for (int i : array1) System.out.print(i + " ");
        System.out.println();
    }
}