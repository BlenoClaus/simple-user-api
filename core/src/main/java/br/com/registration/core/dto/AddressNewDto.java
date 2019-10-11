package br.com.registration.core.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressNewDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty private String street;
    @NotEmpty private String number;
    @NotEmpty private String neighborhood;
    private String complement;
    @NotEmpty private String cep;

}
