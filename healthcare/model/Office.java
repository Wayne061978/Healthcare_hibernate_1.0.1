package healthcare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Offices")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OfficeId")
    private int officeId;

    @Column(name = "Location", nullable = false)
    private String location;

    @Column(name = "Phone", nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name = "DoctorId")
    private Doctor doctor;
}