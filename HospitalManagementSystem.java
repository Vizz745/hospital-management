import java.util.ArrayList;
import java.util.Scanner;

class Doctor {
    private String id;
    private String name;
    private String specialization;

    public Doctor(String id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + id + ", Name: " + name + ", Specialization: " + specialization;
    }
}

class Patient {
    private String id;
    private String name;
    private int age;

    public Patient(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Patient ID: " + id + ", Name: " + name + ", Age: " + age;
    }
}

class Appointment {
    private String appointmentId;
    private String doctorId;
    private String patientId;

    public Appointment(String appointmentId, String doctorId, String patientId) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + ", Doctor ID: " + doctorId + ", Patient ID: " + patientId;
    }
}

public class HospitalManagementSystem {
    public static void main(String[] args) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        ArrayList<Patient> patients = new ArrayList<>();
        ArrayList<Appointment> appointments = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Hospital Management System Menu ---");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Create Appointment");
            System.out.println("4. View Doctors");
            System.out.println("5. View Patients");
            System.out.println("6. View Appointments");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Add Doctor
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Doctor ID: ");
                    String doctorId = scanner.nextLine();
                    System.out.print("Enter Doctor Name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("Enter Specialization: ");
                    String specialization = scanner.nextLine();

                    doctors.add(new Doctor(doctorId, doctorName, specialization));
                    System.out.println("Doctor added successfully!");
                    break;

                case 2: // Add Patient
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Patient ID: ");
                    String patientId = scanner.nextLine();
                    System.out.print("Enter Patient Name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter Patient Age: ");
                    int age = scanner.nextInt();

                    patients.add(new Patient(patientId, patientName, age));
                    System.out.println("Patient added successfully!");
                    break;

                case 3: // Create Appointment
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Appointment ID: ");
                    String appointmentId = scanner.nextLine();
                    System.out.print("Enter Doctor ID: ");
                    String apptDoctorId = scanner.nextLine();
                    System.out.print("Enter Patient ID: ");
                    String apptPatientId = scanner.nextLine();

                    if (findDoctor(doctors, apptDoctorId) != null && findPatient(patients, apptPatientId) != null) {
                        appointments.add(new Appointment(appointmentId, apptDoctorId, apptPatientId));
                        System.out.println("Appointment created successfully!");
                    } else {
                        System.out.println("Invalid Doctor ID or Patient ID.");
                    }
                    break;

                case 4: // View Doctors
                    System.out.println("\n--- List of Doctors ---");
                    for (Doctor doctor : doctors) {
                        System.out.println(doctor);
                    }
                    break;

                case 5: // View Patients
                    System.out.println("\n--- List of Patients ---");
                    for (Patient patient : patients) {
                        System.out.println(patient);
                    }
                    break;

                case 6: // View Appointments
                    System.out.println("\n--- List of Appointments ---");
                    for (Appointment appointment : appointments) {
                        System.out.println(appointment);
                    }
                    break;

                case 7: // Exit
                    System.out.println("Thank you for using the Hospital Management System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static Doctor findDoctor(ArrayList<Doctor> doctors, String doctorId) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(doctorId)) {
                return doctor;
            }
        }
        return null;
    }

    private static Patient findPatient(ArrayList<Patient> patients, String patientId) {
        for (Patient patient : patients) {
            if (patient.getId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }
}

