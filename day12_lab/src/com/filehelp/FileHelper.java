package com.filehelp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import com.app.core.Student;

public class FileHelper {
	 public static void saveToFile(Map<String, Student> studentsMap, String filename) {
	        try (FileWriter writer = new FileWriter(filename)) {
	            studentsMap.values().forEach(student -> {
	                try {
	                    writer.write(student.toString() + "\n");
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            });
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

