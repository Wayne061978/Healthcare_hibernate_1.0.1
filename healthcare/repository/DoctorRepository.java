package healthcare.repository;

import healthcare.model.Doctor;
import healthcare.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class DoctorRepository {

    private final SessionFactory sessionFactory;

    public DoctorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Doctor> getAllDoctors() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Doctor", Doctor.class).list();
        }
    }

    public void createDoctor(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(doctor);
            transaction.commit();
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

    public void updateDoctor(Doctor doctor) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(doctor);
            session.getTransaction().commit();
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

    public void addPatientToDoctor(int doctorId, Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorId);
            if (doctor != null && !doctor.getPatients().contains(patient)) {
                doctor.getPatients().add(patient);
                session.merge(doctor);
            }
            transaction.commit();
        }
    }

    public void removePatientFromDoctor(int doctorId, Patient patient) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, doctorId);
            if (doctor != null && doctor.getPatients().contains(patient)) {
                doctor.getPatients().remove(patient);
                session.merge(doctor);
            }
            transaction.commit();
        }
    } // Properly closed the `removePatientFromDoctor` method.

    public Optional<Doctor> findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Doctor doctor = session.get(Doctor.class, id);
            return Optional.ofNullable(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error finding doctor with id: " + id, e);
        }
    }

    public List<Doctor> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Doctor", Doctor.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error retrieving list of doctors", e);
        }
    }

    public void deleteById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Doctor doctor = session.get(Doctor.class, id);
            if (doctor != null) {
                session.delete(doctor);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting doctor with id: " + id, e);
        }
    }

    public Doctor save(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null");
        }

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(doctor); // Handles save or update based on entity state
            transaction.commit();
            return doctor;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Ensure rollback on exception
            }
            // Log exception properly
            System.err.println("Error saving doctor with ID: " + doctor.getDoctorId());
            throw new RuntimeException("Error saving doctor with ID: " + doctor.getDoctorId(), e);
        }
    }
}
