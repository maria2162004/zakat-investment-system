package Interfaces;

public interface IAuthService {
    boolean signUp(String username, String password);
    boolean login(String username, String password);
}

