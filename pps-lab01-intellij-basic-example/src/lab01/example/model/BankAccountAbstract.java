package lab01.example.model;

public abstract class BankAccountAbstract implements BankAccount{

    private double balance;
    private final AccountHolder holder;
    private final double fee;

    public BankAccountAbstract(final AccountHolder holder, final double balance, final double fee) {
        this.holder = holder;
        this.balance = balance;
        this.fee = fee;
    }

    @Override
    public AccountHolder getHolder(){
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }

    private boolean canPayFee(final double amountToDeposit){
        return amountToDeposit >= fee || getBalance() - fee >= 0;
    }

    private void applyFee(){
        balance -= fee;
    }

    @Override
    public void deposit(final int usrID, final double amount) {
        if (checkUser(usrID) && canPayFee(amount)) {
            this.balance += amount;
            applyFee();
        }
    }

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount + fee;
    }

    @Override
    public void withdraw(final int usrID, final double amount) {
        if (checkUser(usrID) && isWithdrawAllowed(amount)) {
            this.balance -= amount;
            applyFee();
        }
    }

}
