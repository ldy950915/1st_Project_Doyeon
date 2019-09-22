package kr.or.bit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Library  {
	
	Admin admin;
	private static File fileAddress = new File("C:\\test\\BitLibrary");; // ������ġ, ���ٸ� ����
	private static ArrayList<User> userList = null; // ��������Ʈ
	private static HashMap<Integer, String> bookList = null; // isbn , book
	private String today; // ����ð�
	

	public Library() throws ClassNotFoundException, IOException {
		admin = new Admin();
	}

	
	// ������ ����
	public void begin() throws ClassNotFoundException, IOException {

		while (true) {
			System.out.println("���������������� �����絵������ ���Ű��� ȯ���մϴ� ��������������");
			System.out.println("   ����    ����    ����    ����    ����    ����      ");
			System.out.println("   ����    ����    ����    ����    ����    ����     ");
			System.out.println("������������������������������������������������������������������������������");
			System.out.println("       ���ϴ� �׸��� ������ �ּ���                      ");
			System.out.println("");
			System.out.println("   0.���α׷� ����     1.�α���     2.ȸ�����  " );
			int operation = getInt("", new Scanner(System.in));
			if (operation == 1) {
				System.out.println("");
				System.out.println("���ϴ� �׸��� �����ϼ���");
				System.out.println("��������������������������������������������������������������������������");
				int useroradmin = getInt("  0.����     1.ȸ���α���     2.�����ڷα���    ", new Scanner(System.in));
				if (useroradmin == 1) {
					this.userLogin(); // �����α��� �޼ҵ�
					break;
				} else if (useroradmin == 2) {
					this.adminlogin(); // ���ηα��� �޼ҵ�
				} else if (useroradmin == 0) {
					saveFile(); // ��������
					break; // ����
				} else {
					System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�.");
				}
			} else if (operation == 2) {
				this.register(); // ȸ����� �޼ҵ�
			} else if (operation == 0) {
				saveFile(); // ��������
				break; // ����
			} else {
				System.out.println("�߸��� ���� �Է��ϼ̽��ϴ�.");
			}
		}
	    System.out.println("������������������������������������������������������������������������");
	}

	// �α��� �޼ҵ�
	public void userLogin() throws ClassNotFoundException, IOException {
		// �α��� �ܼ�â
		System.out.println("");
		System.out.println("�� ȸ���α���  ��������������������������������������������������");
		while (true) {
			System.out.println("�ڵ�����ȣ ����  ex)010-0000-0000");
			System.out.println("��������������������������������������������������������������������");
			String cellNum = getString("�ڵ�����ȣ�� �Է��ϼ���.", new Scanner(System.in));
			String name = getString("�̸��� �Է��ϼ���.", new Scanner(System.in));
			// �α��� ������ Ȯ��
			boolean access = false; // ��뿩��
			for (User user : getUserList()) {
				if (cellNum.equals(user.getCellNum())) {
					if (name.equals(user.getName())) {
						access = true; // ���̵� ��й�ȣ ������ ��밪 true
						setDate(); // �ð� set
						System.out.println("������������������������������������������������������������������");
						System.out.println("   " + name + "�� ȯ���մϴ�\t");
						System.out.println("   " + "���ӳ�¥ :" + today);
						System.out.println("������������������������������������������������������������������");
						System.out.println("");
						userloginyes(user); // �α���
						break;
					}
				}
			}
			if (!access) { // ���̵� ������ false�϶�
				// �α��� ������ ����
				int loginerrorreturn = this.loginError();
				if (loginerrorreturn == 1) {
					break;
				}
			} else {
				break;
			}
		}
	}
	
	public void adminlogin() throws ClassNotFoundException, IOException {
		// �α��� �ܼ�â
		System.out.println("");
		System.out.println("�� �����ڷα���  ����������������������������������������������������");
		while (true) {
			String id = getString("���̵� �Է��ϼ���", new Scanner(System.in));
			String pw = getString("��й�ȣ�� �Է��ϼ���", new Scanner(System.in));
			// �α��� ������ Ȯ��
			boolean access = false;
			if (id.equals(admin.ID)) {
				if (pw.equals(admin.PW)) {
					access = true; // ���̵� ��й�ȣ ������ ��밪 true
					setDate();
					System.out.println("������������������������������������������������������������������");
					System.out.println("    �����ڸ��� �α����ϼ̽��ϴ�.\t");
					System.out.print("    " + "���ӳ�¥ :" + today + "\n");
					System.out.println("������������������������������������������������������������������");
					adminloginyes(admin); // �α���
					break;
				}
			} else {
				System.out.println("������ ���̵�� ��й�ȣ �Է��� Ȯ�����ּ���. ");
				break;
			}

			if (!access) { // ���̵� ������ false�϶�
				// �α��� ������ ����
				int loginerrorreturn = this.loginError();
				if (loginerrorreturn == 1) {
					break;
				}
			} else {
				break;
			}
		}
	}

	
	// ȸ�����
	public void register() throws ClassNotFoundException, IOException {
		System.out.println("�� ȸ�����  ����������������������������������������������������");
		String registercellNum;
		while (true) {
			System.out.println("�ڵ�����ȣ ����  ex)010-0000-0000");
			System.out.println("�� �� �ڸ��� 010/011/016/017/018/019�� ����");
			registercellNum = getString("�ڵ�����ȣ�� �Է��� �ּ���", new Scanner(System.in));
			String cellformat = "^01(?:0|1[6-9])[-](\\d{3}|\\d{4})[-](\\d{4})$"; // ����ǥ����
			boolean cellNumExist = false; // ȸ�� ���� ����
			for (User user : getUserList()) {
				if (user.getCellNum().equals(registercellNum)) {
					cellNumExist = true; // �ڵ�����ȣ�� �ִٸ�
					break;
				}
			}
			if (!registercellNum.matches(cellformat)) {
				System.out.println("�߸��Է��ϼ̽��ϴ�.");
				System.out.println("�ڵ�����ȣ ����  ex)010-0000-0000");
				System.out.println("�� �� �ڸ��� 010/011/016/017/018/019�� ����");
				return;
			} // ��ȣ ������ �ٸ��ٸ� ������ ������

			if (cellNumExist) { // ������ �����Ѵ�
				System.out.println("������ �ڵ�����ȣ�� �����մϴ�");
			} else {
				break; // �������� �ʴ´ٸ� while�� Ż��
			}
		}

		// ȸ���̸� �Է�
		String registerName = getString("�̸��� �Է��� �ּ���", new Scanner(System.in));
		String nameformat = "^[a-zA-Z��-�R]*$"; // �̸� ����ǥ����
		if (!registerName.matches(nameformat)) {
			System.out.println("�߸��� ������ �Է��ϼ̽��ϴ�.");
			System.out.println("���ĺ� Ȥ�� �ѱ۷� �Է����ּ���.");
			return;
		} else {
			getUserList().add(new User(registercellNum, registerName)); // �´ٸ� ȸ������Ʈ�� ���
			saveFile();
			System.out.println(registerName + "�� ȸ�������� �Ϸ�Ǿ����ϴ�.");
			System.out.println("");
		}

	}

	public void userloginyes(User user) throws IOException, ClassNotFoundException {

		while (true) {
			System.out.println("�� ȸ�����  ����������������������������������������������������");
			System.out.println("1.�������                                        ");
			System.out.println("2.������                                        ");
			System.out.println("3.����                                                       ");
			System.out.println("4.�ݳ�                                                       ");
			System.out.println("5.ȸ��Ż��                                                       ");
			System.out.println("0.�α׾ƿ�                                                       ");
			int operationnum = getInt("", new Scanner(System.in));
			if (operationnum == 0) { // �α׾ƿ�
				System.out.println("���������� �α׾ƿ� �Ǿ����ϴ�.");
				System.out.println("�̿����ּż� �����մϴ�.");
				break;
			} else if (operationnum == 5) { // ȸ��Ż��
				accountCancellation(user);
				break;
			}
			this.operationnum(user, operationnum); // 1,2,3,4�޼ҵ� �Լ� ȣ��
		}
	}

	public void adminloginyes(Admin admin) throws IOException, ClassNotFoundException {
		System.out.println("");

		admin.start();  // �����̶�� ����Ŭ������ �̵�
	}

	
	// �α��� ���� �޼ҵ�
	public int loginError() throws ClassNotFoundException, IOException {
		System.out.println("");
		System.out.println("�߸��Է��ϼ̽��ϴ�.");
		int x = 0;
		System.out.println("������������������������������������������������������������������������");
		System.out.println("1. ���Է�                                                  ");
		System.out.println("2. ȸ������                                                ");
		System.out.println("3. ���α׷� ����");
		int operationerror = getInt("", new Scanner(System.in));
		switch (operationerror) {
		case 1:
			break; // ���Է�
		case 2:
			register(); // ȸ�����
			break;
		case 3: 
			x = 1; // ����
		default:
			System.out.println("�߸��� ��ȣ�� �Է��ϼ̽��ϴ�.");
			break;
		}
		return x;
	}

	
	// �ڼ��� ���۹��
	public void operationnum(User user, int operationnum) throws IOException, ClassNotFoundException {
		// �ڼ��� ���� switch
		switch (operationnum) {
		case 1: // ������� ����
			showBookList();
			break;
		case 2:
			// ������ ����
			System.out.println("");
			showBorrowBookList(user);
			break;
		case 3:
			// ����
			showBookList();
			borrowBook(user);
			break;
		case 4:
			// �ݳ�
			giveBack(user);
			break;
		default:
			System.out.println("��Ȯ�� ���ڸ� �Է����ּ���");
			break;
		}
	}

	
	private void showBookList() throws IOException, ClassNotFoundException {
		Set<Map.Entry<Integer, String>> bookListSet = getBookList().entrySet(); // �ϸ���Ʈ set���� �޾ƿ�
		if (bookListSet.size() != 0) { // ����� 0�� �ƴ϶��
			System.out.println("�������");

		    System.out.println("������������������������������������������������������������������������");
	  	    System.out.println("isbn     ������");

			for (Entry<Integer, String> entry : bookListSet) { // �� å ����Ʈ �����ֱ�
	              System.out.println(" " + entry.getKey() + "      " + entry.getValue());
			}
		
		    System.out.println("������������������������������������������������������������������������");
			System.out.println("���� �츮 �������� �� ���� ���� [" + bookListSet.size() + "]�� �Դϴ�."); // �� å ����
			System.out.println("");

		} else {
			System.out.println("���� �������� �뿩������ ������ �����ϴ�.");
		}
	}

	
	private void showBorrowBookList(User user) { // ������ ���� å ����Ʈ
		Set<Map.Entry<Integer, String>> borrowBookSet = user.getBorrowBook().entrySet();
		if (borrowBookSet.size() != 0) {
     	   System.out.println("�����Ͻ� ���� ���  ");
		    System.out.println("������������������������������������������������������������������������");
		    System.out.println("isbn     ������");
			for (Entry<Integer, String> entry : borrowBookSet) {
              System.out.println(" " + entry.getKey() + "      " + entry.getValue());
			}
			
          System.out.println("");
		} else {
          System.out.println(user.getName() + "���� ���� �뿩 ����� �����ϴ�.");
          System.out.println("");
		}
	}


	public void borrowBook(User user) throws IOException, ClassNotFoundException { // ����

		int booki = getInt("���ϴ� å ��ȣ�� �Է����ּ���", new Scanner(System.in));
		while (true) {
			HashMap<Integer, String> bookList = getBookList();
			if (bookList.get(booki) != null) {
				System.out.println("");
				System.out.printf("%s�� ���� [%s]�� ���������� ���� �Ǿ����ϴ�.\n", user.getName(), bookList.get(booki));
				System.out.println("");
				user.getBorrowBook().put(booki, bookList.get(booki));
				bookList.remove(booki);
				saveFile();			
				break;

			} else {
				System.out.println("�˸´� ���� ��ȣ�� �Է��ϼ���");
				break;
			}
		}
	}

	// �ݳ�
	public void giveBack(User user) throws IOException, ClassNotFoundException {
		System.out.println("");
		System.out.println(user.getName()+ "����" );
		showBorrowBookList(user);
		int booki = getInt("�ݳ��� å ��ȣ�� �Է��ϼ���", new Scanner(System.in));
		while (true) {
			if (user.getBorrowBook().get(booki) != null) {
				System.out.println("");
				System.out.printf("%s�Բ��� ���� [%s]�� �ݳ��� �Ϸ��ϼ̽��ϴ�.\n", user.getName(), user.getBorrowBook().get(booki));
				getBookList().put(booki, user.getBorrowBook().get(booki));
				user.getBorrowBook().remove(booki);
				saveFile();
				break;

			} else {
				System.out.println("�˸´� ���� ��ȣ�� �Է��ϼ���");
				break;
			}
		}
	}

	
	// ȸ��Ż��
	public void accountCancellation(User user) throws ClassNotFoundException, IOException {
		System.out.println("������������������������������������������������������������������������");
		System.out.println("Ż�� �Ŀ��� �ٽ� ������ �����ؾ� �ϸ�, �������� ������ϴ�. �׷��� Ż���Ͻðڽ��ϱ�?");
		System.out.println("1. Ȯ��");
		System.out.println("0. �ǵ��ư���");
		String operation = getString("���ϴ� ��ȣ�� �����ϼ���", new Scanner(System.in)); //0 �̿��� ��ȣ�� �����ø� �ٽ� �ǵ��� ���ϴ�.
		
		if(!(user.getBorrowBook() == null)) {
			System.out.println("���� �뿩���� ������ �ֽ��ϴ�.");
			System.out.println("�ݳ� �� �������ּ���.");
			System.out.println("");
			userloginyes(user);

			
			if (operation.equals("1")) {
				for (User value : getUserList()) {
					if (user.getName().equals(value.getName())) {
						getUserList().remove(value); // ȸ������
						saveFile();
						System.out.println("�̿����ּż� �����մϴ�.");
						break;
					}
				}
			}else {
				userloginyes(user);
				System.out.println("");
			}
		}

	}

	// �����ּ� ��������
	public static File getFileAddress() {
		if (!fileAddress.isDirectory()) {
			fileAddress.mkdirs();
		}
		return fileAddress;
	}

	

	// ���� ����Ʈ �ҷ�����
	public static ArrayList<User> getUserList() throws IOException, IOException, ClassNotFoundException {
		if (userList == null) {
			try {
				FileInputStream fisUser = new FileInputStream(new File(fileAddress, "User.txt"));
				BufferedInputStream bisUser = new BufferedInputStream(fisUser);
				ObjectInputStream ooUser = new ObjectInputStream(bisUser);
				Object obj = ooUser.readObject();
				userList = (ArrayList<User>) obj;
				fisUser.close();
				bisUser.close();
				ooUser.close();
			} catch (Exception e) {
				userList = new ArrayList<User>();
			}
		}
		return userList;
	}

	// å ����Ʈ �ҷ�����
	public static HashMap<Integer, String> getBookList() throws IOException, IOException, ClassNotFoundException {
		if (bookList == null) { // null�̶�� å ����Ʈ �ҷ��´�.
			try {
				FileInputStream fisBook = new FileInputStream(new File(fileAddress, "Book.txt"));
				BufferedInputStream bisBook = new BufferedInputStream(fisBook);
				ObjectInputStream ooBook = new ObjectInputStream(bisBook);
				Object obj = ooBook.readObject();
				bookList = (HashMap<Integer, String>) obj;
				fisBook.close();
				bisBook.close();
				ooBook.close();

			} catch (Exception e) { // ������ ��ٸ� å�� �ִ´�.
				bookList = new HashMap<Integer, String>();
				bookList.put(1, "�ڹ��� ����");
				bookList.put(2, "���� ���� jsp ���α׷���");
				bookList.put(3, "���ü��� û���̴�");
				bookList.put(4, "����� ��");
				bookList.put(5, "������ �ʴ� ��");
				bookList.put(6, "���ڻ�");
				bookList.put(7, "�濵���� ���ε�");
				bookList.put(8, "���� ž");
				bookList.put(9, "�达ǥ����");
				bookList.put(10, "����Ŭ�� ���� �����ͺ��̽� �Թ�");

			}
		}
		return bookList;
	}

	// IO�޼ҵ� ������
	public static void saveFile() throws IOException, IOException, ClassNotFoundException {
		FileOutputStream fosUser = new FileOutputStream(new File(fileAddress, "User.txt"));
		BufferedOutputStream bosUser = new BufferedOutputStream(fosUser);
		ObjectOutputStream ooUser = new ObjectOutputStream(bosUser);

		FileOutputStream fosBook = new FileOutputStream(new File(fileAddress, "Book.txt"));
		BufferedOutputStream bosBook = new BufferedOutputStream(fosBook);
		ObjectOutputStream ooBook = new ObjectOutputStream(bosBook);

		ooUser.writeObject(getUserList());
		ooBook.writeObject(getBookList());

		fosUser.flush();
		fosBook.flush();

		bosUser.flush();
		bosBook.flush();
		ooUser.flush();
		ooBook.flush();
		fosUser.close();
		fosBook.close();

		bosUser.close();
		bosBook.close();

		ooUser.close();
		ooBook.close();

	}
	
	// ��ĳ�ʷ� ��Ʈ���� �޴� �޼ҵ�
	public static String getString(String i, Scanner sc) {
		System.out.println(i);
		String value = sc.nextLine();
		return value;
	}

	// ���ɳʷ� ��Ʈ�� �޴� �޼ҵ�
	public static int getInt(String i, Scanner sc) {
		System.out.println(i);
	    System.out.println("��������������������������������������������������������������������������");
		System.out.print("������> ");
		while (true) {
			if (sc.hasNextInt()) {
				boolean intFlag = true;
				while (intFlag) {
					int value = sc.nextInt();
					if (value < 0) {
						intFlag = false;
						System.out.println("��Ȯ�� ���� �Է����ּ���");
					} else {
						return value;
					}
				}
			} else {
				System.out.println("��Ȯ�� ���� �Է����ּ���");
				sc = new Scanner(System.in);
			}
		}
	}

	// ���糯¥ ����� �޼ҵ�
	public void setDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM��dd�� HH��mm��ss��");
		java.util.Date date = new java.util.Date();
		today = sdf.format(date);
	}

}