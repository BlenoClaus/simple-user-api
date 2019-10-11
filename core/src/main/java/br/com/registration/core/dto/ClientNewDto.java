package br.com.registration.core.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientNewDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty
    private String cpf;
    @NotEmpty
    @Length(min=5, max=120, message="size must be between 5 and 120")
    private String name;
    private List<AddressNewDto> allAddress = new ArrayList<>();

}
