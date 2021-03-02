import lab01.example.model.AccountHolder;
import lab01.example.model.BankAccount;
import lab01.example.model.SimpleBankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    public static final int ACCOUNT_HOLDER_ID = 1;
    public static final double INITIAL_BALANCE = 100;
    public static final double DEPOSIT = 10;
    public static final double WITHDRAW = 20;

    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    public AccountHolder getAccountHolder(){
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public BankAccount getBankAccount(){
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount){
        this.bankAccount = bankAccount;
    }

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", ACCOUNT_HOLDER_ID);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testAccountHolder(){
        assertEquals(bankAccount.getHolder(), accountHolder);
    }

    public void testCurrentBalanceValue(final double expectedBalance){
        assertEquals(expectedBalance, bankAccount.getBalance());
    }

    @Test
    void testInitialBalance() {
        testCurrentBalanceValue(INITIAL_BALANCE);
    }

    protected double getFee(){
        return SimpleBankAccount.FEE;
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(ACCOUNT_HOLDER_ID, DEPOSIT);
        testCurrentBalanceValue(INITIAL_BALANCE + DEPOSIT - getFee());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(ACCOUNT_HOLDER_ID + 1, 50);
        testCurrentBalanceValue(INITIAL_BALANCE);
    }

    @Test
    void testWithdraw() {
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW);
        testCurrentBalanceValue(INITIAL_BALANCE - WITHDRAW - getFee());
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.withdraw(ACCOUNT_HOLDER_ID + 1, 70);
        testCurrentBalanceValue(INITIAL_BALANCE);
    }

}
