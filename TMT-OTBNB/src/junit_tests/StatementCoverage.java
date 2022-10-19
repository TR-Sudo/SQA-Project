package junit_tests;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import otbnb.Server;
import otbnb.Session;
import otbnb.User;

public class StatementCoverage {
    // test if the if statement works
    @Test
    public void logoutActiveSessionFalse() {
        // activeSession defaults to false
        boolean result = Session.logout();
        assertEquals(result, false);
    }

    // test if the else statement works
    @Test
    public void logoutActiveSessionTrue() {
        // need to create this method in our code
        Session.setActiveSession(true);
        // set user, we cannot construct transaction string with user as null
        Session.setUser(new User("user01", "FS"));
        // set transaction file, cannot be null as Session.logout() calls
        // saveDailyTransactions()
        Server.setTransactionsFile("dailyTransactionsFile.txt");

        boolean result = Session.logout();
        assertEquals(result, false);
    }
}
