package my.examples.JBCmart.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
    private Long id;

    @Column
    private String passwd;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String email;

//    @Column(name = "reg_date")
//    private Date regDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;

    public User() {
//        regDate = new Date();
        roles = new HashSet<>();
    }

    public void addRole(Role role) {
        if (roles == null)
            roles = new HashSet<>();
        roles.add(role);
    }
}
