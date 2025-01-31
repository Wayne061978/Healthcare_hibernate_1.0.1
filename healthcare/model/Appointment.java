package healthcare.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointmentId")
    private int appointmentId;

    @Column(name = "appointmentDate", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "notes", length = 500)
    private String notes;

    @Column(name = "patientId", insertable = false, updatable = false) // Prevent insert/update conflicts
    private int patientId;

    @Column(name = "doctorId", insertable = false, updatable = false) // Prevent insert/update conflicts
    private int doctorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", referencedColumnName = "patientId", nullable = false)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId", referencedColumnName = "doctorId", nullable = false)
    private Doctor doctor;

    // Getters and setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", appointmentDate=" + appointmentDate +
                ", notes='" + notes + '\'' +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                '}';
    }
}
