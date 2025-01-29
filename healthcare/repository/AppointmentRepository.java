package healthcare.repository;


import healthcare.model.Appointment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


import java.util.List;
import java.util.Optional;


public class AppointmentRepository {


    private SessionFactory sessionFactory;

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
        updateAppointment(appointment);
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

    public Appointment save(Appointment appointment) {
        return appointment;
    }

    public void deleteById(int id) {
    }
}
