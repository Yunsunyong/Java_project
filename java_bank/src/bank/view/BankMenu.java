package bank.view;

import java.io.*;
import java.util.*;

import bank.controller.BankController;
import bank.model.vo.Bank;

public class BankMenu {
	
	public void bankMenu() {	
		int mnum;
		Scanner sc = new Scanner(System.in);
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
			case 1 : bm.bankInsert(); break;
			case 2 : bm.bankAllPrint(); break;
			case 3 : break;
			case 4 : break;
			case 5 : break;
			case 6 : break;
			case 7 : System.out.println("통장관리 프로그램 종료합니다."); break;
			default : System.out.println("번호를 잘못 입력하셨습니다.");
						System.out.println("다시 입력해주세요."); break;
			}
		} while(mnum != 7);
	}
	
	//1. 통장개설 메소드
	public void bankInsert() {
		Bank bk = new Bank();
		Scanner sc = new Scanner(System.in);
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
		Set sProp = prop.keySet();
		Iterator iter = sProp.iterator();
		while(iter.hasNext()) {
			String key = (String)iter.next();
			String value = prop.getProperty(key);
			System.out.println(key + "=" + value);
		}
	}
	
	//3. 통장 검색 조회 메소드
	public void bankSearh() {
		BankController bctro = new BankController();
		
	}
	
	//4. 입금 메소드
	public void bankDeposit() {
		
	}
	
	//5. 출금 메소드
	public void bankWithdrawal() {
		
	}
	
	//6. 계좌이체 메소드
	public void bankAccTransfer() {
		
	}
}
