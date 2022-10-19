package otbnb;

import java.util.*;
import java.util.regex.Pattern;

/**
 * Class - Session
 * Starts the front-end session
 */
public class Session {
    private static User user;
    private static ArrayList<String> transactions = new ArrayList<String>();
    private static boolean activeSession = false;

    /**
     * Get the transactions.
     * 
     * @return transactions
     */
    public static ArrayList<String> getTransactions() {
        return transactions;
    }

    /**
     * Adds an already formatted String to be later saved to the daily transaction
     * file.
     * 
     * @param transactionString transaction
     * @return true if transaction is added successfully, false otherwise
     */
    public static boolean addTransaction(String transactionString) {
        transactions.add(transactionString);
        return true;
    }

    /**
     * Get the user.
     * 
     * @return user
     */
    public static User getUser() {
        return user;
    }

    /**
     * Get whether this is the current session.
     * 
     * @return true if this is the current session, false otherwise
     */
    public static boolean isActiveSession() {
        return activeSession;
    }

    /**
     * Get user input for login
     * 
     * @return true if user is logged in
     */
    public static boolean login() {
        String username = "";

        // user can only login if they are logged out
        while (!activeSession) {
            System.out.print("Enter username: ");
            username = Server.sc.nextLine();
            while (!Pattern.matches("^[(a-zA-Z0-9 \\-_)]{1,15}$", username)) {
                System.out.println(
                        "Invalid username: username must be less than 15 characters and can only contain A-z, numbers, - and _");
                System.out.print("Enter username: ");
                username = Server.sc.nextLine();
            }

            // check if user exists
            for (User userinfile : Server.getCurrentUsers()) {
                if (userinfile.getUsername().equals(username)) {
                    System.out.println("You have been logged in");
                    user = userinfile;
                    activeSession = true;
                    return activeSession;
                }
            }
            if (!activeSession) {
                System.out.println("User not found");
            }
        }
        if (activeSession) {
            System.out.println("User already logged in");
        }

        return activeSession;
    }

    /**
     * Determines if user is logged out, adds logout transaction
     * 
     * @return false if user is logged out
     */
    public static boolean logout() {
        if (activeSession == false) {
            System.out.println("Error: You must be logged in to log out");
        } else {
            String t = "00 " + user.toString() + " ".repeat(36) + "0 000000 00\n";
            addTransaction(t);

            activeSession = false;
            System.out.println("You have been logged out");
            Server.saveDailyTransactions();
        }

        return activeSession;
    }

    /**
     * Sets activeSession
     * 
     * @param b
     */
    public static void setActiveSession(boolean b) {
        activeSession = b;
    }

    /**
     * Sets the active user
     * 
     * @param user2
     */
    public static void setUser(User user2) {
        user = user2;
    }
}
