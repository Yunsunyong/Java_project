package bank.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Scanner;

import bank.view.BankMenu;

public class BankController {
	private int bNo;
	private Properties prop = new Properties();
	private Properties prop2 = new Properties();
	private BankMenu bm = new BankMenu();
	
	public BankController() {
		Scanner sc = new Scanner(System.in);
		System.out.print("불러올 파일명 :");
		String fileName = sc.next();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));		
		} catch (FileNotFoundException e) {
			System.out.println("통장을 개설해주시기 바랍니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Properties bankInsert(String value) {
		Scanner sc = new Scanner(System.in);
		bNo = prop.size();
		prop.setProperty(String.valueOf(bNo), value);
		prop2.setProperty(String.valueOf(bNo), value);

		System.out.println("        ==== KH은행 ====");
		try {
			System.out.print("저장할 고객정보 파일명 :");
			String FileName = sc.next();
			prop.storeToXML(new FileOutputStream(FileName), "고객정보");
			System.out.print("저장할 고객통장 파일명 :");
			String FileName2 = sc.next();
			prop2.storeToXML(new FileOutputStream(FileName2), "고객통장정보");
		} catch (FileNotFoundException e) {
			System.out.println("통장을 개설해주시기 바랍니다.");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	public Properties bankAllPrint() {
		return prop;
	}
	
	public Properties bankSearch() {
		return prop;
	}
}
