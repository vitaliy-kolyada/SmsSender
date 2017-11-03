package model.message;

/**
 * @field id                ID сообщения
 * @field status            статус сообщения
 * @field segCnt            кол-во сегментов в данном сообщении
 * @field startSendTs       время начала отправки сообщения
 * @field statusUpdateTs    время последнего обновления статуса сообщения
 */
public class SmsStatusResponse {
    private Long id;
    private String status;
    private Integer segCnt;
    private String startSendTs;
    private String statusUpdateTs;
}
