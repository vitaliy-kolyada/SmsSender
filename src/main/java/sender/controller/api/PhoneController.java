package sender.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import sender.model.ApplicationUser;
import sender.model.Phone;
import sender.util.JSONParser.JsonParser;
import sender.util.Utils;

import java.util.List;

import static sender.util.Address.ADDRESS;

@Component("phoneController")
public class PhoneController {
    private JsonParser<Phone> jsonParser;
    private ApplicationUser applicationUser;

    @Autowired
    public void setJsonParser(JsonParser<Phone> jsonParser) {
        this.jsonParser = jsonParser;
    }

    @Autowired
    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    /**
     * @param idAddressBook The address book ID
     * @param phone         Phone
     * @param variables     Variable for personalization
     * @return The response will be returned phone_id is a unique identifier of the phone number.
     * Example query:
     * URL:
     * @link http://api.myatompark.com/sms/3.0/addPhoneToAddressBook?key=public_key&sum=control_sum&idAddressBook=2432&phone=380638962555&variables=test
     */
    public long addPhoneToAddressBook(long idAddressBook, String phone, String variables) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");
        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "addAddressbook?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "addAddressbook", applicationUser.getPublicKey(), Long.toString(idAddressBook), phone, variables) + "&"
                + "idAddressBook=" + Long.toString(idAddressBook) + "&"
                + "phone=" + phone + "&"
                + "variables=" + variables;

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        String responseBody = response.getBody();

        if (!Utils.isValidResponse(responseBody)) {
            return -1;
        }
        return new Long(responseBody.substring(33, responseBody.length() - 7));
    }

    /**
     * @param idAddressBook The address book ID
     * @return List phone list from addressbook
     * Example query:
     * URL:
     * @link http://api.myatompark.com/sms/3.0/getAddressbook?key=public_key&sum=control_sum&idAddressBook=2161
     */
    public List<Phone> getPhoneFromAddressBook(long idAddressBook) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");

        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "getAddressbook?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "getAddressbook", applicationUser.getPublicKey() + Long.toString(idAddressBook)) + "&"
                + "idAddressbook=" + Long.toString(idAddressBook);
        try {
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
            List<Phone> result = jsonParser.parseList(response.getBody());
            for (Phone phone : result) {
                if (phone != null)
                    phone.setAddressbook(idAddressBook);
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * @param idPhone The ID of the phone
     * @return Phone with specified ID
     * URL:
     * @link http://api.myatompark.com/sms/3.0/getPhoneFromAddressBook?key=public_key&sum=control_sum&idPhone=24552301
     */
    public Phone getPhone(long idPhone) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");
        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "getPhoneFromAddressBook?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "getPhoneFromAddressBook", applicationUser.getPublicKey() + Long.toString(idPhone)) + "&"
                + "idPhone=" + Long.toString(idPhone);

        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        String responseBody = response.getBody();
        if (!Utils.isValidResponse(responseBody)) {
            return null;
        }
        return jsonParser.parseOne(responseBody);
    }

    /**
     * @param idPhone The ID of the phone
     * @return Operation status
     * URL:
     * @link http://api.myatompark.com/sms/3.0/delPhoneFromAddressBook?key=public_key&sum=control_sum&idPhone=24552301
     */
    public boolean delPhoneFromAddressBook(long idPhone) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");
        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "delPhoneFromAddressBook?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "delPhoneFromAddressBook", applicationUser.getPublicKey() + Long.toString(idPhone)) + "&"
                + "idPhone=" + Long.toString(idPhone);
        String res = "{\n" +
                "   \"result\":{\n" +
                "      \"successful\":true\n" +
                "   }\n" +
                "} ";
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        String responseBody = response.getBody();
        return responseBody.equals(res);
    }

    /**
     * @param idPhone   The ID of the phone
     * @param phone     The phone number
     * @param variables Variable for personalization
     * @return Operation status
     * URL:
     * @link http://api.myatompark.com/sms/3.0/editPhone?key=public_key&sum=control_sum&idPhone=24552301&phone=380657412569&variables=test
     */
    public boolean editPhone(long idPhone, String phone, String variables) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>("");
        // Forming request URL
        String apiUrl = ADDRESS.getAddress() + "editPhone?"
                + "key=" + applicationUser.getPublicKey() + "&"
                + "sum=" + Utils.getCheckSum(applicationUser.getPrivateKey(), "3.0", "editPhone", applicationUser.getPublicKey() + Long.toString(idPhone) + phone, variables) + "&"
                + "idPhone=" + Long.toString(idPhone) + "&"
                + "phone=" + phone + "&"
                + "variables=" + variables;
        String res = "{\n" +
                "   \"result\":{\n" +
                "      \"successful\":true\n" +
                "   }\n" +
                "} ";
        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.POST, request, String.class);
        String responseBody = response.getBody();
        return responseBody.equals(res);
    }
}
