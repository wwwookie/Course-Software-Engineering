/** @author Lukas Wachter, Alwin Schuster **/

/**
 * Gets special conditions opposed to normal Accounts, inherits same attributes
 **/
public class StudentAccount extends Account {
    University university;

    public StudentAccount(String name) {    super(name);    }

    // other conditions?
    public void withDraw(double value) {    super.withDraw(value);  }
}
