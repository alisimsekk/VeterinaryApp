package simsek.ali.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simsek.ali.VeterinaryManagementProject.dto.request.ReportRequestDto;
import simsek.ali.VeterinaryManagementProject.dto.response.ReportResponseDto;
import simsek.ali.VeterinaryManagementProject.entity.Report;
import simsek.ali.VeterinaryManagementProject.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
class ReportController {

    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<List<ReportResponseDto>> findAllReports (){
        List<ReportResponseDto> reportList = reportService.findAllReports();
        return ResponseEntity.ok().body(reportList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> findReportById (@PathVariable Long id){
        return ResponseEntity.ok().body(reportService.findReportById(id));
    }

    @PostMapping
    public ResponseEntity<Report> createReport (@RequestBody ReportRequestDto reportRequestDto){
        Report createdReport = reportService.createReport(reportRequestDto);
        if (createdReport != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdReport);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport (@PathVariable Long id, @RequestBody ReportRequestDto reportRequestDto){
        Report updatedReport = reportService.updateReport(id,reportRequestDto);
        if (updatedReport != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedReport);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build() ;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteReport(@PathVariable Long id){
        return reportService.deleteReport(id);
    }

}
