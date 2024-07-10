import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {

  /**
   * The first name of the user
   */
  private String firstName;

  /**
   * The last name of the user
   */
  private String lastName;

  /**
   *  The ID Number of the user
   */
  private String uuid;

  /**
   * The MDS Hash of the user's pin
   */
  private byte pinHash[];

  /**
   * List of user's accounts
   */
  private ArrayList<Account> accounts;

  /**
   * Create a new user
   * @param firstName the user's first name
   * @param lastName  the user's last name
   * @param pin       the user's account pin
   * @param theBank   the Bank object that user is a customer of
   * @throws NoSuchAlgorithmException
   */
  public User ( String firstName, String lastName, String pin, Bank theBank) {

    //Set use name
    this.firstName = firstName;
    this.lastName = lastName;

    // Store the pin's MD hash, rather than the original value for security purposes
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      System.err.println("error, caught NoSuchAlgorithmException");
      e.printStackTrace();
      System.exit(1);
    }
    this.pinHash = md.digest(pin.getBytes());

    // Get a new, unique universal ID for the user
    this.uuid = theBank.getNewUserUUID();

    //Create an empty list of accounts
    this.accounts = new ArrayList<Account>();

    //print log message
    System.out.printf("New user %s, %s with ID %s created.\n",lastName, firstName, this.uuid);
  }

  /**
   * Add an account for the user
   * @param onAcct the account to add
   */
  public void addAccount(Account onAcct) {
    this.accounts.add(onAcct);
  }

  /**
   *  Return the user's UUID
   * @return the uuid
   */
  public String getUUID() {
    return this.uuid;
  }

  /**
   * Checks whether the given pin matches the true user pin
   * @param aPin the pin to check
   * @return whether the pin is valid or not
   */

  public boolean validatePin(String aPin){

    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      return MessageDigest.isEqual(md.digest(aPin.getBytes()),
          this.pinHash);
    } catch (NoSuchAlgorithmException e) {
      System.err.println("error, caught NoSuchAlgorithmException");
      e.printStackTrace();
      System.exit(1);
    }
    return false;
  }
}
