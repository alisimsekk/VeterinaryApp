package simsek.ali.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import simsek.ali.VeterinaryManagementProject.dto.request.AnimalRequestDto;
import simsek.ali.VeterinaryManagementProject.entity.Animal;
import simsek.ali.VeterinaryManagementProject.repository.AnimalRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final ModelMapper modelMapper;

    public List<Animal> findAllAnimals (){
        return animalRepository.findAll();
    }

    public Animal findAnimalById (Long id){
        return animalRepository.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "animal could not found!!!"));
    }

    public Animal createAnimal(AnimalRequestDto animalRequestDto){
        Optional<Animal> existAnimalWithSameSpecs = animalRepository.findByNameAndSpeciesAndGenderAndDateOfBirth(animalRequestDto.getName(),animalRequestDto.getSpecies(),animalRequestDto.getGender(),animalRequestDto.getDateOfBirth());

        if (existAnimalWithSameSpecs.isPresent()){
            throw new RuntimeException("The animal has already been saved.");
        }
        Animal newAnimal = modelMapper.map(animalRequestDto, Animal.class);
        return animalRepository.save(newAnimal);
    }
}
