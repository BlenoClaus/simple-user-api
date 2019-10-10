package br.com.registration.core.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The field 'cpf' is mandatory")
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    private String cpf;

    @NotNull(message = "The field 'name' is mandatory")
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="client", cascade=CascadeType.ALL)
    private List<Address> allAddress = new ArrayList<>();

}
