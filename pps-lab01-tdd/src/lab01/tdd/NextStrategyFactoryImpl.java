package lab01.tdd;

public class NextStrategyFactoryImpl implements NextStrategyFactory{

    @Override
    public SelectStrategy createMultipleOfStrategy(final int multiple) {
        return element -> (element % multiple) == 0;
    }

    @Override
    public SelectStrategy createEvenStrategy() {
        return createMultipleOfStrategy(2);
    }

    @Override
    public SelectStrategy createEqualStrategy(final int valueToMatch){
        return element -> element == valueToMatch;
    }
}

