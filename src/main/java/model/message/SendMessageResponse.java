package model.message;

/**
 * @field campaignId идентификатор sms кампании (по нему можно затем узнать различные параметры кампании,
 * запросить список сегментов, их статусы и другую информацию модуля campaign)
 * @field status статус отправки кампании (1 - должно пройти модерацию, 2 - отправлено без модерации)
 * @field messageId идентификатор sms сообщения
 * (по нему можно узнать статус доставки сообщения с помощью message::getsmsstatus)
 */
public class SendMessageResponse {
    private int campaignId;
    private int status;
    private int messageId;
}
