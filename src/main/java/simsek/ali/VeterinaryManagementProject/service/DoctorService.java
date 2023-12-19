package simsek.ali.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import simsek.ali.VeterinaryManagementProject.dto.request.DoctorRequestDto;
import simsek.ali.VeterinaryManagementProject.entity.Doctor;
import simsek.ali.VeterinaryManagementProject.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public List<Doctor> findAllDoctors (){
        return doctorRepository.findAll();
    }

    public Doctor findDoctorById (Long id){
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Doctor could not found!!!"));
    }

    public Doctor createDoctor(DoctorRequestDto doctorRequestDto){
        Optional<Doctor> existDoctorWithSameSpecs = doctorRepository.findByNameAndEmail(doctorRequestDto.getName(), doctorRequestDto.getEmail());

        if (existDoctorWithSameSpecs.isPresent()){
            throw new RuntimeException("The doctor has already been saved.");
        }
        Doctor newDoctor = modelMapper.map(doctorRequestDto, Doctor.class);
        return doctorRepository.save(newDoctor);
    }

    public Doctor updateDoctor (Long id, DoctorRequestDto doctorRequestDto){
        Optional<Doctor> doctorFromDb = doctorRepository.findById(id);
        Optional<Doctor> existOtherDoctorFromRequest = doctorRepository.findByNameAndEmail(doctorRequestDto.getName(), doctorRequestDto.getEmail());

        if (doctorFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + "Doctor could not found!!!");
        }

        if (existOtherDoctorFromRequest.isPresent() && !existOtherDoctorFromRequest.get().getId().equals(id)){
            throw new RuntimeException("This doctor has already been registered. That's why this request causes duplicate data");
        }

        Doctor updatedDoctor = doctorFromDb.get();
        modelMapper.map(doctorRequestDto, updatedDoctor); // DoctorRequestDto -> Doctor
        return doctorRepository.save(updatedDoctor);
    }

    public String deleteDoctor (Long id){
        Optional<Doctor> doctorFromDb = doctorRepository.findById(id);
        if (doctorFromDb.isEmpty()){
            throw new RuntimeException("This doctor could not found!!!");
        }
        else {
            doctorRepository.delete(doctorFromDb.get());
            return "Doctor deleted.";
        }
    }
}
