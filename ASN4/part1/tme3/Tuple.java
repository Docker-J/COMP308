package tme3;

public class Tuple<T, S> {
    private T key;
    private S value;

    public Tuple(T key, S value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public S getValue() {
        return value;
    }

    public void setValue(S value) {
        this.value = value;
    }
}
