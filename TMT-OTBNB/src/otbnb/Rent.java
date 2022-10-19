package otbnb;

/**
 * Class - Rent
 * Allows an user to rent a unit
 */
public class Rent {
    /**
     * User rents a unit by inputing rental ID and number of nights
     * 
     * @return true
     */
    public static Boolean rentUnit() {
        if (Session.isActiveSession()) {
            if (!Session.getUser().getAccountType().equals("PS")) {
                String rentalID = "";
                String confirmRent = "";
                String numNights = "";

                System.out.print("Enter Rental ID: ");
                rentalID = Server.sc.nextLine();

                while (!validate(rentalID)) {
                    System.out.print("Enter Rental ID: ");
                    rentalID = Server.sc.nextLine();
                }

                Integer nights = 0;
                Boolean isValidNights = false;

                // check if number of rooms entered by user is valid
                while (!isValidNights) {
                    System.out.print("Enter number of nights: ");
                    numNights = Server.sc.nextLine();
                    try {
                        nights = Integer.parseInt(numNights);
                        if (nights > 0 && nights < 15) {
                            isValidNights = true;
                        } else {
                            System.out.println("Error: Enter valid range [1-14]");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid input");
                        isValidNights = false;
                    }
                }

                System.out.print("Confirm rental (Yes/No): ");
                confirmRent = Server.sc.nextLine();

                // confirmRent input must be (Yes/No)
                while (!confirmRent.equalsIgnoreCase("No") && !confirmRent.equalsIgnoreCase("Yes")) {
                    System.out.println("Error: Input must be (Yes/No)");
                    System.out.print("Confirm rental (Yes/No): ");
                    confirmRent = Server.sc.nextLine();
                }
                if (confirmRent.equalsIgnoreCase("No")) {
                    System.out.println("Rental not booked");
                } else {
                    System.out.println("Rental: " + rentalID + " booked for " + numNights + " nights");

                    // update rental remaining nights and save transaction
                    for (RentalUnit rentalUnit : Server.getAvailableUnits()) {
                        if (rentalID.equals(rentalUnit.getRentalID())) {
                            rentalUnit.setRemainingNights(nights);
                            saveTransaction(rentalUnit);
                            break;
                        }
                    }
                }
            } else {
                System.out.println("User can not be post standard");
            }
        } else {
            System.out.println("Must be logged in to rent");
        }
        return true;
    }

    /**
     * Validates if rental ID exists, not currently rented and user is not renting
     * their own unit
     * 
     * @param rentalID 8 character string
     * @return true if rental ID exists, not currently rented and user is not
     *         renting their own unit, false otherwise
     */
    public static boolean validate(String rentalID) {
        Boolean resultsFound = false;

        // find search results from rental unit file
        for (RentalUnit rentalUnit : Server.getAvailableUnits()) {
            // checks if rental ID exists
            if (rentalUnit.getRentalID().equals(rentalID)) {
                // checks if rental unit is already rented
                if (!rentalUnit.isRented()) {
                    if (!rentalUnit.getUsername().equals(Session.getUser().getUsername())) {
                        resultsFound = true;
                    } else {
                        System.out.println("Error: User can not rent their own unit");
                    }
                } else {
                    // rental unit is already rented
                    System.out.println("Error: Unit is already rented");
                }
                return resultsFound;
            }
        }
        if (!resultsFound) {
            System.out.println("No Results Found");
        }
        return resultsFound;
    }

    /**
     * Adds rent transaction to the session transactions list
     * 
     * @param rentalUnit the rental unit that is being rented
     */
    public static void saveTransaction(RentalUnit rentalUnit) {
        String t = "05 " + Session.getUser().toString() + " " + rentalUnit.toString() + "\n";
        Session.addTransaction(t);
    }
}
