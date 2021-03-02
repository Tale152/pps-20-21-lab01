package lab01.example.model;

public class SimpleBankAccountWithAtm extends BankAccountAbstract {

    public static double FEE = 1;

    public SimpleBankAccountWithAtm(AccountHolder holder, double balance) {
        super(holder, balance, FEE);
    }

}
