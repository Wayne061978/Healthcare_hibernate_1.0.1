//package healthcare;
//
//import healthcare.model.Patient;
//import healthcare.service.PatientService;
//import healthcare.repository.PatientRepositoryImpl;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();
//        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
//        PatientService patientService = new PatientService(patientRepository);
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("1. Create Patient");
//        System.out.println("2. Read Patient");
//        System.out.println("3. Update Patient");
//        System.out.println("4. Delete Patient");
//
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        try {
//            switch (choice) {
//                case 1:
//                    // Application calls the service layer, not the repository directly
//                    Patient newPatient = new Patient();
//                    System.out.print("Enter first name: ");
//                    newPatient.setFirstName(scanner.nextLine());
//                    System.out.print("Enter last name: ");
//                    newPatient.setLastName(scanner.nextLine());
//                    System.out.print("Enter date of birth (YYYY-MM-DD): ");
//                    newPatient.setDateOfBirth(scanner.nextLine());
//                    System.out.print("Enter email: ");
//                    newPatient.setEmail(scanner.nextLine());
//                    System.out.print("Enter phone number: ");
//                    newPatient.setPhoneNumber(scanner.nextLine());
//                    patientService.createPatient(newPatient);  // Use service here
//                    System.out.println("Patient created successfully.");
//                    break;
//                case 2:
//                    // Application calls the service layer, not the repository directly
//                    System.out.print("Enter Patient ID: ");
//                    int patientId = scanner.nextInt();
//                    Patient patient = patientService.getPatientById(patientId);  // Use service here
//                    if (patient != null) {
//                        System.out.println("Patient ID: " + patient.getPatientId());
//                        System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
//                        System.out.println("Date of Birth: " + patient.getDateOfBirth());
//                        System.out.println("Email: " + patient.getEmail());
//                        System.out.println("Phone: " + patient.getPhoneNumber());
//                    } else {
//                        System.out.println("Patient not found.");
//                    }
//                    break;
//                case 3:
//                    // Application calls the service layer, not the repository directly
//                    System.out.print("Enter Patient ID: ");
//                    patientId = scanner.nextInt();
//                    scanner.nextLine();  // consume newline
//                    patient = patientService.getPatientById(patientId);  // Use service here
//                    if (patient != null) {
//                        System.out.print("Enter new first name: ");
//                        patient.setFirstName(scanner.nextLine());
//                        System.out.print("Enter new last name: ");
//                        patient.setLastName(scanner.nextLine());
//                        System.out.print("Enter new date of birth (YYYY-MM-DD): ");
//                        patient.setDateOfBirth(scanner.nextLine());
//                        System.out.print("Enter new email: ");
//                        patient.setEmail(scanner.nextLine());
//                        System.out.print("Enter new phone number: ");
//                        patient.setPhoneNumber(scanner.nextLine());
//                        patientService.updatePatient(patient);  // Use service here
//                        System.out.println("Patient updated successfully.");
//                    } else {
//                        System.out.println("Patient not found.");
//                    }
//                    break;
//                case 4:
//                    // Application calls the service layer, not the repository directly
//                    System.out.print("Enter Patient ID: ");
//                    patientId = scanner.nextInt();
//                    patientService.deletePatient(patientId);  // Use service here
//                    System.out.println("Patient deleted successfully.");
//                    break;
//                default:
//                    System.out.println("Invalid choice.");
//            }
//        } finally {
//            scanner.close();
//            sessionFactory.close();
//        }
//    }
//}

package healthcare;

import healthcare.model.Patient;
import healthcare.model.Doctor;
import healthcare.model.Appointment;
import healthcare.service.PatientService;
import healthcare.service.DoctorService;
import healthcare.service.AppointmentService;
import healthcare.repository.PatientRepositoryImpl;
import healthcare.repository.DoctorRepository;
import healthcare.repository.AppointmentRepository;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("patient.cfg.xml").buildSessionFactory();

        // Initializing repositories and services
        PatientRepositoryImpl patientRepository = new PatientRepositoryImpl(sessionFactory);
        DoctorRepository doctorRepository = new DoctorRepository(sessionFactory);
        AppointmentRepository appointmentRepository = new AppointmentRepository(sessionFactory);

        PatientService patientService = new PatientService(patientRepository);
        DoctorService doctorService = new DoctorService(doctorRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n---- Healthcare System ----");
            System.out.println("1. Manage Patients");
            System.out.println("2. Manage Doctors");
            System.out.println("3. Manage Appointments");
            System.out.println("4. Exit");

            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            switch (mainChoice) {
                case 1:
                    managePatients(scanner, patientService);
                    break;
                case 2:
                    manageDoctors(scanner, doctorService);
                    break;
                case 3:
                    manageAppointments(scanner, appointmentService);
                    break;
                case 4:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    sessionFactory.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Manage Patients
    private static void managePatients(Scanner scanner, PatientService patientService) {
        System.out.println("\n---- Patient Management ----");
        System.out.println("1. Create Patient");
        System.out.println("2. Read Patient");
        System.out.println("3. Update Patient");
        System.out.println("4. Delete Patient");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                Patient newPatient = new Patient();
                System.out.print("Enter first name: ");
                newPatient.setFirstName(scanner.nextLine());
                System.out.print("Enter last name: ");
                newPatient.setLastName(scanner.nextLine());
                System.out.print("Enter date of birth (YYYY-MM-DD): ");
                newPatient.setDateOfBirth(scanner.nextLine());
                System.out.print("Enter email: ");
                newPatient.setEmail(scanner.nextLine());
                System.out.print("Enter phone number: ");
                newPatient.setPhoneNumber(scanner.nextLine());
                patientService.createPatient(newPatient);
                System.out.println("Patient created successfully.");
                break;
            case 2:
                System.out.print("Enter Patient ID: ");
                int patientId = scanner.nextInt();
                Patient patient = patientService.getPatientById(patientId);
                if (patient != null) {
                    System.out.println("Patient: " + patient);
                } else {
                    System.out.println("Patient not found.");
                }
                break;
            case 3:
                System.out.print("Enter Patient ID: ");
                patientId = scanner.nextInt();
                scanner.nextLine();
                patient = patientService.getPatientById(patientId);
                if (patient != null) {
                    System.out.print("Enter new email: ");
                    patient.setEmail(scanner.nextLine());
                    patientService.updatePatient(patient);
                    System.out.println("Patient updated successfully.");
                } else {
                    System.out.println("Patient not found.");
                }
                break;
            case 4:
                System.out.print("Enter Patient ID: ");
                patientId = scanner.nextInt();
                patientService.deletePatient(patientId);
                System.out.println("Patient deleted successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Manage Doctors
    private static void manageDoctors(Scanner scanner, DoctorService doctorService) {
        System.out.println("\n---- Doctor Management ----");
        System.out.println("1. Create Doctor");
        System.out.println("2. Read Doctor");
        System.out.println("3. Update Doctor");
        System.out.println("4. Delete Doctor");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                Doctor newDoctor = new Doctor();
                System.out.print("Enter first name: ");
                newDoctor.setFirstName(scanner.nextLine());
                System.out.print("Enter last name: ");
                newDoctor.setLastName(scanner.nextLine());
                System.out.print("Enter specialty: ");
                newDoctor.setSpecialty(scanner.nextLine());
                System.out.print("Enter email: ");
                newDoctor.setEmail(scanner.nextLine());
                doctorService.addDoctor(newDoctor);
                System.out.println("Doctor created successfully.");
                break;
            case 2:
                System.out.print("Enter Doctor ID: ");
                int doctorId = scanner.nextInt();
                // FIX: Directly extracting the Doctor object from Optional
                Doctor doctor = (Doctor) doctorService.getDoctorById(doctorId).orElse(null);
                if (doctor != null) {
                    System.out.println("Doctor: " + doctor);
                } else {
                    System.out.println("Doctor not found.");
                }
                break;
            case 3:
                System.out.print("Enter Doctor ID: ");
                doctorId = scanner.nextInt();
                scanner.nextLine();
                // FIX: Directly extracting the Doctor object from Optional
                doctor = (Doctor) doctorService.getDoctorById(doctorId).orElse(null);
                if (doctor != null) {
                    System.out.print("Enter new email: ");
                    doctor.setEmail(scanner.nextLine());
                    doctorService.updateDoctor(doctor);
                    System.out.println("Doctor updated successfully.");
                } else {
                    System.out.println("Doctor not found.");
                }
                break;
            case 4:
                System.out.print("Enter Doctor ID: ");
                doctorId = scanner.nextInt();
                doctorService.deleteDoctor(doctorId);
                System.out.println("Doctor deleted successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // Manage Appointments
    private static void manageAppointments(Scanner scanner, AppointmentService appointmentService) {
        System.out.println("\n---- Appointment Management ----");
        System.out.println("1. Create Appointment");
        System.out.println("2. Read Appointment");
        System.out.println("3. Update Appointment");
        System.out.println("4. Delete Appointment");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                Appointment newAppointment = new Appointment();
                System.out.print("Enter Patient ID: ");
                newAppointment.setPatientId(scanner.nextInt());
                System.out.print("Enter Doctor ID: ");
                newAppointment.setDoctorId(scanner.nextInt());
                scanner.nextLine();
                System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
                newAppointment.setAppointmentDate(LocalDate.parse(scanner.nextLine()));
                System.out.print("Enter Notes (optional): ");
                newAppointment.setNotes(scanner.nextLine());
                appointmentService.addAppointment(newAppointment);
                System.out.println("Appointment created successfully.");
                break;
            case 2:
                System.out.print("Enter Appointment ID: ");
                int appointmentId = scanner.nextInt();
                Appointment appointment = appointmentService.getAppointmentById(appointmentId).orElse(null);
                if (appointment != null) {
                    System.out.println("Appointment: " + appointment);
                } else {
                    System.out.println("Appointment not found.");
                }
                break;
            case 3:
                System.out.print("Enter Appointment ID: ");
                appointmentId = scanner.nextInt();
                scanner.nextLine();
                appointment = appointmentService.getAppointmentById(appointmentId).orElse(null);
                if (appointment != null) {
                    System.out.print("Enter new notes: ");
                    appointment.setNotes(scanner.nextLine());
                    appointmentService.updateAppointment(appointment);
                    System.out.println("Appointment updated successfully.");
                } else {
                    System.out.println("Appointment not found.");
                }
                break;
            case 4:
                System.out.print("Enter Appointment ID: ");
                appointmentId = scanner.nextInt();
                appointmentService.deleteAppointment(appointmentId);
                System.out.println("Appointment deleted successfully.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}

