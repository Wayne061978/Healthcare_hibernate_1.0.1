package healthcare.repository;


import healthcare.model.Appointment;
import healthcare.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;
import java.util.Optional;


public class AppointmentRepository {


    private final SessionFactory sessionFactory;
//123123
    public AppointmentRepository(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;
    }

    public void createAppointment(Appointment appointment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(appointment);
            transaction.commit();
        }
    }

    public List<Appointment> getAllAppointments() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Appointment", Appointment.class).list();
        }
    }

    public Optional<Appointment> getAppointmentById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Appointment appointment = session.get(Appointment.class, id);
            return Optional.ofNullable(appointment);
        }
    }

    public Appointment addAppointment(Appointment appointment) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(appointment);
            session.getTransaction().commit();
            return appointment;
        }
    }

    public void updateAppointment(Appointment appointment) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(appointment);
            transaction.commit();
        }
    }
    public void deleteAppointment(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Appointment appointment = session.get(Appointment.class, id);
            if (appointment != null) {
                session.delete(appointment);
            }
            session.getTransaction().commit();
        }
    }

    public boolean hasOtherAppointmentsBetween(int doctorId, int patientId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            String query = "SELECT COUNT(a) FROM Appointment a " +
                    "WHERE a.doctor.doctorId = :doctorId " +
                    "AND a.patient.patientId = :patientId";
            Long count = session.createQuery(query, Long.class)
                    .setParameter("doctorId", doctorId)
                    .setParameter("patientId", patientId)
                    .uniqueResult();
            transaction.commit();
            return count != null && count > 1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error during data access", e);
        }
    }

    public Appointment save(Appointment appointment) {
        return appointment;
    }

    public void deleteById(int id) {
    }
}
