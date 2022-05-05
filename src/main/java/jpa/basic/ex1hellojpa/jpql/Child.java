package jpa.basic.ex1hellojpa.jpql;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Parent parent;

    public void changeParent(Parent parent) {
        this.parent = parent;
        parent.addChild(this);
    }
}
