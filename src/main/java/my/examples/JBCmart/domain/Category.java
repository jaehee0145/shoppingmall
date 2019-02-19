package my.examples.JBCmart.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    private int ordering;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category(){
        products = new ArrayList<>();
    }
}

