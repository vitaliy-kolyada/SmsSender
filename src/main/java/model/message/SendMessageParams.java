package model.message;

/**
 * @field name          название кампании
 * @field deferredToTs  дата и время отправки, если нужно начать отправку в указанное время.
 * Должна быть не позднее чем через 14 дней и не ранее чем через час от текущего времени.
 * Формат: 2013-12-31 15:34:55
 * @field mclass        0,1,2,3, по умолчанию
 * 1 - сообщения сохраняются в папку Входящих сообщений телефона,
 * 0 - отображаются всплывающим окном и никуда не сохраняются (flashSMS), поддерживается не всеми телефонами,
 * 2 - Сохраняется на сим карту,
 * 3 - SIM Toolkit SMS
 * @field validity время жизни сообщения в минутах от 1 мин до 3 суток (4320 мин) с момента отправки
 */
public class SendMessageParams {
    private String name;
    private String deferredToTs;
    private int mclass;
    private int validity;
}
