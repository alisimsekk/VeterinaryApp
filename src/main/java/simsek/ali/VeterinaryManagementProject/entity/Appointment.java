package simsek.ali.VeterinaryManagementProject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Table(name = "appointment")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne (fetch = FetchType.EAGER) //Appointment sildiğimizde veya güncellediğimizde doctoru silmemize yada güncellememize gerek olmadığından cascade vermedik.
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "animal_id")
    private Animal animal;

    @OneToOne (mappedBy = "appointment")
    @JsonIgnore
    private Report report;
}
