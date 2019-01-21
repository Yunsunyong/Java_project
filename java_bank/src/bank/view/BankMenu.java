package bank.view;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import bank.controller.BankController;
import bank.model.vo.Bank;

public class BankMenu {
	private Scanner sc = new Scanner(System.in);
	
	public void bankMenu() {	
		int mnum;
		BankMenu bm = new BankMenu();
		do {
			System.out.println("======= 통장개설 프로그램 ======="); 
			System.out.println("                1. 통장 개설");
			System.out.println("                2. 통장 전체조회 ");
			System.out.println("                3. 통장 검색조회");
			System.out.println("                4. 입금");
			System.out.println("                5. 출금");
			System.out.println("                6. 계좌이체");
			System.out.println("                7. 프로그램종료");
			System.out.println("===========================");
			System.out.print("메뉴 선택 : ");
			mnum = sc.nextInt();
			
			switch(mnum) {
			case 1 : bm.bankInsert(); break;           //통장 개설
			case 2 : bm.bankAllPrint(); break;         //통장 전체조회
			case 3 : bm.bankSearh(); break;           //통장 검색조회
			case 4 : bm.bankDeposit(); break;        //입금
			case 5 : bm.bankWithdrawal(); break;   //출금
			case 6 : bm.bankAccTransfer(); break;   //계좌이체
			case 7 : System.out.println("통장관리 프로그램 종료합니다."); break;
			case 8 : bm.bankRemove(); break;
			default : System.out.println("번호를 잘못 입력하셨습니다.");
						System.out.println("다시 입력해주세요."); break;
			}
		} while(mnum != 7);
	}
	
	//1. 통장개설 메소드
	public void bankInsert() {
		Bank bk = new Bank();
		Properties prop = new Properties();
		BankController bctro = new BankController();
		
		
		System.out.print("이름 입력 :");
		String userName = sc.next();
		System.out.print("성별 입력(남,여) :");
		char gender = sc.next().charAt(0);
		System.out.print("나이 입력 :");
		int age = sc.nextInt();
		System.out.print("계좌번호 입력 :");
		String bNumber = sc.next();
		System.out.print("기본 금액 입력 :");
		int price = sc.nextInt();
				
		bk = new Bank(userName, gender, age, bNumber, price, new Date());
		
		prop = bctro.bankInsert(bk.toString());
	}
	
	//2. 통장 전체 조회 메소드
	public void bankAllPrint() {
		BankController bctro = new BankController();
		Properties prop = bctro.bankAllPrint();
		Set<String> sProp = prop.stringPropertyNames();
		Iterator<String> iter = sProp.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			String value = prop.getProperty(key);
			System.out.println(key + "=" + value);
		}
	}
	
	//3. 통장 검색 조회 메소드
	public void bankSearh() {
		BankController bctro = new BankController();
		Properties prop = bctro.bankSearch();
		Set<String> sProp = prop.stringPropertyNames();
		Iterator<String> iter = sProp.iterator();
		
		System.out.print("검색할 이름명 :");
		String userName = sc.next();
		
		for(int i = 0; iter.hasNext(); i++) {
			String key = iter.next();
			String value = prop.getProperty(key);
			if(prop.getProperty(key).contains(userName)) {
				System.out.println(key + "=" + value);
			}
		}
	}
	
	//4. 입금 메소드
	public void bankDeposit() {
		BankController bctro = new BankController();
		Properties prop = bctro.bankSearch();
		Bank bk = new Bank();
		Set<String> sProp = prop.stringPropertyNames();
		Iterator<String> iter = sProp.iterator();
		
		if (prop.size() == 1) {
			while (iter.hasNext()) {
				String key = iter.next();
				String value = prop.getProperty(key);
				System.out.println(key + "=" + value);

				String[] sar = value.split("\\|");
				int price = Integer.parseInt(sar[4]);
				System.out.print("내용:");
				String content = sc.next();
				System.out.print("입금할 금액:");
				int price2 = sc.nextInt();
				int sum = price + price2;
				String userInfo = content + "|입금["+price2+"]|잔액["+ sum + "]|" + bk.getOpenDate();

				bctro.bankDeposit(userInfo);
				break;
			}										
		}else {
			while (iter.hasNext()) {
				String key = iter.next();
				String value = prop.getProperty(key);
				System.out.println(key + "=" + value);

				String[] sar = value.split("\\|");
				String[] sar2 = sar[2].split("\\[|\\]");
				int price = Integer.parseInt(sar2[1]);
				
				System.out.print("내용:");
				String content = sc.next();
				System.out.print("입금할 금액:");
				int price2 = sc.nextInt();
				int sum = price + price2;
				String userInfo = content + "|입금["+price2+"]|잔액["+ sum + "]|" + bk.getOpenDate();

				bctro.bankDeposit(userInfo);
				break;
			}
		}
	}
	
	//5. 출금 메소드
	public void bankWithdrawal() {
		BankController bctro = new BankController();
		Properties prop = bctro.bankSearch();
		Bank bk = new Bank();
		Set<String> sProp = prop.stringPropertyNames();
		Iterator<String> iter = sProp.iterator();
		
		if (prop.size() == 1) {
			while (iter.hasNext()) {
				String key = iter.next();
				String value = prop.getProperty(key);
				System.out.println(key + "=" + value);

				String[] sar = value.split("\\|");
				int price = Integer.parseInt(sar[4]);
				System.out.print("내용:");
				String content = sc.next();
				System.out.print("출금할 금액:");
				int price2 = sc.nextInt();
				int sum = price - price2;
				String userInfo = content + "|출금["+price2+"]|잔액["+ sum + "]|" + bk.getOpenDate();

				bctro.bankWithdrawal(userInfo);
				break;
			}										
		}else {
			while (iter.hasNext()) {
				String key = iter.next();
				String value = prop.getProperty(key);
				System.out.println(key + "=" + value);

				String[] sar = value.split("\\|");
				String[] sar2 = sar[2].split("\\[|\\]");
				int price = Integer.parseInt(sar2[1]);
				
				System.out.print("내용:");
				String content = sc.next();
				System.out.print("출금할 금액:");
				int price2 = sc.nextInt();
				int sum = price - price2;
				String userInfo = content + "|출금["+price2+"]|잔액["+ sum + "]|" + bk.getOpenDate();

				bctro.bankWithdrawal(userInfo);
				break;
			}
		}
	}
	
	public void bankRemove() {
		BankController bctro = new BankController();
		Properties prop = bctro.bankSearch();
		
		Set<String> sProp = prop.stringPropertyNames();
		Iterator<String> iter = sProp.iterator();
		while(iter.hasNext()) {
			String key = iter.next();
			System.out.print("삭제할 고유번호 :");
			String num = sc.next();
			if(key.equals(num)) {
				prop.remove(key);
				break;
			}
		}
	}
	
	//6. 계좌이체 메소드
	public void bankAccTransfer() {
		
	}
}
