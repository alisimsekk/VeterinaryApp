package simsek.ali.VeterinaryManagementProject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import simsek.ali.VeterinaryManagementProject.dto.request.AnimalWithoutCustomerRequestDto;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VaccineWithoutCustomerRequestDto {
    private String name;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;
    private AnimalWithoutCustomerRequestDto animalWithoutCustomer;
}
