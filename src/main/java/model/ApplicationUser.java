package model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "ApplicationUser")
public class ApplicationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "sign")
    private String sign;

    @Column(name = "ballance")
    private BigInteger ballance;
}
