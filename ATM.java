import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
public class ATM {
  public static void main(String[] args) {
    //init Scanner
    Scanner sc = new Scanner(System.in);

    //init Bank
    Bank theBank = new Bank("Da Bank");

    //add user to the bank, which also creates a savings account
    User aUser;

    {
      try {
        aUser = theBank.addUser("John", "Doe", "1234");
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e);
      }
    }

    //add a checking account
    Account newAccount = new Account("Checking", aUser, theBank);
    aUser.addAccount(newAccount);
    theBank.addAccount(newAccount);

    User curUser;
    while (true){

      //stay in the login prompt until successful login
      curUser = ATM.mainMenuPrompt(theBank, sc);

      //stay in the main menu until the user quits
      ATM.printUserMenu(curUser,sc);
    }
  }



}
