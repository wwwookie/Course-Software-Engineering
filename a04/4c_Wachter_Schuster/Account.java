/** @author Lukas Wachter, Alwin Schuster **/

/** Account makes it possible to transfer money between other Accounts **/
public class Account implements Ident {
    private static int AccountCounts;
    private String name;
    private double money;
    private Manager manager;
    private long id;

    public Account(String name) {   this.name = name;   }

    public void deposit(double value) { this.money += value;    }

    public void withDraw(double value) {    this.money -= value;    }

    public String getName() {   return this.name;   }

    @Override
    public long getId() {   return this.id; }
}
