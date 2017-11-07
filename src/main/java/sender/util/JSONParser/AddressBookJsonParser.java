package sender.util.JSONParser;

import org.springframework.stereotype.Component;
import sender.model.Addressbook;

import java.util.ArrayList;
import java.util.List;

@Component("addressBookJsonParser")
public class AddressBookJsonParser implements JsonParser<Addressbook> {

    @Override
    public Addressbook parseOne(String json) {

        String[] arr = json.split(",");
        int id = new Integer(arr[0].substring(27));
        String name = arr[1].substring(15, arr[1].length() - 1);
        int phones = new Integer(arr[2].substring(16));
        int exceptions = new Integer(arr[3].substring(20));
        String creationdate = arr[4].substring(23, arr[4].length() - 1);
        String description = arr[5].substring(21, arr[5].length() - 4);

        return new Addressbook(id, name, phones, exceptions, creationdate, description);
    }

    @Override
    public List<Addressbook> parseList(String json) {
        String[] string = json.split("data\":");
        String s = string[1];

        ArrayList<Addressbook> addressbooks = new ArrayList<>();
        String[] objects = s.split("\\[");

        for (int i = 2; i < objects.length; i++) {

            String[] fields = objects[i].split("\n");
            int id = new Integer(fields[1].substring(13, fields[1].length() - 2));
            String name = fields[2].substring(13, fields[2].length() - 2);
            int phone = new Integer(fields[3].substring(13, fields[3].length() - 2));
            String date = fields[4].substring(13, fields[4].length() - 2);
            String description = fields[5].substring(13, fields[5].length() - 1);

            addressbooks.add(new Addressbook(id, name, phone, date, description));
        }
        return addressbooks;
    }
}
