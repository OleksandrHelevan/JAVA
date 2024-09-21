package task5;

public class Pair<F extends Comparable<F>,S> implements Comparable<Pair<F,S>> {
    private final F first;
    private final S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "[ " + first + " ; " + second + " ]";
    }

    @Override
    public int compareTo(Pair<F, S> other) {
        return this.first.compareTo(other.first);
    }

    public Pair<F,S> compare(Pair<F,S> other) {
        if(compareTo(other) > 0){
            return this;
        }else if(compareTo(other) < 0) {
            return other;
        }else {
            return null;
        }
    }
}



