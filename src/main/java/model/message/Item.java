package model.message;

/**
 * список найденных элементов, каждый из которых содержит поля:
 *
 * @field id                идентификатор сообщения
 * @field campaignId        идентификатор кампании
 * @field segNum            количество сегментов
 * @field segUserBuy        стоимость покупки сегмента для пользователя в валюте пользователя
 * @field status            статус
 * @field uuid              внутренний идентификатор сообщения
 * @field from              подпись отправителя
 * @field to                номер получателя
 * @field text              текст сообщения
 * @field groups            ID групп получателя сообщения, в которые входил данный номер на момент создания кампании
 */
public class Item {
    private int id;
    private int campaignId;
    private int segNum;
    private float segUserBuy;
    private String status;
    private String uuid;
    private String from;
    private String to;
    private String text;
    private String groups;
}
