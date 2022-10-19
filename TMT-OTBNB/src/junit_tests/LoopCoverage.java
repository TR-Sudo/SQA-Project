package junit_tests;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import otbnb.Rent;
import otbnb.RentalUnit;
import otbnb.Server;
import otbnb.Session;
import otbnb.User;

public class LoopCoverage {
    // Decision on line 92 in Rent.java
    // Loop through all rental units zero times
    @Test
    public void loopZeroTimes() {
        ArrayList<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();
        // set rental units and user
        Server.setRentalUnits(rentalUnits);
        
        boolean result = Rent.validate("a0b0c0d0");
        assertEquals(result, false);
    }

    // Decision on line 92 in Rent.java
    // Loop through one rental unit
    @Test
    public void loopOnce() {
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

    // Decision on line 92 in Rent.java
    // Loop through two rental unit
    @Test
    public void loopTwice() {
        // Set rental unit
        RentalUnit rentalUnit = new RentalUnit("a0b0c0d2", "username05", "Oshawa", 5, 120, false, 0);
        RentalUnit rentalUnit2 = new RentalUnit("a0b0c0d0", "username05", "Oshawa", 5, 120, false, 0);
        
        ArrayList<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();
        rentalUnits.add(rentalUnit);
        rentalUnits.add(rentalUnit2);

        // set rental units and user
        Server.setRentalUnits(rentalUnits);
        Session.setUser(new User("user01", "FS"));
        boolean result = Rent.validate("a0b0c0d0");

        assertEquals(result, true);
    }

    // Decision on line 92 in Rent.java
    // Loop through many rental units 
    @Test
    public void loopManyTimes() {
        // Set rental unit
        RentalUnit rentalUnit = new RentalUnit("a0b0c0d3", "username05", "Oshawa", 5, 120, false, 0);
        RentalUnit rentalUnit2 = new RentalUnit("a0b0c0d2", "username05", "Oshawa", 5, 120, false, 0);
        RentalUnit rentalUnit3 = new RentalUnit("a0b0c0d0", "username05", "Oshawa", 5, 120, false, 0);
        
        ArrayList<RentalUnit> rentalUnits = new ArrayList<RentalUnit>();
        rentalUnits.add(rentalUnit);
        rentalUnits.add(rentalUnit2);
        rentalUnits.add(rentalUnit3);

        // set rental units and user
        Server.setRentalUnits(rentalUnits);
        Session.setUser(new User("user01", "FS"));
        boolean result = Rent.validate("a0b0c0d0");

        assertEquals(result, true);
    }
}
