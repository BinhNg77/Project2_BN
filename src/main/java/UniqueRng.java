import java.util.*;

public class UniqueRng implements Iterator<Integer> {
    private ArrayList<Integer> numbers = new ArrayList<>();
    public ArrayList<Integer> randomNumbers = new ArrayList<>();

    public ArrayList<Integer> ArrayListUniqueRng(int n, int range) {

        for (int i = 1; i <= range; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        for (int j = 0; j < n; j ++ ){
            randomNumbers.add(numbers.get(j));
        }
        return randomNumbers;
    }

    @Override
    public boolean hasNext() {
        return !numbers.isEmpty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers.remove(0);
    }
}