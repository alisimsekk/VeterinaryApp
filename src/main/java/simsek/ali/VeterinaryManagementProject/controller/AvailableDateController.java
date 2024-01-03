package simsek.ali.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import simsek.ali.VeterinaryManagementProject.dto.request.AvailableDateRequestDto;
import simsek.ali.VeterinaryManagementProject.dto.request.DoctorRequestDto;
import simsek.ali.VeterinaryManagementProject.entity.AvailableDate;
import simsek.ali.VeterinaryManagementProject.entity.Doctor;
import simsek.ali.VeterinaryManagementProject.service.AvailableDateService;
import simsek.ali.VeterinaryManagementProject.service.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/available-dates")
@RequiredArgsConstructor
public class AvailableDateController {

    public final AvailableDateService availableDateService;

    @GetMapping
    public List<AvailableDate> findAllAvailableDates (){
        return availableDateService.findAllAvailableDates();
    }

    @GetMapping("/{id}")
    public AvailableDate findAvailableDateById (@PathVariable Long id){
        return availableDateService.findAvailableDateById(id);
    }

    @PostMapping
    public AvailableDate createAvailableDate (@RequestBody AvailableDateRequestDto availableDateRequestDto){
        return availableDateService.createAvailableDate(availableDateRequestDto);
    }

    @PutMapping("/{id}")
    public AvailableDate updateAvailableDate (@PathVariable Long id, @RequestBody AvailableDateRequestDto availableDateRequestDto){
        return availableDateService.updateAvailableDate(id,availableDateRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteAvailableDate(@PathVariable Long id){
        return availableDateService.deleteAvailableDate(id);
    }
}
