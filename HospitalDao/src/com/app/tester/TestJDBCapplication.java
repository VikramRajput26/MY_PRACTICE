package com.app.tester;

import java.util.Scanner;

import com.app.dao.PatientDaoImpl;

public class TestJDBCapplication {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			PatientDaoImpl patDao=new PatientDaoImpl();
			boolean exit = false;
			while(!exit) {
				System.out.println("options \n 1.Login\n 2.displayAll \n 3.RegisterPatient \n 4.exit \n");
			
			try {
				switch(sc.nextInt()) {
				case 1:
					System.out.println("enter email and password ");
				}
			}
			
			}
			
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
