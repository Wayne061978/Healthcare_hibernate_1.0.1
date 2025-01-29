package healthcare.service;



import healthcare.model.Doctor;
import healthcare.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;


public class DoctorService {


    private DoctorRepository doctorRepository;

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by ID
    public Optional<Object> getDoctorById(int id) {
        return doctorRepository.findById(id);
    }

    // Add a new doctor
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Update an existing doctor
    public void updateDoctor(Doctor doctor) {
        doctorRepository.updateDoctor(doctor);
    }


    // Delete a doctor
    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }
}
