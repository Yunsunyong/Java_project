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
		//Scanner sc = new Scanner(System.in);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy'년'mm'월'dd'일'");
		
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
		String openDate = sdf.format(new Date());
		
		bk = new Bank(userName, gender, age, bNumber, price, openDate);
		
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
		Properties prop = bctro.bankDeposit();
		SimpleDateFormat sdf = new SimpleDateFormat("yymmdd");
		Set<String> sProp = prop.stringPropertyNames();
		Iterator<String> iter = sProp.iterator();
		
		String valueT = null;
		while(iter.hasNext()) {
			String key = iter.next();
			String value = prop.getProperty(key);
			System.out.println(key+"="+value);
			
			System.out.print("수정할 금액 :");
			String setPrice = sc.next(); 

			if(prop.getProperty(key).contains(setPrice)) {
				System.out.print("내용:");
				sc.nextLine();
				String content = sc.nextLine();
				System.out.print("찾으신 금액:");
				String foundPrice = sc.next();
				System.out.print("입금할 금액:");
				int price2 = sc.nextInt();
				String priceS = Integer.toString(price2);
				
				int price = Integer.parseInt(setPrice);
				int sum = price + price2;
				String sums = Integer.toString(sum);
				
				valueT = "거래일:"+sdf.format(new Date()) + " 내용:" +content+
						" 찾으신 금액:" +foundPrice+ " 입금한 금액:" + priceS +
						" 잔액:" + sums;
				break;
			}
		}
		bctro.bankInsert2(valueT);
	}
	
	//5. 출금 메소드
	public void bankWithdrawal() {
		
	}
	
	//6. 계좌이체 메소드
	public void bankAccTransfer() {
		
	}
}
