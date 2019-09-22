package kr.or.bit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Admin {
	public static final String ID = "superid"; // ������ ���̵�
	public static final String PW = "iamsuper"; // ������ ��й�ȣ
	File f; // ������ �ּ�
	HashMap<Integer, String> bookList; // �ϸ���Ʈ
	ArrayList<User> userList; // ��������Ʈ


	public Admin()  {
		try {
			f = Library.getFileAddress(); 
			userList = Library.getUserList(); 
			bookList = Library.getBookList();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ������ �α��� ������ �� �����ϴ� �޼ҵ�
	public void start() throws ClassNotFoundException, IOException {
		while (true) {
		    System.out.println("�� �����ڱ��  ����������������������������������������������������");
			System.out.println("1.������� ���");
			System.out.println("2.ȸ������ ���");
			System.out.println("3.���� ���");
			System.out.println("4.���� ���� ");
			System.out.println("5.���� ����");
			System.out.println("6.ȸ�� ����");
			System.out.println("7.���� ���� �� �α׾ƿ�");
		    System.out.println("������������������������������������������������������������������������");

			Scanner sc = new Scanner(System.in);
			int i = sc.nextInt();
			if (i == 1) {
				getBookList(f, bookList); // ��������Ʈ �ҷ�����
			} else if (i == 2) {
				getUserList(f, userList); // ��������Ʈ �ҷ�����
			} else if (i == 3) {
				addBook(f, bookList); // �����߰�
			} else if (i == 4) {
				modifyBook(f, bookList); // ��������
			} else if (i == 5) {
				delBook(f, bookList); // ��������
			} else if (i == 6) {
				deluser(f, userList); // ��������
			} else if (i == 7) {
				Library.saveFile(); // ���� �� �α׾ƿ�
				System.out.println("�α׾ƿ� ����");
				if (Library.getInt("�α׿��� �Ͻ÷��� 0�� �Է��ϼ��� �ٸ� �۾��� �Ͻ÷��� �ٸ� Ű�� �Է��ϼ���", new Scanner(System.in)) == 0) {
					break;
				}
			}
		}
	}

	// ���� ����Ʈ ���
	private static void getBookList(File f, HashMap<Integer, String> bookList) {
		Set<Map.Entry<Integer, String>> bookListSet = bookList.entrySet();
		System.out.println("�������");
	    System.out.println("������������������������������������������������������������������������");
		System.out.println("isbn          ������" );
		for (Entry<Integer, String> entry : bookListSet) { // isbn , �����̸� ���

			System.out.println(" "+ entry.getKey() + "         " + entry.getValue());
		}
		System.out.println("�� ���� ���� [" + bookListSet.size() + "]�� �Դϴ�.");
		System.out.println();
	}

	// ���� ����Ʈ ���
	private static void getUserList(File f, ArrayList<User> userList) throws ClassNotFoundException, IOException {
		System.out.println("ȸ�����");
	    System.out.println("������������������������������������������������������������������������");
	    System.out.println("����              �̸�                     �ڵ�����ȣ");
		for (int i = 0; i < userList.size(); i++) { // �����̸� ��ȣ���
			System.out.println(i + "       " + userList.get(i).getName() + "               " + userList.get(i).getCellNum());
		}
		System.out.println("������������������������������������������������������������������������");
		System.out.println("�� ���� ���� :" + userList.size() + "�� �Դϴ�."); // ��������Ʈ��
		System.out.println("");
	}

	// å �߰�
	private static void addBook(File f, HashMap<Integer, String> bookList) {
		while (true) {
			getBookList(f, bookList); // ����
			Integer num = Library.getInt("������ �߰��մϴ� å ��ȣ�� �Է����ּ���", new Scanner(System.in));
			String name = Library.getString("å �̸��� �Է����ּ���", new Scanner(System.in));

			if (bookList.containsKey(num)) {
				System.out.println("�̹� �����ϴ� ��ȣ�Դϴ�. �ٽ� �Է����ּ���.");
			} else {
				System.out.printf("isbn %d�� %s(��)�� ��ϵǾ����ϴ�.", num, name);
				bookList.put(num, name);
				break;
			}
		}
	}

	// å ����
	private static void modifyBook(File f, HashMap<Integer, String> bookList) { // ��������
		getBookList(f, bookList);

		while (true) {
			String name ="";
			Integer num = Library.getInt("������ ���� ��ȣ�� �Է����ּ���.", new Scanner(System.in));

			if (bookList.get(num) != null) { // �ϸ���Ʈ�� isbn���� null�� �ƴ϶��
				name = Library.getString("�����Ͻ� �������� �Է����ּ���.", new Scanner(System.in));

			}else {
					System.out.println("�������� �ʴ� ���� ��ȣ�Դϴ�. �ٽ� �Է����ּ���.");
				}
				
				if (bookList.containsKey(num)) { // �ش�Ǵ� isbn�� �ִٸ�
					bookList.put(num, name); // put
					System.out.printf("������ȣ %d���� ������ %s�� ��ϵǾ����ϴ�.", num, name);
					break;

				} 
			}
	}

	// å ����

	private static void delBook(File f, HashMap<Integer, String> bookList) {
		getBookList(f, bookList);

		while (true) {
			int i = Library.getInt("������ �����մϴ� ������ ���� ��ȣ�� �Է����ּ���", new Scanner(System.in));
			if (bookList.get(i) != null) {
				System.out.printf("isbn %d, %s (��)�� �����Ǿ����ϴ�.\n", i, bookList.get(i)); // �߰�
				System.out.println(); // �߰�
				bookList.remove(i); // ����Ʈ���� ���� �� ���� �ٷο�Ͽ� ��´�.
				break;
			} else {
				System.out.println("�������� �ʴ� ���� ��ȣ�Դϴ�. �ٽ� �Է����ּ���.");
				System.out.println("");
			}
		}

	}

	// ���� ����
	private static void deluser(File f, ArrayList<User> userList) throws ClassNotFoundException, IOException {
		getUserList(f, userList);
		int i = Library.getInt("������ �����մϴ� ������ ������ȣ�� �Է����ּ���", new Scanner(System.in));

		try {
			userList.remove(i);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("�߸��� ��ȣ�� �Է��ϼ̽��ϴ�.");
		}

	}

}

