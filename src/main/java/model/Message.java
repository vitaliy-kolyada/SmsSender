package model;

import javax.persistence.*;

@Entity
@Table(name = "Message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String recipient;

    @Column
    private String text;
    @Column
    private String params;

    @Column
    private String message;


}
