package simsek.ali.VeterinaryManagementProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String diagnosis;

    private double price;

    @OneToOne
    @JoinColumn (name = "appointment_id")
    private Appointment appointment;

    @OneToMany (mappedBy = "report", cascade = CascadeType.REMOVE)
    private List<Vaccine> vaccineList;

}
