package simsek.ali.VeterinaryManagementProject.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import simsek.ali.VeterinaryManagementProject.dto.request.AppointmentRequestDto;
import simsek.ali.VeterinaryManagementProject.dto.request.VaccineWithoutCustomerRequestDto;
import simsek.ali.VeterinaryManagementProject.entity.Appointment;
import simsek.ali.VeterinaryManagementProject.entity.AvailableDate;
import simsek.ali.VeterinaryManagementProject.entity.Customer;
import simsek.ali.VeterinaryManagementProject.entity.Vaccine;
import simsek.ali.VeterinaryManagementProject.repository.AppointmentRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AvailableDateService availableDateService;
    private final ModelMapper modelMapper;

    public List<Appointment> findAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment findAppointmentById (Long id){
        return appointmentRepository.findById(id).orElseThrow(() -> new RuntimeException("id:" + id + "Appointment could not found!!!"));
    }

    public List<Appointment> findAppointmentByDoctorIdAndDateRange(Long doctorId, LocalDate startDate, LocalDate endDate) {
        if (doctorId==null){
            return appointmentRepository.findByDateBetween(startDate.atStartOfDay(),endDate.atStartOfDay());
        }
        return appointmentRepository.findByDoctorIdAndDateBetween(doctorId,startDate.atStartOfDay(),endDate.atStartOfDay());
    }

    public List<Appointment> findAppointmentByAnimalIdAndDateRange(Long animalId, LocalDate startDate, LocalDate endDate) {
        if (animalId==null){
            return appointmentRepository.findByDateBetween(startDate.atStartOfDay(),endDate.atStartOfDay());
        }
        return appointmentRepository.findByAnimalIdAndDateBetween(animalId,startDate.atStartOfDay(),endDate.atStartOfDay());
    }

    public Appointment createAppointment(AppointmentRequestDto appointmentRequestDto){

        Optional<Appointment> existAppointmentWithSameSpecs =
                appointmentRepository.findByDateAndDoctorIdAndAnimalId(appointmentRequestDto.getDate(), appointmentRequestDto.getDoctor().getId(), appointmentRequestDto.getAnimal().getId());

        Optional<AvailableDate> existsAvailableDateByDoctorIdAndDate =
                availableDateService.findByDoctorIdAndDate(appointmentRequestDto.getDoctor().getId(), appointmentRequestDto.getDate().toLocalDate());

        Optional<Appointment> existAppointmentWithDateAndDoctorId =
                appointmentRepository.findByDateAndDoctorId(appointmentRequestDto.getDate(), appointmentRequestDto.getDoctor().getId());

        if (existAppointmentWithSameSpecs.isPresent()){
            throw new RuntimeException("The Appointment has already been saved.");
        }

        if (existsAvailableDateByDoctorIdAndDate.isEmpty()){
            throw new RuntimeException("The Doctor doesn't work this day.");
        }

        if (!existAppointmentWithDateAndDoctorId.isEmpty()){
            throw new RuntimeException("The doctor has another appointment.");
        }

        Appointment newAppointment = modelMapper.map(appointmentRequestDto, Appointment.class);
        return appointmentRepository.save(newAppointment);
    }

    public Appointment updateAppointment (Long id, AppointmentRequestDto appointmentRequestDto){

        Optional<Appointment> appointmentFromDb = appointmentRepository.findById(id);

        if (appointmentFromDb.isEmpty()){
            throw new RuntimeException("id:" + id + " Appointment could not found!!!");
        }

        Optional<Appointment> existAppointmentWithSameSpecs =
                appointmentRepository.findByDateAndDoctorIdAndAnimalId(appointmentRequestDto.getDate(), appointmentRequestDto.getDoctor().getId(), appointmentRequestDto.getAnimal().getId());

        if (!existAppointmentWithSameSpecs.isEmpty()){
            throw new RuntimeException("\"This Appointment has already been registered. That's why this request causes duplicate data\"");
        }

        Optional<AvailableDate> existsAvailableDateByDoctorIdAndDate =
                availableDateService.findByDoctorIdAndDate(appointmentRequestDto.getDoctor().getId(), appointmentRequestDto.getDate().toLocalDate());

        if (existsAvailableDateByDoctorIdAndDate.isEmpty()){
            throw new RuntimeException("The Doctor doesn't work this day.");
        }

        Optional<Appointment> existAppointmentWithDateAndDoctorId =
                appointmentRepository.findByDateAndDoctorId(appointmentRequestDto.getDate(), appointmentRequestDto.getDoctor().getId());

        if (!existAppointmentWithDateAndDoctorId.isEmpty()){
            throw new RuntimeException("The doctor has another appointment.");
        }

        Appointment updatedAppointment = appointmentFromDb.get();
        modelMapper.map(appointmentRequestDto, updatedAppointment); // appointmentRequestDto -> Appoinment
        return appointmentRepository.save(updatedAppointment);
    }

    public String deleteAppointment (Long id){
        Optional<Appointment> appointmentFromDb = appointmentRepository.findById(id);

        if (appointmentFromDb.isEmpty()){
            throw new RuntimeException("This vaccine could not found!!!");
        }
        else {
            appointmentRepository.delete(appointmentFromDb.get());
            return "Appointment deleted.";
        }
    }
}
