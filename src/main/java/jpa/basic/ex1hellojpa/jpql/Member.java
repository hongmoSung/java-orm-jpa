package jpa.basic.ex1hellojpa.jpql;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "UniqueName", columnNames = {"userName"})}
)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;

    private String userName;

    @ManyToOne
    @JoinColumn(name = "teamId", foreignKey = @ForeignKey(name = "FK_TEAM"))
    private Team team;
}
