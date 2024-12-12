import java.util.Comparator;

public class MaxArrayDeque<T> extends CircularArrayDeque<T> {
    private Comparator<T> comp;

    public MaxArrayDeque() {}

    public MaxArrayDeque(Comparator<T> c) {
        comp = c;
    }

    public T max() {
        if (size() == 0) return null;
        T ret = this.get(0);
        for (int i = 1; i < size(); i++) {
            if (comp.compare(ret, get(i)) > 0) ret = get(i);
        }
        return ret;
    }

    public T max(Comparator<T> c) {
        if (size() == 0) return null;
        T ret = this.get(0);
        for (int i = 1; i < size(); i++) {
            if (c.compare(get(i), ret) > 0) ret = get(i);
        }
        return ret;
    }

    public static void main(String[] args) {
        Comparator<Integer> ic = (o1 ,o2) -> (o1-o2);
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

        System.out.println(array1.max(ic));
    }
}