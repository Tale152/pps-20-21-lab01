package lab01.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CircularListImpl implements CircularList {

    private final List<Integer> list;
    private int index;

    public CircularListImpl() {
        list = new ArrayList<>();
        index = 0;
    }

    @Override
    public void add(int element) {
        list.add(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Optional<Integer> next() {
        if(!isEmpty()){
            int result = index++;
            if(index == list.size()){
                reset();
            }
            return Optional.of(list.get(result));
        }
        return Optional.empty();
    }

    private void setIndexToLast(){
        index = list.size() - 1;
    }

    @Override
    public Optional<Integer> previous() {
        if(!isEmpty()){
            if(--index < 0){
                setIndexToLast();
            }
            return Optional.of(list.get(index));
        }
        return Optional.empty();
    }

    @Override
    public void reset() {
        index = 0;
    }

    @Override
    public Optional<Integer> next(SelectStrategy strategy) {
        int startingIndex = index;
        if(!isEmpty()){
            do{
                Optional<Integer> nextElement = next();
                if(nextElement.isPresent() && strategy.apply(nextElement.get())){
                    return nextElement;
                }
            } while (startingIndex != index);
        }
        return Optional.empty();
    }
}
