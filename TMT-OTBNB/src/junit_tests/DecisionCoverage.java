package junit_tests;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import otbnb.Rent;
import otbnb.RentalUnit;
import otbnb.Server;
import otbnb.Session;
import otbnb.User;

public class DecisionCoverage {

    // test rental units are equal
    @Test
    public void rentalUnitsEqualTrue() {
        // Set rental unit
        RentalUnit rentalUnit = new RentalUnit("a0b0c0d0", "username05", "Oshawa", 5, 120, false, 0);
        ArrayList<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();
        rentalUnits.add(rentalUnit);

        // set rental units and user
        Server.setRentalUnits(rentalUnits);
        Session.setUser(new User("user01", "FS"));
        boolean result = Rent.validate("a0b0c0d0");

        assertEquals(result, true);
    }

    // test rental units are not equal
    @Test
    public void rentalUnitsEqualFalse() {
        // Set rental unit
        RentalUnit rentalUnit = new RentalUnit("a0b0c0d0", "username05", "Oshawa", 5, 120, false, 0);
        ArrayList<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();
        rentalUnits.add(rentalUnit);

        // set rental units and user
        Server.setRentalUnits(rentalUnits);
        Session.setUser(new User("user01", "FS"));
        boolean result = Rent.validate("aaaa0000");

        assertEquals(result, false);
    }

    // Decision on line 96 in Rent.java
    // Checking if rental unit is rented
    @Test
    public void rentalUnitAlreadyRentedTrue() {
        // Set rental unit
        RentalUnit rentalUnit = new RentalUnit("a0b0c0d0", "username05", "Oshawa", 5, 120, false, 0);
        ArrayList<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();
        rentalUnits.add(rentalUnit);

        // set rental units and user
        Server.setRentalUnits(rentalUnits);
        Session.setUser(new User("user01", "FS"));
        boolean result = Rent.validate("a0b0c0d0");

        assertEquals(result, true);
    }

    // Decision on line 96 in Rent.java
    // Checking if rental unit is rented
    @Test
    public void rentalUnitAlreadyRentedFalse() {
        // Set rental unit
        RentalUnit rentalUnit = new RentalUnit("a0b0c0d0", "username05", "Oshawa", 5, 120, true, 0);
        ArrayList<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();
        rentalUnits.add(rentalUnit);

        // set rental units and user
        Server.setRentalUnits(rentalUnits);
        Session.setUser(new User("user01", "FS"));
        boolean result = Rent.validate("a0b0c0d0");

        assertEquals(result, false);
    }

    // Decision on line 97 in Rent.java
    // Checking if user who posted rental unit is same as session user
    @Test
    public void rentalUnitSameUserTrue() {
        // Set rental unit
        RentalUnit rentalUnit = new RentalUnit("a0b0c0d0", "username05", "Oshawa", 5, 120, false, 0);
        ArrayList<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();
        rentalUnits.add(rentalUnit);

        // set rental units and user
        Server.setRentalUnits(rentalUnits);
        Session.setUser(new User("user01", "FS"));
        boolean result = Rent.validate("a0b0c0d0");

        assertEquals(result, true);
    }

    // Decision on line 97 in Rent.java
    // Checking if user who posted rental unit is same as session user
    @Test
    public void rentalUnitSameUserFalse() {
        // Set rental unit
        RentalUnit rentalUnit = new RentalUnit("a0b0c0d0", "username05", "Oshawa", 5, 120, false, 0);
        ArrayList<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();
        rentalUnits.add(rentalUnit);

        // set rental units and user
        Server.setRentalUnits(rentalUnits);
        Session.setUser(new User("username05", "FS"));
        boolean result = Rent.validate("a0b0c0d0");

        assertEquals(result, false);
    }

    // Decision on line 109 in Rent.java
    // Checking if rental unit is already rented
    @Test
    public void rentalUnitNotFoundTrue() {
        // Set rental unit
        RentalUnit rentalUnit = new RentalUnit("a0b0c0d0", "username05", "Oshawa", 5, 120, true, 0);
        ArrayList<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();
        rentalUnits.add(rentalUnit);

        // set rental units and user
        Server.setRentalUnits(rentalUnits);
        Session.setUser(new User("user01", "FS"));
        boolean result = Rent.validate("a0b0c0d0");

        assertEquals(result, false);
    }

    // Decision on line 109 in Rent.java
    // Checking if rental unit is already rented
    @Test
    public void rentalUnitNotFoundFalse() {
        // Set rental unit
        RentalUnit rentalUnit = new RentalUnit("a0b0c0d0", "username05", "Oshawa", 5, 120, false, 0);
        ArrayList<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();
        rentalUnits.add(rentalUnit);

        // set rental units and user
        Server.setRentalUnits(rentalUnits);
        Session.setUser(new User("user01", "FS"));
        boolean result = Rent.validate("a0b0c0d0");

        assertEquals(result, true);
    }
}
