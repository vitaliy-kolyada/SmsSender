package sender.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

public class Utils {
    /**
     * * For the checksum required:
     * 1. sort all the incoming keys
     * 2. concat the values of those keys
     * 3. concat the returned value with the private key
     * 4. son-in MD5 from the received result
     *
     * @param privateKey Private user key
     * @param params     Method parameter. Example:
     *                   version="3.0"
     *                   action="addAddressbook"
     *                   key="openKey"
     *                   name="TestAddressbook"
     *                   description="Test description"
     * @return Control sum for HTTP request.
     */
    public static String getCheckSum(String privateKey, String... params) {
        Arrays.sort(params);
        StringBuilder sum = new StringBuilder();
        for (String param : params) {
            sum.append(param);
        }
        sum.append(privateKey);
        return DigestUtils.md5Hex(sum.toString());
    }

    /**
     * @param response HTTP response text
     * @return true, if response not matching error JSON
     */
    public static boolean isValidResponse(String response) {
        return !response.equals("{\n" +
                "   \"error\":\"Wrong public key.\",\n" +
                "   \"code\":\"-1\",\n" +
                "   \"result\":\"false\"\n" +
                "} ");
    }
}
