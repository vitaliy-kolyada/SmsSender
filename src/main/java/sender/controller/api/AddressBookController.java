package sender.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sender.model.Addressbook;
import sender.model.ApplicationUser;
import sender.util.JSONParser.JsonParser;
import sender.util.Utils;

import java.util.List;

import static sender.util.Address.ADDRESS;

/**
 * Operation with address book.
 * Api docs:
 *
 * @link https://www.epochta.com.ua/products/sms/v3.php
 * @see sender.model.Addressbook
 * @see Utils
 * @see ApplicationUser
 */
@Component("addressBookController")
public class AddressBookController {
    private JsonParser<Addressbook> jsonParser;
    private ApplicationUser applicationUser;

    @Autowired
    public void setJsonParser(JsonParser<Addressbook> jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Autowired
    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    /**
     * @param name        The name of the address book
     * @param description Note for the address book
     * @return addressbook_id — Unique identifier for the new address book
     * Request example: URL
     * @link :http://api.myatompark.com/sms/3.0/addAddressbook?key=public_key&sum=control_sum&name=BOOK_NAME&description=BOOK_DESCRIPTION
     */
    public int addAddressbook(String name, String description) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");

        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "addAddressbook?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "addAddressbook", applicationUser.getPublicKey(), name, description) + "&"
                + "name=" + name + "&"
                + "description=" + description;

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        String responseBody = response.getBody();

        if (!Utils.isValidResponse(responseBody)) {
            return -1;
        }
        // Parsing response
        String[] strings = responseBody.split("\n");
        String[] res = strings[2].split(":");
        return new Integer(res[1]);
    }

    /**
     * @param idAddressBook The ID of a deleted address book
     * @return In case of successful execution the result object will contain "successful":true.
     * When you delete a book that does not exist, the server will return code 202.
     * Request example: URL
     * @link URL:http://api.myatompark.com/sms/3.0/delAddressbook?key=public_key&sum=control_sum&idAddressBook=21619
     */
    public boolean delAddressbook(int idAddressBook) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");

        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "delAddressbook?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "delAddressbook", applicationUser.getPublicKey(), Integer.toString(idAddressBook)) + "&"
                + "idAddressbook=" + Integer.toString(idAddressBook);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        String responseBody = response.getBody();

        return responseBody.equals("{\n" +
                "   \"result\":{\n" +
                "      \"successful\":true\n" +
                "   }\n" +
                "} ");
    }

    /**
     * @param idAddressBook ID edit the address book
     * @param newName       The name of the book
     * @param newDescr      Book description
     * @return Response status
     * Request example: URL
     * @link http://api.myatompark.com/sms/3.0/editAddressbook?key=public_key&sum=control_sum&idAddressBook=21619&newName=name&newDescr=descr
     */
    public boolean editAddressbook(int idAddressBook, String newName, String newDescr) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");

        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "editAddressbook?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "editAddressbook", applicationUser.getPublicKey(), Integer.toString(idAddressBook), newName, newDescr) + "&"
                + "idAddressbook=" + Integer.toString(idAddressBook) + "&"
                + "newName=" + newName + "&"
                + "newDescr=" + newDescr;

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        String responseBody = response.getBody();

        return responseBody.equals("{\n" +
                "   \"result\":{\n" +
                "      \"successful\":true\n" +
                "   }\n" +
                "} ");
    }

    /**
     * @param idAddressBook The address book ID
     * @return Address book with the specified ID
     * Request example: URL
     * @link http://api.myatompark.com/sms/3.0/getAddressbook?key=public_key&sum=control_sum&idAddressBook=2161
     */
    public Addressbook getAddressbook(int idAddressBook) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");

        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "getAddressbook?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "getAddressbook", applicationUser.getPublicKey(), Integer.toString(idAddressBook)) + "&"
                + "idAddressbook=" + Integer.toString(idAddressBook);

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
            return jsonParser.parseOne(response.getBody());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param from   Return objects starting from the position from
     * @param offset How many objects to return
     * @return List address books
     * Request example: URL
     * @link http://api.myatompark.com/sms/3.0/getAddressbook?key=public_key&sum=control_sum&from=3&offset=3
     */
    @SuppressWarnings("unchecked")
    public List getListAddressbook(int from, int offset) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");

        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "getAddressbook?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "getAddressbook", applicationUser.getPublicKey(), Integer.toString(from), Integer.toString(offset)) + "&"
                + "from=" + Integer.toString(from) + "&"
                + "offset=" + Integer.toString(offset);
        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
            return jsonParser.parseList(response.getBody());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @return List all address books
     * Request example: URL
     * @link http://api.myatompark.com/sms/3.0/getAddressbook?key=public_key&sum=control_sum
     */
    @SuppressWarnings("unchecked")
    public List getAllAdressbook() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");

        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "getAddressbook?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "getAddressbook", applicationUser.getPublicKey());
        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
            return jsonParser.parseList(response.getBody());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * The method is used to create a copy of the address book with the new ID
     *
     * @param idAddressBook The IDs of the address book, a copy of which should be created.
     * @return Clone ID
     * Request example: URL
     * @link http://api.myatompark.com/sms/3.0/cloneaddressbook?key=public_key&sum=control_sum&idAddressBook=147
     */
    public int cloneaddressbook(int idAddressBook) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");

        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "addAddressbook?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "addAddressbook", applicationUser.getPublicKey(), Integer.toString(idAddressBook)) + "&"
                + "idAddressBook=" + Integer.toString(idAddressBook);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        String responseBody = response.getBody();

        if (!Utils.isValidResponse(responseBody)) {
            return -1;
        }
        // Parsing response
        String[] strings = responseBody.split("\n");
        String[] res = strings[2].split(":");
        return new Integer(res[1]);
    }
}
