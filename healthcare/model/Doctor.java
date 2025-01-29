package healthcare.model;

import jakarta.persistence.*;



@Entity
@Table(name = "Doctors")
public class Doctor {

    // Getters and Setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DoctorId")
    private int doctorId;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @Column(name = "Specialty", nullable = false)
    private String specialty;

    @Column(name = "Email", unique = true, nullable = false)
    private String email;

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", specialty='" + specialty + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
