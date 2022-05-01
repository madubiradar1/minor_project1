
package com.example.L13.L13.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String orderReference;

    @Version
    Long version;

    double amount;

    @Enumerated(value = EnumType.STRING)
    OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    UserInfo userInfo;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
            @ToString.Exclude
    Book associatedBook;
}


