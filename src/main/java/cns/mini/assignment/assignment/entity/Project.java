package cns.mini.assignment.assignment.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Lob
    @Column(name = "intro")
    @NotBlank(message = "Introduction is mandatory")
    private String intro;

    @Column(name = "owner")
    @NotBlank(message = "Owner is mandatory")
    private String owner;

    @Column(name = "status")
    @NotBlank(message = "Status is mandatory")
    private String status;

    @Column(name = "start_date_time")
    //@CreationTimestamp
    private Date startDateTime;

    @Column(name = "end_date_time")
    //@UpdateTimestamp
    private Date endDateTime;

    @Size(max = 5, message = "Project member can be maximum 5")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_member",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> member = new HashSet<>();
}
