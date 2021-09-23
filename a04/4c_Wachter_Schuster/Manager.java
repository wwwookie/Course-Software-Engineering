
/**@author Lukas Wachter, Alwin Schuster **/

import java.util.List;

/**
 * Manages Accounts, stores these accounts in list, can get Identity from
 * Accounts
 **/
public class Manager implements Ident {
    private List<Account> accounts;
    private List<Ident> idElems;
    private long id;

    private Manager() {}

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    @Override
    public long getId() {   return this.id; }
}