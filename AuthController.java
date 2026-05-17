package Controller;

import java.util.HashMap;
import java.util.Map;

import ModelSystem.User;

public class AuthController {
    private Map<String, User> userDatabase = new HashMap<>();
    private User loggedInUser;

    public boolean signUp(String username, String password) {
        if (userDatabase.containsKey(username)) {
            return false;
        }
        userDatabase.put(username, new User(username, password));
        return true;
    }

    public boolean login(String username, String password) {
        User user = userDatabase.get(username);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            return true;
        }
        return false;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
