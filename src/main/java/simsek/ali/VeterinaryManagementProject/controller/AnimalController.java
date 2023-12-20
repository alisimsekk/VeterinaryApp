package simsek.ali.VeterinaryManagementProject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import simsek.ali.VeterinaryManagementProject.dto.request.AnimalRequestDto;
import simsek.ali.VeterinaryManagementProject.entity.Animal;
import simsek.ali.VeterinaryManagementProject.service.AnimalService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animals")
@RequiredArgsConstructor
public class AnimalController {

    public final AnimalService animalService;

    @GetMapping
    public List<Animal> findAllAnimals (){
        return animalService.findAllAnimals();
    }

    @GetMapping("/{id}")
    public Animal findAnimalById (@PathVariable Long id){
        return animalService.findAnimalById(id);
    }

    @PostMapping
    public Animal createAnimal (@RequestBody AnimalRequestDto animalRequestDto){
        return animalService.createAnimal(animalRequestDto);
    }
}
