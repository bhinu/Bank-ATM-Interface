import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Random;

public class Bank {

  private String name;

  private ArrayList<User> users;

  private ArrayList<Account> accounts;

  /**
   * Create a new Bank object with empty lists of users and accounts
   * @param name the name of the Bank
   */

  public Bank(String name){

    this.name = name;
    this.users = new ArrayList<User>();
    this.accounts = new ArrayList<Account>();
  }

  /**
   * Generate a new universally unique ID for a user
   * @return the uuid
   */
  public String getNewUserUUID() {
    // inits
    String uuid;
    Random rng = new Random();
    int len = 6;
    boolean nonUnique;

    // continue looping till we get a unique ID
    do {

      // generate the number
      uuid = "";
      for (int c = 0; c < len; c++){
        uuid += ((Integer)rng.nextInt(10)).toString();
      }

      // Check to make sure its unique
      nonUnique = false;
      for (User u : this.users){
        if (uuid.compareTo(u.getUUID()) == 0){
          nonUnique = true;
          break;
        }
      }

    } while (nonUnique);

    return uuid;

  }

  /**
   *
   * @return
   */

  public String getNewAccountUUID() {
    // inits
    String uuid;
    Random rng = new Random();
    int len = 10;
    boolean nonUnique;

    // continue looping till we get a unique ID
    do {

      // generate the number
      uuid = "";
      for (int c = 0; c < len; c++){
        uuid += ((Integer)rng.nextInt(10)).toString();
      }

      // Check to make sure its unique
      nonUnique = false;
      for (Account a : this.accounts){
        if (uuid.compareTo(a.getUUID()) == 0){
          nonUnique = true;
          break;
        }
      }

    } while (nonUnique);

    return uuid;

  }

  /**
   * Add an account
   * @param onAcct the account to add
   */
  public void addAccount(Account onAcct) {
    this.accounts.add(onAcct);
  }

  public User addUser(String firstName, String lastName, String pin)
      throws NoSuchAlgorithmException {

    // Create a new User Object and add it to our list
    User newUser = new User(firstName, lastName, pin, this);
    this.users.add(newUser);

    // Create a savings account for the user
    Account newAccount = new Account("Savings",newUser,this);
    newUser.addAccount(newAccount);
    this.addAccount(newAccount);

    return newUser;

  }

  /**
   * Get the user object associated with a particular userID and pin, if they are valid
   * @param userID  the UUID of the user to log in
   * @param pin     the pin of the user
   * @return        The User object, if the login successful, or null, if it is not
   */

  public User userLogin (String userID, String pin){

    // Search through list of users
    for (User u : this.users){

      // Check user ID is correct
      if (u.getUUID().compareTo(userID) == 0 && u.validatePin(pin)){
        return u;
      }
    }

    // If we haven't found the user or have an incorrect pin
    return null;
  }
}
