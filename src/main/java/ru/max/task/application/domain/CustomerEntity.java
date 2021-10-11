package ru.max.task.application.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true, length = 45)
    String login;

    @Column(nullable = false, unique = true, length = 45)
    String email;

    @Column(nullable = false, length = 64)
    String password;

    @Column(nullable = false, length = 20)
    String name;
}
