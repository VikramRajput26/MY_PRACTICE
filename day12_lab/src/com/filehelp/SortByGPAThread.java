package com.filehelp;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.app.core.Student;
import com.app.core.Subject;

public class SortByGPAThread extends Thread {
	private Map<String, Student> studentsMap;
	private Subject subject;

	public SortByGPAThread(Map<String, Student> studentsMap, Subject subject) {
		this.studentsMap = studentsMap;
		this.subject = subject;
	}

	@Override
	public void run() {
		Map<String, Student> filteredMap = new LinkedHashMap<>();
		studentsMap.entrySet().stream().filter(entry -> entry.getValue().getSubject() == subject)
				.sorted(Map.Entry.comparingByValue(Comparator.comparing(Student::getGpa).reversed()))
				.forEachOrdered(entry -> filteredMap.put(entry.getKey(), entry.getValue()));
		FileHelper.saveToFile(filteredMap, "sortedByGPA_" + subject + ".txt");
	}
}
