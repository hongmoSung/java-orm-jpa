package jpa.basic.ex1hellojpa.jpql;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Getter
@Setter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Team team;

    public void changeTeam(Team team) {
        this.team = team;
        this.getTeam().getMembers().add(this);
    }

    public Member() {
    }

    public Member(String username) {
        this.username = username;
    }
}
