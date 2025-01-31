package healthcare.repository;

import healthcare.model.Office;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class OfficeRepository {
    private final SessionFactory sessionFactory; // Define sessionFactory as a field

    public OfficeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory; // Initialize sessionFactory in the constructor
    }

    public void createOffice(Office office) {
        try (Session session = sessionFactory.openSession()) { // Use instance sessionFactory
            Transaction transaction = session.beginTransaction();
            session.save(office);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Office getOfficeById(int officeId) {
        try (Session session = sessionFactory.openSession()) { // Use instance sessionFactory
            return session.get(Office.class, officeId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateOffice(Office office) {
        try (Session session = sessionFactory.openSession()) { // Use instance sessionFactory
            Transaction transaction = session.beginTransaction();
            session.update(office);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOffice(int officeId) {
        try (Session session = sessionFactory.openSession()) { // Use instance sessionFactory
            Transaction transaction = session.beginTransaction();
            Office office = session.get(Office.class, officeId);
            if (office != null) {
                session.delete(office);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Office> getAllOffices() {
        try (Session session = sessionFactory.openSession()) { // Use instance sessionFactory
            Query<Office> query = session.createQuery("from Office", Office.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}