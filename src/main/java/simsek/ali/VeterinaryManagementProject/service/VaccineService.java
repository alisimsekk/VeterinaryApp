package simsek.ali.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import simsek.ali.VeterinaryManagementProject.dto.request.DoctorRequestDto;
import simsek.ali.VeterinaryManagementProject.dto.request.VaccineRequestDto;
import simsek.ali.VeterinaryManagementProject.entity.Doctor;
import simsek.ali.VeterinaryManagementProject.entity.Vaccine;
import simsek.ali.VeterinaryManagementProject.repository.VaccineRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    private final ModelMapper modelMapper;

    public List<Vaccine> findAllVaccines (){
        return vaccineRepository.findAll();
    }

    public Vaccine findVaccineById (Long id){
        return vaccineRepository.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Vaccine could not found!!!"));
    }

    public Vaccine createVaccine(VaccineRequestDto vaccineRequestDto){
        Optional<Vaccine> existValidVaccineWithSameSpecsAnd =
                vaccineRepository.findByNameAndCodeProtectionStartDateAfter(vaccineRequestDto.getName(), vaccineRequestDto.getCode(),vaccineRequestDto.getProtectionStartDate());

        if (existValidVaccineWithSameSpecsAnd.isPresent()){
            throw new RuntimeException("The vaccine you want to save is still protective for this animal.");
        }
        Vaccine newVaccine = modelMapper.map(vaccineRequestDto, Vaccine.class);
        return vaccineRepository.save(newVaccine);
    }

    public Vaccine updateVaccine (Long id, VaccineRequestDto vaccineRequestDto){
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        Optional<Vaccine> existOtherValidVaccineFromRequest =
                vaccineRepository.findByNameAndCodeProtectionStartDateAfter(vaccineRequestDto.getName(), vaccineRequestDto.getCode(),vaccineRequestDto.getProtectionStartDate());

        if (vaccineFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + "Vaccine could not found!!!");
        }

        if (existOtherValidVaccineFromRequest.isPresent() && !existOtherValidVaccineFromRequest.get().getId().equals(id)){
            throw new RuntimeException("This Vaccine has already been registered. That's why this request causes duplicate data");
        }

        if (existOtherValidVaccineFromRequest.isPresent()){
            throw new RuntimeException("The vaccine you want to update is still protective for this animal.");
        }

        Vaccine updatedVaccine = vaccineFromDb.get();
        modelMapper.map(vaccineRequestDto, updatedVaccine); // VaccineRequestDto -> Vaccine
        return vaccineRepository.save(updatedVaccine);
    }

    public String deleteVaccine (Long id){
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        if (vaccineFromDb.isEmpty()){
            throw new RuntimeException("This vaccine could not found!!!");
        }
        else {
            vaccineRepository.delete(vaccineFromDb.get());
            return "Vaccine deleted.";
        }
    }

}
