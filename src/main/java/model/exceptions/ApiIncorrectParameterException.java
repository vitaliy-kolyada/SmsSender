package model.exceptions;

public class ApiIncorrectParameterException extends Exception {
    public ApiIncorrectParameterException() {
        super("Не указано ни одного или указано более 100 идентификаторов сообщений");
    }
}
