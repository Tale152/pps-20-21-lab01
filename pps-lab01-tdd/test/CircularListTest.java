import lab01.tdd.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private static final int TEST_REPETITIONS = 100;

    private final NextStrategyFactory factory = new NextStrategyFactoryImpl();
    private final SelectStrategy evenStrategy = factory.createEvenStrategy();
    private final SelectStrategy multipleOfFiveStrategy = factory.createMultipleOfStrategy(5);
    private final SelectStrategy equalToTwoStrategy = factory.createEqualStrategy(2);

    private CircularList circularList;

    @BeforeEach
    public void beforeEach(){
        circularList = new CircularListImpl();
    }

    public void assertCurrentSize(final int size){
        assertEquals(circularList.size(), size);
    }

    @Test
    public void testIsEmpty(){
        assertCurrentSize(0);
        assertTrue(circularList.isEmpty());
        assertTrue(circularList.next().isEmpty());
        assertTrue(circularList.previous().isEmpty());
    }

    public void populateListWithOrderedIntegers(final int boundary){
        for(int i = 0; i < boundary; i++){
            circularList.add(i);
        }
    }

    @Test
    public void testSize(){
        testIsEmpty();
        populateListWithOrderedIntegers(TEST_REPETITIONS);
        assertCurrentSize(TEST_REPETITIONS);
    }

    @Test
    public void testNext(){
        populateListWithOrderedIntegers(TEST_REPETITIONS);
        for(int i = 0; i < TEST_REPETITIONS; i++){
            assertEquals(circularList.next(), Optional.of(i));
        }
        assertEquals(circularList.next(), Optional.of(0));
    }

    private void oneElementSetup(final int valueToAdd){
        testIsEmpty();
        circularList.add(valueToAdd);
        assertCurrentSize(1);
    }

    @Test
    public void testNextWithOneElement(){
        int valueToAdd = 0;
        oneElementSetup(valueToAdd);
        assertEquals(circularList.next(), Optional.of(valueToAdd));
        assertEquals(circularList.next(), Optional.of(valueToAdd));
    }

    @Test
    public void testPrevious(){
        populateListWithOrderedIntegers(TEST_REPETITIONS);
        for(int i = TEST_REPETITIONS; i > 0; i--){
            assertEquals(circularList.previous(), Optional.of(i-1));
        }
        assertEquals(circularList.previous(), Optional.of(TEST_REPETITIONS-1));
    }

    @Test
    public void testPreviousWithOneElement(){
        int valueToAdd = 0;
        oneElementSetup(valueToAdd);
        assertEquals(circularList.previous(), Optional.of(valueToAdd));
        assertEquals(circularList.previous(), Optional.of(valueToAdd));
    }

    @Test
    public void testReset(){
        populateListWithOrderedIntegers(TEST_REPETITIONS);
        for(int i = 0; i < TEST_REPETITIONS; i++){
            assertEquals(circularList.next(), Optional.of(0));
            assertEquals(circularList.next(), Optional.of(1));
            circularList.reset();
        }
        for(int i = 0; i < TEST_REPETITIONS; i++){
            assertEquals(circularList.previous(), Optional.of(TEST_REPETITIONS - 1));
            assertEquals(circularList.previous(), Optional.of(TEST_REPETITIONS - 2));
            circularList.reset();
        }
        assertCurrentSize(TEST_REPETITIONS);
    }

    @Test
    public void testNextEvenStrategy(){
        assertEquals(circularList.next(evenStrategy), Optional.empty());
        populateListWithOrderedIntegers(TEST_REPETITIONS);
        for(int i = 0; i < TEST_REPETITIONS; i+=2){
            assertEquals(circularList.next(evenStrategy), Optional.of(i));
        }
        assertEquals(circularList.next(evenStrategy), Optional.of(0));
    }

    @Test
    public void testNextEvenStrategyWithoutCorrectValues(){
        circularList.add(1);
        assertEquals(circularList.next(evenStrategy), Optional.empty());
        assertEquals(circularList.next(evenStrategy), Optional.empty());
    }

    @Test
    public void testNextMultipleOfFiveStrategy(){
        assertEquals(circularList.next(multipleOfFiveStrategy), Optional.empty());
        populateListWithOrderedIntegers(TEST_REPETITIONS);
        for(int i = 0; i < TEST_REPETITIONS; i+=5){
            assertEquals(circularList.next(multipleOfFiveStrategy), Optional.of(i));
        }
        assertEquals(circularList.next(multipleOfFiveStrategy), Optional.of(0));
    }

    @Test
    public void testMultipleOfFiveStrategyWithoutCorrectValues(){
        circularList.add(1);
        assertEquals(circularList.next(multipleOfFiveStrategy), Optional.empty());
        assertEquals(circularList.next(multipleOfFiveStrategy), Optional.empty());
    }

    @Test
    public void testEqualToTwoStrategy(){
        assertEquals(circularList.next(equalToTwoStrategy), Optional.empty());
        populateListWithOrderedIntegers(TEST_REPETITIONS);
        assertEquals(circularList.next(equalToTwoStrategy), Optional.of(2));
        assertEquals(circularList.next(equalToTwoStrategy), Optional.of(2));
    }

    @Test
    public void testEqualToTwoStrategyWithoutCorrectValues(){
        circularList.add(1);
        assertEquals(circularList.next(equalToTwoStrategy), Optional.empty());
        assertEquals(circularList.next(equalToTwoStrategy), Optional.empty());
    }

}
