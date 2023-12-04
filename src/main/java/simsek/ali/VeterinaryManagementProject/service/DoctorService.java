package simsek.ali.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import simsek.ali.VeterinaryManagementProject.entity.Doctor;
import simsek.ali.VeterinaryManagementProject.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> findAllDoctors (){
        return doctorRepository.findAll();
    }

    public Doctor findDoctorById (Long id){
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Doctor does not found!!!"));
    }

    public Doctor createDoctor(Doctor doctor){
        Optional<Doctor> existDoctorWithSameSpecs = doctorRepository.getByNameAndEmail(doctor.getName(), doctor.getEmail());

        if (existDoctorWithSameSpecs.isPresent()){
            throw new RuntimeException("The doctor has already been saved.");
        }

        return doctorRepository.save(doctor);
    }
}
