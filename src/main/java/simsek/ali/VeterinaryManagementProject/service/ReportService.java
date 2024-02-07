package simsek.ali.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import simsek.ali.VeterinaryManagementProject.dto.request.ReportRequestDto;
import simsek.ali.VeterinaryManagementProject.dto.response.AnimalForReportResponseDto;
import simsek.ali.VeterinaryManagementProject.dto.response.AppointmentForReportResponseDto;
import simsek.ali.VeterinaryManagementProject.dto.response.ReportResponseDto;
import simsek.ali.VeterinaryManagementProject.entity.Appointment;
import simsek.ali.VeterinaryManagementProject.entity.Report;
import simsek.ali.VeterinaryManagementProject.repository.ReportRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final AppointmentService appointmentService;
    private final ModelMapper modelMapper;

    public List<ReportResponseDto> findAllReports (){
        List<Report> reportList = reportRepository.findAll();
        List<ReportResponseDto> reportResponseDtoList = new ArrayList<>();
        for (Report report : reportList){
            ReportResponseDto reportResponseDto = modelMapper.map(report, ReportResponseDto.class);

            AnimalForReportResponseDto animalForReportResponseDto = modelMapper.map(report.getAppointment().getAnimal(),AnimalForReportResponseDto.class);
            reportResponseDto.setAnimalForReportResponseDto(animalForReportResponseDto);

            AppointmentForReportResponseDto appointmentForReportResponseDto = modelMapper.map(report.getAppointment(), AppointmentForReportResponseDto.class);
            reportResponseDto.setAppointmentForReportResponseDto(appointmentForReportResponseDto);

            reportResponseDtoList.add(reportResponseDto);
        }
        return reportResponseDtoList;
    }

    public Report findReportById (Long id){
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Report could not found!!!"));
    }

    public Report createReport(ReportRequestDto reportRequestDto){
        Optional<Report> existReportWithSameSpecs = reportRepository.findByAppointmentId(reportRequestDto.getAppointmentId());

        if (existReportWithSameSpecs.isPresent()){
            throw new RuntimeException("The Report has already been saved.");
        }

        Appointment appointment = appointmentService.findAppointmentById(reportRequestDto.getAppointmentId());
        reportRequestDto.setAppointmentId(null);
        Report newReport = modelMapper.map(reportRequestDto, Report.class);
        newReport.setAppointment(appointment);
        return reportRepository.save(newReport);
    }

    public Report updateReport (Long id, ReportRequestDto reportRequestDto){
        Optional<Report> reportFromDb = reportRepository.findById(id);
        Optional<Report> existOtherReportFromRequest = reportRepository.findByAppointmentId(reportRequestDto.getAppointmentId());

        if (reportFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + " Report could not found!!!");
        }

        if (existOtherReportFromRequest.isPresent() && !existOtherReportFromRequest.get().getId().equals(id)){
            throw new RuntimeException("This Report has already been registered. That's why this request causes duplicate data");
        }
        Report updatedReport = reportFromDb.get();
        updatedReport.setDiagnosis(reportRequestDto.getDiagnosis());
        updatedReport.setPrice(reportRequestDto.getPrice());
        updatedReport.setAppointment(appointmentService.findAppointmentById(reportRequestDto.getAppointmentId()));
        return reportRepository.save(updatedReport);
    }

    public String deleteReport (Long id){
        Optional<Report> reportFromDb = reportRepository.findById(id);
        if (reportFromDb.isEmpty()){
            throw new RuntimeException("This Report could not found!!!");
        }
        else {
            reportRepository.delete(reportFromDb.get());
            return "Report deleted.";
        }
    }

}
