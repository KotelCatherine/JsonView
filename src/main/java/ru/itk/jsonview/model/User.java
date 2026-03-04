package ru.itk.jsonview.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itk.jsonview.view.Views;

import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.UserSummary.class)
    private UUID id;

    @Column(name = "firstname")
    @JsonView(Views.UserSummary.class)
    private String firstName;

    @Column(name = "lastname")
    @JsonView(Views.UserSummary.class)
    private String lastName;

    @Column(name = "email")
    @JsonView(Views.UserDetails.class)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonView(Views.UserDetails.class)
    private List<Order> orders;

}
