package simsek.ali.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import simsek.ali.VeterinaryManagementProject.entity.Doctor;
import simsek.ali.VeterinaryManagementProject.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {

    public final DoctorService doctorService;

    @GetMapping
    public List<Doctor> findAllDoctors (){
        return doctorService.findAllDoctors();
    }

    @GetMapping("/{id}")
    public Doctor findDoctorById (@PathVariable Long id){
        return doctorService.findDoctorById(id);
    }

    @PostMapping
    public Doctor create (@RequestBody Doctor doctor){
        return doctorService.createDoctor(doctor);
    }
}
