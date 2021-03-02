package lab01.example.model;

public class SimpleBankAccount extends BankAccountAbstract{

    public static double FEE = 0;

    public SimpleBankAccount(AccountHolder holder, double balance) {
        super(holder, balance, FEE);
    }

}
