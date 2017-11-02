package model.message;

public class SmsStatus {
    private Long id;                          //ID сообщения
    private String status;                    //статус сообщения
    private Integer segCnt;                   //кол-во сегментов в данном сообщении
    private String startSendTs;               //время начала отправки сообщения
    private String statusUpdateTs;            //время последнего обновления статуса сообщения
}
