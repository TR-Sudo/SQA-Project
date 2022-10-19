package otbnb;

import java.util.*;
import java.io.*;

/**
 * Class - Server
 * Starts the front-end OT-BNB
 */
public class Server {
    public static Scanner sc;

    private static ArrayList<User> currentUsers = new ArrayList<User>();
    private static ArrayList<RentalUnit> availableUnits = new ArrayList<RentalUnit>();
    private static String transactionsFilename;

    public static void main(String[] args) throws Exception {
        if (args.length == 3) {
            sc = new Scanner(System.in);
            String transaction = "";

            Server.loadCurrentUsers(args[0]);
            Server.loadRentals(args[1]);
            transactionsFilename = args[2];

            while (!transaction.equalsIgnoreCase("quit")) {
                System.out.print("Enter transaction: ");

                transaction = sc.nextLine();

                if (transaction.equalsIgnoreCase("login")) {
                    Session.login();
                } else if (transaction.equalsIgnoreCase("logout")) {
                    Session.logout();
                } else if (transaction.equalsIgnoreCase("create")) {
                    Create.createUser();
                } else if (transaction.equalsIgnoreCase("delete")) {
                    Delete.deleteUser();
                } else if (transaction.equalsIgnoreCase("search")) {
                    Search.searchRentalUnit();
                } else if (transaction.equalsIgnoreCase("rent")) {
                    Rent.rentUnit();
                } else if (transaction.equalsIgnoreCase("post")) {
                    Post.postRentalUnit();
                }
            }
            sc.close();
        } else {
            System.out.println("Expected 3 arguments, <user file> <rentalunit file> <transaction file>");
        }

    }

    /**
     * Get the current users.
     * 
     * @return ArrayList of Users
     */
    public static ArrayList<User> getCurrentUsers() {
        return currentUsers;
    }

    /**
     * Get the available units.
     * 
     * @return ArrayList of RentalUnits
     */
    public static ArrayList<RentalUnit> getAvailableUnits() {
        return availableUnits;
    }

    /**
     * Load the current users.
     * 
     * @return true if loaded correctly, false otherwise
     */
    public static boolean loadCurrentUsers(String filename) {
        try {
            File userFile = new File(filename);
            Scanner sc = new Scanner(userFile);
            while (sc.hasNextLine()) {
                String lin = sc.nextLine();
                String ns[] = lin.split("\s+");
                User user = new User(ns[0], ns[1]);
                // adds users from the file into the current users list
                currentUsers.add(user);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Save the current users.
     * 
     * @return true if saved correctly, false otherwise
     */
    public boolean saveCurrentUsers() {
        return true;
    }

    /**
     * Load the current rentals.
     * 
     * @return true if loaded correctly, false otherwise
     */
    public static boolean loadRentals(String filename) {
        try {
            File rentalFile = new File(filename);
            Scanner sc = new Scanner(rentalFile);

            while (sc.hasNextLine()) {
                String lin = sc.nextLine();
                String ns[] = lin.split("\s+");
                Boolean rentalFlag = false;

                if (ns[5].equals("T")) {
                    rentalFlag = true;
                }

                RentalUnit rentalUnit = new RentalUnit(ns[0], ns[1], ns[2], Integer.parseInt(ns[3]),
                        Float.parseFloat(ns[4]), rentalFlag, Integer.parseInt(ns[6]));
                // adds rental units from the file to the available units list
                availableUnits.add(rentalUnit);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Save the rentals.
     * 
     * @return true if saved correctly, false otherwise
     */
    public boolean saveRentals() {
        return true;
    }

    /**
     * Save the daily transactions.
     * 
     * @return true if saved correctly, false otherwise
     */
    public static boolean saveDailyTransactions() {
        File dailyTransactions = new File(transactionsFilename);
        try {
            FileWriter writer = new FileWriter(dailyTransactions);
            // save to daily transactions file
            for (String transaction : Session.getTransactions()) {
                writer.write(transaction);
            }

            writer.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Sets the transaction file
     * 
     * @param filename
     */
    public static void setTransactionsFile(String filename) {
        transactionsFilename = filename;
    }

    /**
     * Set rental units
     * 
     * @param rentalUnits
     */
    public static void setRentalUnits(ArrayList<RentalUnit> rentalUnits) {
        availableUnits = rentalUnits;
    }
}
