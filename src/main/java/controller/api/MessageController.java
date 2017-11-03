package controller.api;

import model.exceptions.ApiIncorrectParameterException;
import model.message.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;

import static model.constants.Address.*;

/**
 * Server API docs
 *
 * @link http://docs.mobizon.com/api/module.Message.html
 */
public class MessageController {

    /**
     * Получить список найденных по фильтру сообщений
     *
     * @param criterias   критерии поиска
     * @param paginations параметры постраничного вывода
     * @param sort        сортировка
     * @return ListMessageResponse
     * @see Criteria
     * @see Pagination
     * @see Sort
     * @see ListMessageResponse
     */
    public ListMessageResponse list(Criteria[] criterias, Pagination[] paginations, Sort[] sort) {

        StringBuilder sb = new StringBuilder();
        sb.append("criteria=");
        for (Criteria criteria : criterias) {
            sb.append(criteria);
            sb.append(",");
        }
        sb.append("pagination=");
        for (Pagination pagination : paginations) {
            sb.append(pagination);
            sb.append(",");
        }
        sb.append("sort=");
        for (int i = 0; i < sort.length; i++) {
            sb.append(sort[i]);
            sb.append(i != sort.length - 1 ? "," : "");
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(sb.toString());
        ResponseEntity<ListMessageResponse> response =
                restTemplate.exchange(LIST_MESSAGES.getAddress(), HttpMethod.POST, request, ListMessageResponse.class);
        return response.getBody();
    }

    /**
     * @param ids ID сообщений
     * @return Возвращает отчет о статусе доставки сообщений текущего пользователя с заданными ID
     * @throws ApiIncorrectParameterException если не указано ни одного или указано более 100 идентификаторов сообщений
     * @see SmsStatusResponse
     * @see ApiIncorrectParameterException
     */
    public SmsStatusResponse getSMSStatus(Integer... ids) throws ApiIncorrectParameterException {

        if (ids.length == 0 || ids.length > 100)
            throw new ApiIncorrectParameterException();

        StringBuilder sb = new StringBuilder();
        sb.append("ids=");
        for (int i = 0; i < ids.length; i++) {
            sb.append(ids[i]);
            sb.append(i != ids.length - 1 ? "," : "");
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(sb.toString());
        ResponseEntity<SmsStatusResponse> response =
                restTemplate.exchange(GET_SMS_STATUS.getAddress(), HttpMethod.POST, request, SmsStatusResponse.class);
        return response.getBody();
    }

    /**
     * @param recipient Получатель SMS сообщения, номер в международном формате, если в номере есть + в начале, то его следует закодировать в URL сущность. Желательно + вообще не указывать.
     * @param text      Текст SMS сообщения, закодированный в URL сущность. Если во время попытки отправить сообщение при помощи GET запроса система не возвращает ответ с данными сообщения, следует в первую очередь обратить внимание на наличие спецсимволов в теле запроса, такими символами являются: ? / \ & + пробел.
     * @param from      Подпись отправителя. Можно не указывать, тогда в случае, если нет ни одной валидной подписи, будет использована общая системная подпись. ВНИМАНИЕ! Подпись может отличаться для каждого оператора и может быть без предупреждения изменена в любое время. Если же есть заведенные подписи, то будет использована та, у которой установлен флаг "По умолчанию".
     * @param params    Дополнительные параметры сообщения:
     * @return SendMessageResponse
     * @see SendMessageParams
     * @see SendMessageResponse
     */
    public SendMessageResponse sendSMSMessage(String recipient, String text, String from, String... params) throws MalformedURLException {
        //TODO write validator

        StringBuilder sb = new StringBuilder();
        sb.append("recipient=");
        sb.append(recipient);
        sb.append("text=");
        sb.append(text);
        sb.append("from=");
        sb.append(from);
        sb.append("params=");
        for (int i = 0; i < params.length; i++) {
            sb.append(params[i]);
            sb.append(i != params.length - 1 ? "," : "");
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(sb.toString());
        ResponseEntity<SendMessageResponse> response =
                restTemplate.exchange(SEND_MESSAGE.getAddress(), HttpMethod.POST, request, SendMessageResponse.class);
        return response.getBody();
    }

}
