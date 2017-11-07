package sender.util.JSONParser;

import java.util.List;

public interface JsonParser<T> {
    T parseOne(String json);

    List<T> parseList(String json);
}
