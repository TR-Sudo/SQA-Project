package otbnb;

import java.text.DecimalFormat;
import java.util.regex.Pattern;

/**
 * Class - Search
 * Allows a user to search rental units
 */
public class Search {
    /**
     * User searches rentals given city, rental price and number of rooms
     * 
     * @return true
     */
    public static boolean searchRentalUnit() {
        if (Session.isActiveSession()) {
            String city = "";
            String rentalPriceString = "";
            String roomsString = "";

            System.out.print("Enter city: ");
            city = Server.sc.nextLine();

            // city must be a valid name, cannot be empty
            while (!Pattern.matches("^[a-zA-Z \\-]{1,25}$", city) && !city.equals("*")) {
                System.out.println(
                        "Error: City name must be less than 25 characters and can only contain A-z, spaces, and - or strictly be a *");
                System.out.print("Enter city: ");
                city = Server.sc.nextLine();
            }

            Boolean isValidPrice = false;
            Float rentalPrice = 0.0f;

            // check if rental price entered by user is valid
            while (!isValidPrice) {
                System.out.print("Enter rental price (0-999.99): ");
                rentalPriceString = Server.sc.nextLine();
                try {
                    rentalPrice = Float.parseFloat(rentalPriceString);
                    DecimalFormat df = new DecimalFormat("#.00");
                    rentalPriceString = df.format(rentalPrice);

                    if (rentalPrice > 0 && rentalPrice < 1000) {
                        isValidPrice = true;
                    } else {
                        System.out.println("Error: Rental price must be between 0 and 999.99");
                    }
                } catch (NumberFormatException e) {
                    if (rentalPriceString.equals("*")) {
                        isValidPrice = true;
                    } else {
                        System.out.println("Error: Invalid number");
                        isValidPrice = false;
                    }
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
                    if (rooms > 0 && rooms < 10) {
                        isValidRooms = true;
                    } else {
                        System.out.println("Error: Number of rooms must be between 0 and 9");
                    }
                } catch (NumberFormatException e) {
                    if (roomsString.equals("*")) {
                        isValidRooms = true;
                    } else {
                        System.out.println("Error: Invalid number");
                        isValidRooms = false;
                    }
                }
            }

            // display the results from search
            display(city, rentalPriceString, roomsString);
        } else {
            System.out.println("Cannot search unless logged in");
        }
        return true;
    }

    /**
     * Displays the search results of rental units given city, rental price, and
     * number of rooms
     * 
     * @param city              city can be a string or *
     * @param rentalPriceString rental price can be a number as a string or *
     * @param roomsString       rooms can be a number as a string or *
     */
    public static void display(String city, String rentalPriceString, String roomsString) {
        Boolean resultsFound = false;

        // find search results from rental unit file
        System.out.println("\nSearch Results:\n");
        for (RentalUnit rentalUnit : Server.getAvailableUnits()) {
            Boolean matchesCity = rentalUnit.getCity().equals(city) || city.equals("*");
            Boolean matchesRentalPrice = rentalPriceString.equals("*")
                    || (rentalUnit.getRentalPrice() <= Float.parseFloat(rentalPriceString));
            Boolean matchesRooms = roomsString.equals("*") || (rentalUnit.getRooms() >= Integer.parseInt(roomsString));

            // searches any unit with the same city, maximum rental price, and minimum rooms
            if ((!rentalUnit.isRented()) && matchesCity && matchesRentalPrice && matchesRooms) {
                rentalUnit.displayRental();
                resultsFound = true;
            }
        }

        if (!resultsFound) {
            System.out.println("No Results Found\n");
        } else {
            System.out.println("End of search results\n");
        }

        saveTransaction(city, roomsString, rentalPriceString);
    }

    /**
     * Adds search transaction to the session transactions list
     * 
     * @param city              city can be a string or *
     * @param numRooms          rooms can be a number as a string or *
     * @param rentalPriceString rental price can be a number as a string or *
     */
    public static void saveTransaction(String city, String numRooms, String rentalPriceString) {
        String t = "04 " + Session.getUser().toString() + " " + " ".repeat(8) + " " + city
                + " ".repeat(25 - city.length()) + " " + numRooms + " " + "0".repeat(6 - rentalPriceString.length())
                + rentalPriceString + " " + "00" + "\n";
        Session.addTransaction(t);
    }
}
