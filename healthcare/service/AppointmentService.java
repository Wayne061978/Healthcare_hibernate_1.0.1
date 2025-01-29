package healthcare.service;


import healthcare.model.Appointment;
import healthcare.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;


public class AppointmentService {


    private final AppointmentRepository appointmentRepository;
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    // Get appointment by ID
    public Optional<Appointment> getAppointmentById(int id) {
        return appointmentRepository.getAppointmentById(id);
    }

    // Add a new appointment
    public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Update an existing appointment
    public void updateAppointment(Appointment appointment) {
        appointmentRepository.updateAppointment(appointment);
    }


    // Delete an appointment
    public void deleteAppointment(int id) {
        appointmentRepository.deleteById(id);
    }
}
