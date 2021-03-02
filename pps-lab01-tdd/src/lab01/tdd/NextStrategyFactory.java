package lab01.tdd;

public interface NextStrategyFactory {

    SelectStrategy createEvenStrategy();

    SelectStrategy createMultipleOfStrategy(int multiple);

    SelectStrategy createEqualStrategy(int valueToMatch);

}
