package bank.model.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Bank {
	
	private int bNo=0;  					//통장고유번호
	private String userName;   	//이름
	private char gender;  			//성별
	private int age;       				//나이
	private String bNumber;		//계좌번호
	private int price;					//금액
	private Date openDate = Calendar.getInstance().getTime();      	//통장개설날짜

	public Bank() {}

	public Bank(String userName, char gender, int age, String bNumber, int price, Date openDate) {
		this.userName = userName;
		this.gender = gender;
		this.age = age;
		this.bNumber = bNumber;
		this.price = price;
		this.openDate = openDate;
	}

	public void setbNo(int bNo) {
		this.bNo = bNo;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setbNumber(String bNumber) {
		this.bNumber = bNumber;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public int getbNo() {
		return bNo;
	}

	public String getUserName() {
		return userName;
	}

	public char getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public String getbNumber() {
		return bNumber;
	}

	public int getPrice() {
		return price;
	}

	public String getOpenDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		return sdf.format(openDate);
	}
	
	@Override
	public String toString() {
		return userName+ "|" + gender + "|" + age +
				 "|" +bNumber + "|" +price + "|"+getOpenDate();
	}
	





}
