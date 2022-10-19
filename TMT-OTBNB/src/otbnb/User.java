package otbnb;

/**
 * Class - User
 * User information
 */
public class User {
    private String username;
    private String accountType;

    /**
     * Create a new user with the given username and account type.
     * 
     * @param username    username
     * @param accountType account type
     */
    public User(String username, String accountType) {
        this.username = username;
        this.accountType = accountType;
    }

    /**
     * Get user's username.
     * 
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get user's account type.
     * 
     * @return accountType
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Get the username and account type as a string.
     * 
     * @return string of username and account type
     */
    @Override
    public String toString() {
        String result = username;
        for (int i = 0; i < (15 - (username.length())); i++) {
            result = result + " ";
        }

        result = result + " " + accountType;

        return result;
    }
}
