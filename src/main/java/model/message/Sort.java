package model.message;

/**
 * сортировка
 *
 * @field id                идентификатор сообщения
 * @field campaignId        идентификатор кампании
 * @field from              подпись отправителя
 * @field to                номер получателя
 * @field text              текст сообщения
 * @field status            статус сообщения
 * @field groups            группы получателя сообщения
 * @field contentProviderId идентификатор смс центра
 */
public class Sort {
    private int id;
    private int campaignId;
    private String from;
    private String to;
    private String text;
    private int status;
    private String groups;
    private int contentProviderId;
}
