import java.util.ArrayList;

public class Account {
  /**
   *  Name of the account
   */
  private String name;

  /**
   * Account ID Number
   */
  private String uuid;

  /**
   * The user object that holds ths account
   */
  private User holder;

  /**
   * The list of transactions for this account
   */
  private ArrayList<Transaction> transactions;

  /**
   * Creates a new account
   * @param name    the name of the account
   * @param holder  the User object that holds this account
   * @param theBank the Bank that issues this account
   */

  public Account( String name, User holder, Bank theBank ){

    // Set Account name and holder
    this.name = name;
    this.holder = holder;

    // Get new account uuid
    this.uuid = theBank.getNewAccountUUID();

    // init transactions
    this.transactions = new ArrayList<Transaction>();
  }

  /**
   * Get the Account ID
   * @return the uuid
   */

  public String getUUID() {
    return this.uuid;
  }
}
