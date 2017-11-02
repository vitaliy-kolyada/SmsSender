package controller;

import model.message.SmsStatus;

/**
 * Server API docs
 *
 * @link http://docs.mobizon.com/api/module.Message.html
 */
public class MessageController {

    public SmsStatus getSMSStatus(String... ids) {
        return null;
    }

    /**
     *
     * @param recipient Получатель SMS сообщения, номер в международном формате, если в номере есть + в начале, то его следует закодировать в URL сущность. Желательно + вообще не указывать.
     * @param text Текст SMS сообщения, закодированный в URL сущность. Если во время попытки отправить сообщение при помощи GET запроса система не возвращает ответ с данными сообщения, следует в первую очередь обратить внимание на наличие спецсимволов в теле запроса, такими символами являются: ? / \ & + пробел.
     * @param from Подпись отправителя. Можно не указывать, тогда в случае, если нет ни одной валидной подписи, будет использована общая системная подпись. ВНИМАНИЕ! Подпись может отличаться для каждого оператора и может быть без предупреждения изменена в любое время. Если же есть заведенные подписи, то будет использована та, у которой установлен флаг "По умолчанию".
     * @param params
     * @return
     */
    public String sendSMSMessage(String recipient, String text, String from, String[] params) {
        return null;
    }

}
