package simsek.ali.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simsek.ali.VeterinaryManagementProject.dto.request.AppointmentRequestDto;
import simsek.ali.VeterinaryManagementProject.dto.request.VaccineWithoutCustomerRequestDto;
import simsek.ali.VeterinaryManagementProject.entity.Appointment;
import simsek.ali.VeterinaryManagementProject.entity.Vaccine;
import simsek.ali.VeterinaryManagementProject.service.AppointmentService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<Appointment>> findAllAppointments(){
        List<Appointment> appointmentList = appointmentService.findAllAppointments();

        return ResponseEntity.ok().body(appointmentList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> findAppointmentById (@PathVariable Long id){
        Appointment appointment = appointmentService.findAppointmentById(id);
        if (appointment != null){
            return ResponseEntity.ok().body(appointment);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByDoctorAndDateRange")
    public ResponseEntity<List<Appointment>> findAppointmentByDoctorIdAndDateRange (@RequestParam(value = "id", required = false) Long id,@RequestParam LocalDate startDate,@RequestParam LocalDate endDate){
        List<Appointment> appointmentListSearchByDoctorAndDateRange = appointmentService.findAppointmentByDoctorIdAndDateRange(id, startDate, endDate);
        if (appointmentListSearchByDoctorAndDateRange != null){
            return ResponseEntity.ok().body(appointmentListSearchByDoctorAndDateRange);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByAnimalAndDateRange")
    public ResponseEntity<List<Appointment>> findAppointmentByAnimalIdAndDateRange (@RequestParam(value = "id", required = false) Long id,@RequestParam LocalDate startDate,@RequestParam LocalDate endDate){
        List<Appointment> appointmentListSearchByAnimalAndDateRange = appointmentService.findAppointmentByAnimalIdAndDateRange(id, startDate, endDate);
        if (appointmentListSearchByAnimalAndDateRange != null){
            return ResponseEntity.ok().body(appointmentListSearchByAnimalAndDateRange);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment (@RequestBody AppointmentRequestDto appointmentRequestDto){
        Appointment createdAppointment = appointmentService.createAppointment(appointmentRequestDto);
        if (createdAppointment != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment (@PathVariable Long id, @RequestBody AppointmentRequestDto appointmentRequestDto){
        Appointment updatedAppointment = appointmentService.updateAppointment(id,appointmentRequestDto);
        if (updatedAppointment != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedAppointment);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable Long id){
        return appointmentService.deleteAppointment(id);
    }


}
