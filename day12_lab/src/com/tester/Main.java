package com.tester;

import java.util.Map;

import com.app.core.Student;
import com.app.core.Subject;
import com.filehelp.SortByDOBThread;
import com.filehelp.SortByGPAThread;

import static utils.StudentCollectionUtils.*;

public class Main {
	public static void main(String[] args) {
		Map<String, Student> studentsMap = populateMap(populateList());

		Thread sortByDOBThread = new SortByDOBThread(studentsMap);
		sortByDOBThread.start();

		Thread sortByGPAThread1 = new SortByGPAThread(studentsMap, Subject.JAVA);
		Thread sortByGPAThread2 = new SortByGPAThread(studentsMap, Subject.REACT);
		sortByGPAThread1.start();
		sortByGPAThread2.start();

		// Ensure no orphans by waiting for threads to finish
		try {
			sortByDOBThread.join();
			sortByGPAThread1.join();
			sortByGPAThread2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
