package healthcare.service;



import healthcare.model.Doctor;
import healthcare.repository.DoctorRepository;

import java.util.List;
import java.util.Optional;


public class DoctorService {


    private DoctorRepository doctorRepository;
//123123
public DoctorService(DoctorRepository doctorRepository) {
    this.doctorRepository = doctorRepository;
}

    public void createDoctor(Doctor doctor) { doctorRepository.createDoctor(doctor);}

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }


    public Optional<Object> getDoctorById(int id) {
        return Optional.ofNullable(doctorRepository.findById(id));
    }


    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }


    public void updateDoctor(Doctor doctor) {
        doctorRepository.updateDoctor(doctor);
    }



    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }
}
