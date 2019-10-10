package br.com.registration.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "The field 'street' is mandatory")
    @Column(nullable = false)
    private String street;

    @NotNull(message = "The field 'number' is mandatory")
    @Column(nullable = false)
    private String number;

    @NotNull(message = "The field 'neighborhood' is mandatory")
    @Column(nullable = false)
    private String neighborhood;

    private String complement;

    @NotNull(message = "The field 'cep' is mandatory")
    @Column(nullable = false)
    private String cep;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

}
