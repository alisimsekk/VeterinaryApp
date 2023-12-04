package simsek.ali.VeterinaryManagementProject.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Table(name = "vaccine")
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "finishDate")
    private LocalDate finishDate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "animal_id")
    private Animal animal;

}
