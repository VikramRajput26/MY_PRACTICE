package com.filehelp;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.app.core.Student;

public class SortByDOBThread extends Thread {
	private Map<String, Student> studentsMap;

	public SortByDOBThread(Map<String, Student> studentsMap) {
		this.studentsMap = studentsMap;
	}

	@Override
	public void run() {
		Map<String, Student> sortedMap = new LinkedHashMap<>();
		studentsMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.comparing(Student::getDob)))
				.forEachOrdered(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
		FileHelper.saveToFile(sortedMap, "sortedByDOB.txt");
	}
}
