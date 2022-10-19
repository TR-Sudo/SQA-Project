package otbnb;

import java.text.DecimalFormat;

/**
 * Class - RentalUnit
 * Rental unit information
 */
public class RentalUnit {
    private String username;
    private String rentalID;
    private String city;
    private float rentalPrice;
    private int rooms;
    private int remainingNights;
    private boolean rentalFlag;

    /**
     * Create a new RentalUnit with the given information.
     * 
     * @param username        User who posted the rental unit
     * @param rentalID        Unique rental ID
     * @param city            Rental unit's city
     * @param rentalPrice     Rental price in dollars
     * @param rooms           Number of rooms in rental unit
     * @param remainingNights Number of nights remaining on rental if currently
     *                        rented
     * @param rentalFlag      Rental flag, true if RentalUnit is currently rented,
     *                        false if rental unit is not currently rented
     */
    public RentalUnit(String rentalID, String username, String city, int rooms, float rentalPrice, boolean rentalFlag,
            int remainingNights) {
        this.username = username;
        this.rentalID = rentalID;
        this.city = city;
        this.rentalPrice = rentalPrice;
        this.rooms = rooms;
        this.remainingNights = remainingNights;
        this.rentalFlag = rentalFlag;
    }

    /**
     * Get the user who posted the RentalUnit.
     * 
     * @return user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the unique rental ID.
     * 
     * @return rental ID
     */
    public String getRentalID() {
        return rentalID;
    }

    /**
     * Get the city.
     * 
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Get the rental price in dollars.
     * 
     * @return rental price in dollars
     */
    public float getRentalPrice() {
        return rentalPrice;
    }

    /**
     * Get number of rooms in rental unit.
     * 
     * @return number of rooms in rental unit
     */
    public int getRooms() {
        return rooms;
    }

    /**
     * Get the number of remaining nights on the rental if currently rented.
     * 
     * @return Number of nights remaining if currently rented, 0 if not currently
     *         rented
     */
    public int getRemainingNights() {
        return remainingNights;
    }

    /**
     * Updates the remaining nights on the rental.
     */
    public void setRemainingNights(Integer r) {
        remainingNights = r;
    }

    /**
     * Returns whether the RentalUnit is currently rented.
     * 
     * @return true if the unit is currently rented, false if unit is not currently
     *         rented
     */
    public boolean isRented() {
        return rentalFlag;
    }

    /**
     * Displays the rental unit information in a user friendly format
     */
    public void displayRental() {
        System.out.println("Rental ID: " + rentalID);
        System.out.println("City: " + city);
        System.out.println("Number of bedrooms: " + rooms);
        System.out.println("Rental price per night: " + rentalPrice + "\n");
    }

    /**
     * Gets the rental unit information as a string, used in the daily transactions
     * file
     * 
     * @return rental unit string
     */
    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.00");
        String rentalPriceString = "" + df.format(rentalPrice);
        String remainingNightsString = "" + remainingNights;
        return rentalID + " " + city + " ".repeat(25 - city.length()) + " " + rooms + " "
                + "0".repeat(6 - rentalPriceString.length()) + rentalPriceString + " "
                + "0".repeat(2 - remainingNightsString.length()) + remainingNights;
    }
}
