package com.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import com.app.patient.Patient;
import com.app.patient.ServicePlan;
import com.patientException.PatientException;

public class PatientUtils {

	public static Patient findPatientByPatientId(String patientId, Map<String, Patient> patientsMap) {
		return patientsMap.get(patientId);
	}

	public static Map<String, Patient> populateHM() {
		Patient a1 = new Patient("1", "Virat", "Kohli", LocalDate.parse("2000-01-01"), "vikram@gmail.com", "1234",
				1234567891,"236362258513","PAN1234567", LocalDate.parse("2000-01-01"), LocalDate.parse("2000-01-01"), 10000, ServicePlan.HIGH);

		Patient a2 = new Patient("2", "Sachin", "Tendulkar", LocalDate.parse("1973-04-24"), "sachin@gmail.com", "5678",
				987654321,"236362258513","TYU1234561", LocalDate.parse("1990-07-18"), LocalDate.parse("1990-07-25"), 15000, ServicePlan.LOW);

		Patient a3 = new Patient("3", "Rahul", "Dravid", LocalDate.parse("1973-01-11"), "rahul@gmail.com", "abcd",
				123123123,"236362258513","WER1678567", LocalDate.parse("1996-09-15"), LocalDate.parse("1996-09-22"), 12000, ServicePlan.MEDIUM);

		Patient a4 = new Patient("4", "MS", "Dhoni", LocalDate.parse("1981-07-07"), "dhoni@gmail.com", "efgh",
				456456456, "236362258513","SDT9994567",LocalDate.parse("2004-12-23"), LocalDate.parse("2004-12-30"), 18000, ServicePlan.HIGH);

		Patient a5 = new Patient("5", "Yuvraj", "Singh", LocalDate.parse("1981-12-12"), "yuvraj@gmail.com", "ijkl",
				789789789,"236362258513","VKR1454567", LocalDate.parse("2000-10-07"), LocalDate.parse("2000-10-14"), 11000, ServicePlan.LOW);

		// Create a new HashMap to store patients
		Map<String, Patient> patientsMap = new HashMap<>();

		// Add patients to the map with email addresses as keys
		patientsMap.put(a1.getEmail(), a1);
		patientsMap.put(a2.getEmail(), a2);
		patientsMap.put(a3.getEmail(), a3);
		patientsMap.put(a4.getEmail(), a4);
		patientsMap.put(a5.getEmail(), a5);

		// Return the populated map
		return patientsMap;
	}

//
//    public static Map<String, Patient> populateHM() {
//        Patient a1 = new Patient("1", "Virat", "Kohli", LocalDate.parse("2000-01-01"), "vikram@gmail.com", "1234",
//                1234567891, LocalDate.parse("2000-01-01"), LocalDate.parse("2000-01-01"), 10000, ServicePlan.HIGH);
//
//        Patient a2 = new Patient("2", "Sachin", "Tendulkar", LocalDate.parse("1973-04-24"), "sachin@gmail.com", "5678",
//                987654321, LocalDate.parse("1990-07-18"), LocalDate.parse("1990-07-25"), 15000, ServicePlan.LOW);
//
//        Patient a3 = new Patient("3", "Rahul", "Dravid", LocalDate.parse("1973-01-11"), "rahul@gmail.com", "abcd",
//                123123123, LocalDate.parse("1996-09-15"), LocalDate.parse("1996-09-22"), 12000, ServicePlan.MEDIUM);
//
//        Patient a4 = new Patient("4", "MS", "Dhoni", LocalDate.parse("1981-07-07"), "dhoni@gmail.com", "efgh",
//                456456456, LocalDate.parse("2004-12-23"), LocalDate.parse("2004-12-30"), 18000, ServicePlan.HIGH);
//
//        Patient a5 = new Patient("5", "Yuvraj", "Singh", LocalDate.parse("1981-12-12"), "yuvraj@gmail.com", "ijkl",
//                789789789, LocalDate.parse("2000-10-07"), LocalDate.parse("2000-10-14"), 11000, ServicePlan.LOW);
//
//        Patient[] patients = {a1, a2, a3, a4, a5};
//
//        Map<String, Patient> hm = new HashMap<>();
//        for (Patient patient : patients) {
//            hm.put(patient.getPatientId(), patient);
//        }
//
//        return hm;
//    }
//    
	public static Patient authenticatePatient(String email, String password, Map<String, Patient> map)
			throws PatientException {
		// Search for patient by email
		Patient patient = map.values().stream().filter(p -> p.getEmail().equals(email)).findFirst().orElse(null);

		if (patient == null)
			throw new PatientException("Invalid email, login failed");

		if (patient.getPassword().equals(password))
			return patient;

		throw new PatientException("Invalid password, login failed");
	}

	public static void writeDetails(Map<String, Patient> patient, String fileName) throws IOException {
		// Java App --> OOS --> FOS (bin file)
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
			out.writeObject(patient);// serialization
		} // JVM : out.close --fos.close --close the file handle
	}

	public static Map<String, Patient> readDetails(String fileName) throws IOException, ClassNotFoundException {
		// Java App <--- OIS <--- FIS (bin file)
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
			return (Map<String, Patient>) in.readObject();
		}
	}

}
