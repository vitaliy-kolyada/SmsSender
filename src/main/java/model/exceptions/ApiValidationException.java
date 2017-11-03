package model.exceptions;

public class ApiValidationException extends Exception {
    public ApiValidationException() {
        super("Ошибка валидации передаваемых данных во время создания или обновления какой-либо сущности. " +
                "В поле data представлена информация о том, какие поля заполнены неверно. " +
                "Следует исправить ошибки и повторить запрос с новыми данными.");
    }
}
