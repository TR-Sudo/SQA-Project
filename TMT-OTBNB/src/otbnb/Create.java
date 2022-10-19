package otbnb;
import java.util.regex.Pattern;

/**
 * Class - Create
 * Allows an admin user to create another user
 */
public class Create {
    /**
     * Admin user creates another user by inputing username and account type
     * 
     * @return true
     */
    public static boolean createUser() {
        String username = "";
        String accountType = "";
        Boolean usernameIsValid = false;
        Boolean accountTypeIsValid = false;

        if (Session.isActiveSession()) {
            if (Session.getUser().getAccountType().equals("AA")) {
                while (!usernameIsValid) {
                    System.out.print("Enter username: ");
                    username = Server.sc.nextLine();

                    // Check if username is less than 15 characters and has only valid character
                    while (!Pattern.matches("^[(a-zA-Z0-9 \\-_)]{1,15}$", username)) {
                        System.out.println("Invalid username: username must be less than 15 characters and can only contain A-z, numbers, - and _");
                        System.out.print("Enter username: ");
                        username = Server.sc.nextLine();
                    }

                    usernameIsValid = true;

                    // Check if user already exists
                    for (User userinfile : Server.getCurrentUsers()) {
                        if (userinfile.getUsername().equals(username)) {
                            System.out.println("Username already taken");
                            usernameIsValid = false;
                        }
                    }
                }

                // check if account type entered is valid
                while (!accountTypeIsValid) {
                    System.out.print("Enter account type: ");
                    accountType = Server.sc.nextLine();
                    String[] validAccountTypes = { "AA", "FS", "RS", "PS" };

                    for (String type : validAccountTypes) {
                        if (type.equals(accountType)) {
                            accountTypeIsValid = true;
                            break;
                        }
                    }

                    if (!accountTypeIsValid) {
                        System.out.println("Error: Account type not valid");
                    }
                }

                System.out.println("Created user " + username + " with account type " + accountType);

                // saves to the Server, does not update user accounts file
                User user = new User(username, accountType);
                saveTransaction(user);
            } else {
                System.out.println("Error: Only admin users can create an account");
            }
        } else {
            System.out.println("Cannot create unless logged in");
        }
        return true;
    }

    /**
     * Adds create transaction to the session transactions list
     * 
     * @param createdUser new user that is being created
     */
    public static void saveTransaction(User createdUser) {
        String t = "01 " + createdUser.toString() + " ".repeat(36) + "0 000000 00\n";
        Session.addTransaction(t);
    }
}
