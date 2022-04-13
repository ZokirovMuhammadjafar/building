package uz.karkas.building.exception;

public class UserAlreadyTaken extends RuntimeException{

    public UserAlreadyTaken(String message) {
        super(message);
    }
}
