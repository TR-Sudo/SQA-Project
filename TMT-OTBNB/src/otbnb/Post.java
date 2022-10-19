package otbnb;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Class - Post
 * Allows an user to post a rental unit
 */
public class Post {
    /**
     * User posts a rental unit by inputing the city, rental price and number of
     * rooms
     * 
     * @return true
     */
    public static Boolean postRentalUnit() {
        if (Session.isActiveSession()) {
            if (!Session.getUser().getAccountType().equals("RS")) {
                String cityName = "";
                String rentalPriceString = "";
                String roomsString = "";

                System.out.print("Enter City Name: ");
                cityName = Server.sc.nextLine();
                while (!Pattern.matches("^[a-zA-Z \\-]{1,25}$", cityName)) {
                    // rental id must be a valid name, cannot be empty
                    System.out.println(
                            "Error: City name must be less than 25 characters and can only contain A-z, spaces, and -");
                    System.out.print("Enter City Name: ");
                    cityName = Server.sc.nextLine();
                }

                Boolean isValidPrice = false;
                Float rentalPrice = 0.0f;

                // check if rental price entered by user is valid
                while (!isValidPrice) {
                    System.out.print("Enter rental price (0-999.99): ");
                    rentalPriceString = Server.sc.nextLine();
                    try {
                        // format the rental price to at most two decimals
                        rentalPrice = Float.parseFloat(rentalPriceString);
                        DecimalFormat df = new DecimalFormat("#.00");
                        rentalPriceString = df.format(rentalPrice);
                        // check if rental price is within range
                        if (rentalPrice > 0 && rentalPrice < 1000) {
                            isValidPrice = true;
                        } else {
                            System.out.println("Error: Rental price must be between 0 and 999.99");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid number");
                        isValidPrice = false;
                    }
                }

                Integer rooms = 0;
                Boolean isValidRooms = false;

                // check if number of rooms entered by user is valid
                while (!isValidRooms) {
                    System.out.print("Enter number of rooms (0-9): ");
                    roomsString = Server.sc.nextLine();
                    try {
                        rooms = Integer.parseInt(roomsString);
                        // check if number of rooms is within range
                        if (rooms > 0 && rooms < 10) {
                            System.out.println("Unit posted by user: " + Session.getUser().getUsername());
                            isValidRooms = true;
                        } else {
                            System.out.println("Error: Number of rooms must be between 0 and 9");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid number");
                        isValidRooms = false;
                    }
                }
                // save transaction only when user input is valid
                saveTransaction(cityName, roomsString, rentalPriceString);
            } else {
                System.out.println(
                        "Error: Posting is only allowed for users with admin, full-standard or post-standard accounts");
            }
        }

        return true;
    }

    /**
     * Adds post transaction to the session transactions list
     * 
     * @param city              string is at most 25 characters
     * @param numRooms          number of rooms as a string
     * @param rentalPriceString formatted rental price as a string
     */
    public static void saveTransaction(String city, String numRooms, String rentalPriceString) {
        UUID randomUUID = UUID.randomUUID();
        String rentalID = randomUUID.toString().replaceAll("-", "").substring(0, 8);
        boolean isUnique = false;

        // generate a random rental id, make sure it doesn't exist
        while (!isUnique) {
            for (RentalUnit rentalUnit : Server.getAvailableUnits()) {
                if (rentalUnit.getRentalID().equals(rentalID)) {
                    rentalID = randomUUID.toString().replaceAll("-", "").substring(0, 8);
                    break;
                }
            }

            if (!isUnique) {
                isUnique = true;
            }
        }

        String t = "03 " + Session.getUser().toString() + " " + rentalID + " " + city + " ".repeat(25 - city.length())
                + " " + numRooms + " " + "0".repeat(6 - rentalPriceString.length()) + rentalPriceString + " " + "00"
                + "\n";
        Session.addTransaction(t);
    }

}
