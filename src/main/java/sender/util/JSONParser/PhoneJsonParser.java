package sender.util.JSONParser;

import sender.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneJsonParser implements JsonParser<Phone> {

    @Override
    public Phone parseOne(String json) {
        String[] arr = json.split("\n");

        long id = new Long(arr[2].substring(11, arr[2].length() - 1));
        long addressbook = new Long(arr[3].substring(20, arr[3].length() - 1));
        String phone = arr[4].substring(15, arr[4].length() - 2);
        String normalphone = arr[5].substring(21, arr[5].length() - 2);
        String variables = arr[6].substring(19, arr[6].length() - 2);
        int status = new Integer(arr[7].substring(15));

        return new Phone(id, addressbook, phone, normalphone, variables, status);
    }

    @Override
    public List<Phone> parseList(String json) {
        String[] string = json.split("data\":");
        String s = string[1];

        ArrayList<Phone> phones = new ArrayList<>();
        String[] objects = s.split("\\[");
        for (int i = 2; i < objects.length; i++) {

            String[] arr = objects[i].split("\n");
            long id = new Long(arr[1].substring(13, arr[1].length() - 2));
            String phone = arr[2].substring(13, arr[2].length() - 2);
            String normalphone = arr[3].substring(13, arr[3].length() - 2);
            String variables = arr[4].substring(13, arr[4].length() - 2);
            int status = new Integer(arr[5].substring(13, arr[5].length() - 2));

            phones.add(new Phone(id, phone, normalphone, variables, status));
        }
        return phones;
    }
}
