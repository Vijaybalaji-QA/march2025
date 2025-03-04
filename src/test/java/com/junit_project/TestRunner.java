package com.junit_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({Flipkart.class,Zomato.class})
public class TestRunner {

	public static void main(String[] args) {
		
		File f = new File("C:\\Users\\ACER\\eclipse-workspace\\Junit\\src\\test\\resources\\NewFile.property");
		Properties prop = new Properties();
		try {
		FileInputStream fi = new FileInputStream(f);
			try {
				prop.load(fi);
				String user = prop.getProperty("Username");
				String password = prop.getProperty("Password");
				String testdata = prop.getProperty("Testdata");
				System.out.println("Username : " + user);
				System.out.println("Password : " + password);
				System.out.println("Testdata : " + testdata);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		try {
		FileOutputStream fo = new FileOutputStream(f);
		prop.setProperty("Environment", "Quality Analyst");
		prop.setProperty("Junit", "Library");
		prop.save(fo, "Updated by Srinivasa Velan");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
