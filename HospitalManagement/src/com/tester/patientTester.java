package com.tester;

import static com.utils.PatientUtils.authenticatePatient;
import static com.utils.PatientUtils.populateHM;
import static com.utils.PatientUtils.readDetails;
import static com.utils.PatientUtils.writeDetails;
import static com.validation.PatientValidation.validatePatientInputs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.app.patient.Patient;
import com.patientException.PatientException;

public class patientTester {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			boolean exit = false;

			Map<String, Patient> patientsMap = populateHM();
			while (!exit) {
				System.out.println(
						"Options:\n1. Patient Registration\n2. Display All Patients\n3. Login\n4. Change Password\n5. Unsubscribe\n6. Sorting Customers by Date of Birth\n7. Sorting Customers by Date of Birth and Service Plan\n8. Enter Email to Remove Customer\n9. Sort by Date of Birth and Last Name\n10. Sorting Customers by Email\n11.Customers sorted by ID\n12.Customers sorted by Plan\n13.de-serialize file \n14.store in file \n15.store using bufferwriter\n16.store using bufferreader\n");
				System.out.println("Choose an option:");

				try {
					switch (sc.nextInt()) {
					case 1:
						System.out.println(
								"Enter customer details: firstName, lastName, email, password,phoneNo,adharCardNo,panCardNo, regAmount, dob, plan");
						Patient patient = validatePatientInputs(sc.next(), sc.next(), sc.next(), sc.next(),
								sc.nextDouble(), sc.next(), sc.next(), sc.nextDouble(), sc.next(), sc.next(),
								patientsMap);
						patientsMap.put(patient.getEmail(), patient);
						System.out.println("Patient registered successfully.");
						break;

					case 2:
						System.out.println("All Customers:");
						patientsMap.values().forEach(System.out::println);
						break;

					case 3:
						System.out.println("Enter email:");
						String email = sc.next();
						System.out.println("Enter password:");
						String password = sc.next();
						try {
							Patient authenticatedPatient = authenticatePatient(email, password, patientsMap);
							System.out.println("Successful login, your details: " + authenticatedPatient);
						} catch (PatientException e) {
							System.out.println(e.getMessage());
						}
						break;

					case 4:
						System.out.println("Enter email, old password, and new password:");
						String email1 = sc.next();
						String oldPassword = sc.next();
						Patient patientToChangePassword = authenticatePatient(email1, oldPassword, patientsMap);
						System.out.println("Enter new password:");
						String newPassword = sc.next();
						patientToChangePassword.setPassword(newPassword);

						System.out.println("Password changed successfully.");
						break;
					case 5:
						System.out.println("Enter email to unsubscribe:");
						String unsubscribeEmail = sc.next().trim(); // Trim the input to remove whitespace
						System.out.println("Trying to unsubscribe patient with email: " + unsubscribeEmail);

						// Debugging print statement to check if the patient map contains the trimmed
						// email
						System.out.println("Patients map contains key: " + patientsMap.containsKey(unsubscribeEmail));

						// Check if the patient exists with the given email (trimmed)
						if (patientsMap.containsKey(unsubscribeEmail)) {
							// If patient exists, remove them from the map
							patientsMap.remove(unsubscribeEmail);
							System.out.println("Patient unsubscribed successfully.");
						} else {
							// If patient doesn't exist, print appropriate message
							System.out.println("Patient not found with the given email.");
						}
						break;

					case 6:
						System.out.println("Sorting Customers by Date of Birth:");
						List<Patient> sortedByDob = new ArrayList<>(patientsMap.values());
						Collections.sort(sortedByDob, new Comparator<Patient>() {
							@Override
							public int compare(Patient p1, Patient p2) {
								return p1.getDob().compareTo(p2.getDob());
							}
						});
						for (Patient patient1 : sortedByDob) {
							System.out.println(patient1);
						}
						break;

					case 7:
						System.out.println("Sorting Customers by Date of Birth and Service Plan:");
						List<Patient> sortedByDobAndPlan = new ArrayList<>(patientsMap.values());
						Collections.sort(sortedByDobAndPlan, new Comparator<Patient>() {
							@Override
							public int compare(Patient p1, Patient p2) {
								int dobComparison = p1.getDob().compareTo(p2.getDob());
								if (dobComparison != 0) {
									return dobComparison;
								} else {
									return p1.getPlan().compareTo(p2.getPlan());
								}
							}
						});
						for (Patient patient1 : sortedByDobAndPlan) {
							System.out.println(patient1);
						}
						break;

					case 8:
						System.out.println("Enter email to remove customer:");
						String removeEmail = sc.next();
						patientsMap.remove(removeEmail);
						System.out.println("Customer removed successfully.");
						break;

					case 9:
						System.out.println("Sort by Date of Birth and Last Name:");
						List<Patient> sortedByDobAndLastName = new ArrayList<>(patientsMap.values());
						Collections.sort(sortedByDobAndLastName, new Comparator<Patient>() {
							@Override
							public int compare(Patient p1, Patient p2) {
								int dobComparison = p1.getDob().compareTo(p2.getDob());
								if (dobComparison != 0) {
									return dobComparison;
								} else {
									return p1.getLastName().compareTo(p2.getLastName());
								}
							}
						});
						for (Patient patient1 : sortedByDobAndLastName) {
							System.out.println(patient1);
						}
						break;

					case 10:
						System.out.println("Sorting Customers by Email:");
						List<Patient> sortedByEmail = new ArrayList<>(patientsMap.values());
						Collections.sort(sortedByEmail, new Comparator<Patient>() {
							@Override
							public int compare(Patient p1, Patient p2) {
								return p1.getEmail().compareTo(p2.getEmail());
							}
						});
						for (Patient patient1 : sortedByEmail) {
							System.out.println(patient1);
						}
						break;

					case 11:
						System.out.println("Customers sorted by ID:");
						List<Patient> sortedById = new ArrayList<>(patientsMap.values());
						Collections.sort(sortedById, new Comparator<Patient>() {
							@Override
							public int compare(Patient p1, Patient p2) {
								return p1.getPatientId().compareTo(p2.getPatientId());
							}
						});
						for (Patient patient1 : sortedById) {
							System.out.println(patient1);
						}
						break;

					case 12:
						System.out.println("Customers sorted by Plan:");
						List<Patient> sortedByPlan = new ArrayList<>(patientsMap.values());
						Collections.sort(sortedByPlan, new Comparator<Patient>() {
							@Override
							public int compare(Patient p1, Patient p2) {
								return p1.getPlan().compareTo(p2.getPlan());
							}
						});
						for (Patient patient1 : sortedByPlan) {
							System.out.println(patient1);
						}
						break;
					case 13:
						System.out.println("Enter file name : ");
						String fileName = sc.next();
						Map<String, Patient> deserializedMap = null;
						try {
							deserializedMap = readDetails(fileName);
							if (deserializedMap != null) {
								System.out.println("Patients data from file:");
								for (Patient patient1 : deserializedMap.values()) {
									System.out.println(patient1);
								}
							} else {
								System.out.println("No data found in the file.");
							}
						} catch (IOException | ClassNotFoundException e) {
							System.out.println("Error occurred while deserializing data: " + e.getMessage());
						}
						break;

					case 14:
						System.out.println("Enter file name : ");
						writeDetails(patientsMap, sc.next());
						break;
					default:
						System.out.println("Invalid option.");
						break;

					case 15:
						System.out.println("Enter file name to store patient details:");
						String fileNameToStore = sc.next();
						try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameToStore))) {
							for (Patient patient1 : patientsMap.values()) {
								writer.write(patient1.toString());
								writer.newLine();
							}
							System.out.println("Patient details stored in file successfully.");
						} catch (IOException e) {
							System.out.println("An error occurred while writing to file: " + e.getMessage());
						}
						break;
					case 16:
					    System.out.println("Enter file name to display patient details:");
					    String fileNameToDisplay = sc.next();
					    try (BufferedReader reader = new BufferedReader(new FileReader(fileNameToDisplay))) {
					        String line;
					        System.out.println("Patients data from file:");
					        while ((line = reader.readLine()) != null) {
					            System.out.println(line);
					        }
					    } catch (IOException e) {
					        System.out.println("An error occurred while reading from file: " + e.getMessage());
					    }
					    break;

					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					sc.nextLine();

				}
			}
		}
	}

}
/*
 * case 5: System.out.println("Enter email to unsubscribe:"); String
 * unsubscribeEmail = sc.next(); if (patientsMap.containsKey(unsubscribeEmail))
 * { patientsMap.remove(unsubscribeEmail);
 * System.out.println("Patient unsubscribed successfully."); } else {
 * System.out.println("Patient not found with the given email."); } break;
 * 
 * case 6: System.out.println("Sorting Customers by Date of Birth:");
 * patientsMap.values().stream() .sorted(Comparator.comparing(Patient::getDob))
 * .forEach(System.out::println); break;
 * 
 * case 7:
 * System.out.println("Sorting Customers by Date of Birth and Service Plan:");
 * patientsMap.values().stream() .sorted(Comparator.comparing(Patient::getDob)
 * .thenComparing(p -> p.getPlan().toString())) .forEach(System.out::println);
 * break;
 * 
 * case 8: System.out.println("Enter email to remove customer:"); String
 * removeEmail = sc.next(); patientsMap.remove(removeEmail);
 * System.out.println("Customer removed successfully."); break;
 * 
 * case 9: System.out.println("Sort by Date of Birth and Last Name:");
 * patientsMap.values().stream() .sorted(Comparator.comparing(Patient::getDob)
 * .thenComparing(Patient::getLastName)) .forEach(System.out::println); break;
 * 
 * case 10: System.out.println("Sorting Customers by Email:");
 * patientsMap.values().stream()
 * .sorted(Comparator.comparing(Patient::getEmail))
 * .forEach(System.out::println); break;
 * 
 * case 11: System.out.println("Customers sorted by ID:");
 * patientsMap.values().stream()
 * .sorted(Comparator.comparing(Patient::getPatientId))
 * .forEach(System.out::println); break;
 * 
 * case 12: System.out.println("Customers sorted by Plan:");
 * patientsMap.values().stream() .sorted(Comparator.comparing(p ->
 * p.getPlan().toString())) .forEach(System.out::println); break;
 * 
 */
