package healthcare.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Assumes auto-increment strategy
    @Column(name = "doctorId")
    private int doctorId;

    @Column(name = "FirstName", nullable = false)
    private String firstName;

    @Column(name = "LastName", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Patient> patients = new HashSet<>();

    // Default constructor (needed by Hibernate)
    public Doctor() {
    }

    // Getters and setters
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    // Utility methods for managing patients
    public void addPatient(Patient patient) {
        patients.add(patient);
        patient.setDoctor(this);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
        patient.setDoctor(null);
    }

    public void setSpecialty(String s) {
    }

    public void setEmail(String s) {
    }

    public String getName() {
        return "Name";
    }
}