import java.util.*;
import java.io.*;

public class BackEnd {
    /**
     * Three arguements required file name for users file,listings file and
     * transactions file
     * 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String usersFilename;
        String listingsFilename;
        String transactionsFilename;

        if (args.length == 3) {
            usersFilename = args[0];
            listingsFilename = args[1];
            transactionsFilename = args[2];

            createUsersFile(usersFilename);
            createListingsFile(listingsFilename);
        } else {
            System.out.println("Error: Need to enter three arguments");
        }
    }

    /**
     * Creates a random users file with mandatoryUsers for tests
     * 
     * @param usersFilename
     * @return
     */
    public static boolean createUsersFile(String usersFilename) {
        Random random = new Random();

        File usersFile = new File(usersFilename);
        try {
            FileWriter writer = new FileWriter(usersFile);

            ArrayList<String> mandatoryUsers = new ArrayList<>(Arrays.asList(
                    "AdminUser       AA",
                    "user01          FS",
                    "user02          PS",
                    "username05      RS",
                    "usern           AA",
                    "admin01         AA",
                    "RS_user01       RS",
                    "PS_user01       PS",
                    "admin02         AA"));

            // List of users for the program to choose 5 random users
            ArrayList<String> users = new ArrayList<>(Arrays.asList(
                    "user100         FS",
                    "AdminUser55     AA",
                    "admin10         AA",
                    "PS_user09       PS",
                    "PS_user105      PS",
                    "PS_user34       PS",
                    "RS_user99       RS",
                    "FS_user25       FS"));

            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(users.size());
                users.remove(index);
            }

            // Randomize file
            users.addAll(mandatoryUsers);
            Collections.shuffle(users);

            // save to users account file
            for (int i = 0; i < users.size(); i++) {
                if (i != users.size() - 1) {
                    writer.write(users.get(i) + "\n");
                } else {
                    writer.write(users.get(i));
                }
            }

            writer.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Creates a random listings file with mandatoryListings for tests
     * 
     * @param listingsFilename
     * @return
     */

    public static boolean createListingsFile(String listingsFilename) {
        Random random = new Random();

        File listingsFile = new File(listingsFilename);
        try {
            FileWriter writer = new FileWriter(listingsFile);

            ArrayList<String> mandatoryListings = new ArrayList<>(Arrays.asList(
                    "a0b0c0d0 username05      Oshawa                    5 110.00 F 00",
                    "a0b0c0d2 username05      Oshawa                    5 110.00 F 00",
                    "a0b0c0d3 username05      Oshawa                    5 110.00 T 00",
                    "a0b1c3d4 admin02         Oshawa                    5 110.00 F 00",
                    "aaaabbbb username05      Oshawa                    5 110.00 T 00",
                    "a0a0a0a0 username05      Oshawa                    5 110.00 F 00",
                    "aaaabbb2 username05      Oshawa                    5 020.00 F 00"));

            // List of users for the program to choose 4 random listings
            ArrayList<String> listings = new ArrayList<>(Arrays.asList(
                    "a0b0c0d5 username05      Toronto                   5 110.00 T 00",
                    "a0b0c0d6 username05      Toronto                   5 110.00 T 00",
                    "a0b0c0d7 username05      Toronto                   5 110.00 T 00",
                    "a0b1c3d9 admin02         Toronto                   5 110.00 T 00",
                    "aaaabbbc username05      Toronto                   5 110.00 T 00",
                    "a0a0a0a2 username05      Toronto                   5 110.00 T 00",
                    "aaaabbb8 username05      Toronto                   5 020.00 T 00"));

            // Randomize file
            Collections.shuffle(listings);

            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(listings.size());
                listings.remove(index);
            }

            listings.addAll(mandatoryListings);

            // save to listings file
            for (int i = 0; i < listings.size(); i++) {
                if (i != listings.size() - 1) {
                    writer.write(listings.get(i) + "\n");
                } else {
                    writer.write(listings.get(i));
                }
            }

            writer.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
