package healthcare.repository;


import healthcare.model.Doctor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;
import java.util.Optional;


public class DoctorRepository {


    private SessionFactory sessionFactory;
    public DoctorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public List<Doctor> getAllDoctors() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Doctor", Doctor.class).list();
        }
    }

    public Optional<Doctor> getDoctorById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Doctor doctor = session.get(Doctor.class, id);
            return Optional.ofNullable(doctor);
        }
    }

    public Doctor addDoctor(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(doctor);
            session.getTransaction().commit();
            return doctor;
        }
    }

    public Doctor updateDoctor(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(doctor);
            session.getTransaction().commit();
            return doctor;
        }
    }

    public void deleteDoctor(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, id);
            if (doctor != null) {
                session.delete(doctor);
            }
            session.getTransaction().commit();
        }
    }

    public Optional<Object> findById(int id) {
        return Optional.empty();
    }

    public List<Doctor> findAll() {
        return List.of();
    }

    public void deleteById(int id) {
    }

    public Doctor save(Doctor doctor) {
        return doctor;
    }
}

