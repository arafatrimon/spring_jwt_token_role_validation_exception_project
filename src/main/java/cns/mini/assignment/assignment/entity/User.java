package cns.mini.assignment.assignment.entity;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Username is mandatory")
    //@Size(min = 3, max = 20)
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password is mandatory")
    //@Size(min = 6, message = "Password should have at least 5 characters")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Column(unique = true)
    // @Email(message = "Please enter a valid email")
//@Pattern(regexp = "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$")
    private String email;

    //@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> role;

}
