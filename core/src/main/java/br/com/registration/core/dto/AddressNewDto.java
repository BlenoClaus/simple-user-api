package br.com.registration.core.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressNewDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    @Length(min=1, max=100)
    @NotEmpty
    private String street;
    @Length(min=1, max=10)
    @NotEmpty
    private String number;
    @Length(min=1, max=100)
    @NotEmpty
    private String neighborhood;
    private String complement;
    @Length(min=1, max=15)
    @NotEmpty
    private String cep;
}
