package otbnb;

import java.util.regex.Pattern;

/**
 * Class - Delete
 * Allows an admin user to delete another current non-admin user and not
 * themselves
 */
public class Delete {
    /**
     * Admin user deletes another current non-admin user and not themselves
     * 
     * @return true
     */
    public static boolean deleteUser() {
        if (Session.isActiveSession()) {
            // must be a privileged user
            if (Session.getUser().getAccountType().equals("AA")) {
                Boolean isValid = false;

                while (!isValid) {
                    System.out.print("Enter username: ");
                    String username = Server.sc.nextLine();

                    // Check if username is less than 15 characters and has only valid character
                    while (!Pattern.matches("^[(a-zA-Z0-9 \\-_)]{1,15}$", username)) {
                        System.out.println(
                                "Invalid username: username must be less than 15 characters and can only contain A-z, numbers, - and _");
                        System.out.print("Enter username: ");
                        username = Server.sc.nextLine();
                    }

                    // Check if username is current user
                    if (username.equals(Session.getUser().getUsername())) {
                        System.out.println("You cannot delete your own account");
                    } else {
                        // Check if user already exists
                        for (User userinfile : Server.getCurrentUsers()) {
                            if (userinfile.getUsername().equals(username)) {
                                // check if user being deleted is an admin account
                                if (userinfile.getAccountType().equals("AA")) {
                                    System.out.println("You cannot delete another admin from the front end");
                                    break;
                                } else {
                                    // can be deleted
                                    isValid = true;
                                    Server.getCurrentUsers().remove(userinfile);
                                    System.out.println("Deleted user " + username);

                                    saveTransaction(userinfile);

                                    return true;
                                }
                            }
                        }

                        // if user does not exist
                        if (isValid) {
                            System.out.println("User does not exist");
                        }
                    }
                }
            } else {
                System.out.println("Error: Only admin users can delete an account");
            }
        } else {
            System.out.println("Cannot delete unless logged in");
        }
        return true;
    }

    /**
     * Adds delete transaction to the session transactions list
     * 
     * @param deletedUser the deleted user
     */
    public static void saveTransaction(User deletedUser) {
        // saves the user that has been deleted into daily transactions file
        String t = "02 " + deletedUser.toString() + " ".repeat(36) + "0 000000 00\n";
        Session.addTransaction(t);
    }
}
