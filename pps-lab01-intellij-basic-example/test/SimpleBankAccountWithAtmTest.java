import lab01.example.model.AccountHolder;
import lab01.example.model.SimpleBankAccountWithAtm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SimpleBankAccountWithAtmTest extends SimpleBankAccountTest {

    @Override
    protected double getFee(){
        return SimpleBankAccountWithAtm.FEE;
    }

    @BeforeEach
    void beforeEach(){
        setAccountHolder(new AccountHolder("Mario", "Rossi", ACCOUNT_HOLDER_ID));
        setBankAccount(new SimpleBankAccountWithAtm(getAccountHolder(), INITIAL_BALANCE));
    }

    @Test
    void testDepositCannotPayFee(){
        getBankAccount().withdraw(ACCOUNT_HOLDER_ID, INITIAL_BALANCE - getFee());
        testCurrentBalanceValue(0);
        double smallerThanFee = getFee() / 2;
        getBankAccount().deposit(ACCOUNT_HOLDER_ID, smallerThanFee);
        testCurrentBalanceValue(0);
    }

    @Test
    void testWithdrawCannotPayFee(){
        getBankAccount().withdraw(ACCOUNT_HOLDER_ID, INITIAL_BALANCE - WITHDRAW - getFee());
        testCurrentBalanceValue(WITHDRAW);
        getBankAccount().withdraw(ACCOUNT_HOLDER_ID, WITHDRAW);
        testCurrentBalanceValue(WITHDRAW);
    }

}
