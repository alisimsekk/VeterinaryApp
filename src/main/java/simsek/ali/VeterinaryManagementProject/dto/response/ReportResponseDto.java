package simsek.ali.VeterinaryManagementProject.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportResponseDto {
    private Long id;
    private String diagnosis;
    private double price;
    private AnimalForReportResponseDto animalForReportResponseDto;
    private AppointmentForReportResponseDto appointmentForReportResponseDto;

}
