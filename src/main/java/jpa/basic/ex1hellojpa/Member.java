package jpa.basic.ex1hellojpa;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String username;
    private String city;
    private String street;
    private String zipCode;
}
