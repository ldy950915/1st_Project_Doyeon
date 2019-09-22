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
	public static final String ID = "superid"; // 관리자 아이디
	public static final String PW = "iamsuper"; // 관리자 비밀번호
	File f; // 파일의 주소
	HashMap<Integer, String> bookList; // 북리스트
	ArrayList<User> userList; // 유저리스트


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
	
	// 관리자 로그인 성공할 시 시작하는 메소드
	public void start() throws ClassNotFoundException, IOException {
		while (true) {
		    System.out.println("● 관리자기능  ──────────────────────────");
			System.out.println("1.도서목록 출력");
			System.out.println("2.회원정보 출력");
			System.out.println("3.도서 등록");
			System.out.println("4.도서 수정 ");
			System.out.println("5.도서 삭제");
			System.out.println("6.회원 삭제");
			System.out.println("7.파일 저장 후 로그아웃");
		    System.out.println("────────────────────────────────────");

			Scanner sc = new Scanner(System.in);
			int i = sc.nextInt();
			if (i == 1) {
				getBookList(f, bookList); // 도서리스트 불러오기
			} else if (i == 2) {
				getUserList(f, userList); // 유저리스트 불러오기
			} else if (i == 3) {
				addBook(f, bookList); // 도서추가
			} else if (i == 4) {
				modifyBook(f, bookList); // 도서수정
			} else if (i == 5) {
				delBook(f, bookList); // 도서삭제
			} else if (i == 6) {
				deluser(f, userList); // 유저삭제
			} else if (i == 7) {
				Library.saveFile(); // 저장 및 로그아웃
				System.out.println("로그아웃 성공");
				if (Library.getInt("로그오프 하시려면 0을 입력하세요 다른 작업을 하시려면 다른 키를 입력하세요", new Scanner(System.in)) == 0) {
					break;
				}
			}
		}
	}

	// 도서 리스트 출력
	private static void getBookList(File f, HashMap<Integer, String> bookList) {
		Set<Map.Entry<Integer, String>> bookListSet = bookList.entrySet();
		System.out.println("도서목록");
	    System.out.println("────────────────────────────────────");
		System.out.println("isbn          도서명" );
		for (Entry<Integer, String> entry : bookListSet) { // isbn , 도서이름 출력

			System.out.println(" "+ entry.getKey() + "         " + entry.getValue());
		}
		System.out.println("총 도서 수는 [" + bookListSet.size() + "]개 입니다.");
		System.out.println();
	}

	// 유저 리스트 출력
	private static void getUserList(File f, ArrayList<User> userList) throws ClassNotFoundException, IOException {
		System.out.println("회원목록");
	    System.out.println("────────────────────────────────────");
	    System.out.println("순서              이름                     핸드폰번호");
		for (int i = 0; i < userList.size(); i++) { // 유저이름 번호출력
			System.out.println(i + "       " + userList.get(i).getName() + "               " + userList.get(i).getCellNum());
		}
		System.out.println("────────────────────────────────────");
		System.out.println("총 유저 수는 :" + userList.size() + "명 입니다."); // 유저리스트수
		System.out.println("");
	}

	// 책 추가
	private static void addBook(File f, HashMap<Integer, String> bookList) {
		while (true) {
			getBookList(f, bookList); // 수정
			Integer num = Library.getInt("도서를 추가합니다 책 번호를 입력해주세요", new Scanner(System.in));
			String name = Library.getString("책 이름을 입력해주세요", new Scanner(System.in));

			if (bookList.containsKey(num)) {
				System.out.println("이미 존재하는 번호입니다. 다시 입력해주세요.");
			} else {
				System.out.printf("isbn %d번 %s(이)가 등록되었습니다.", num, name);
				bookList.put(num, name);
				break;
			}
		}
	}

	// 책 수정
	private static void modifyBook(File f, HashMap<Integer, String> bookList) { // 도서수정
		getBookList(f, bookList);

		while (true) {
			String name ="";
			Integer num = Library.getInt("수정할 도서 번호를 입력해주세요.", new Scanner(System.in));

			if (bookList.get(num) != null) { // 북리스트의 isbn값이 null이 아니라면
				name = Library.getString("수정하실 도서명을 입력해주세요.", new Scanner(System.in));

			}else {
					System.out.println("존재하지 않는 도서 번호입니다. 다시 입력해주세요.");
				}
				
				if (bookList.containsKey(num)) { // 해당되는 isbn이 있다면
					bookList.put(num, name); // put
					System.out.printf("도서번호 %d번이 도서명 %s로 등록되었습니다.", num, name);
					break;

				} 
			}
	}

	// 책 삭제

	private static void delBook(File f, HashMap<Integer, String> bookList) {
		getBookList(f, bookList);

		while (true) {
			int i = Library.getInt("도서를 삭제합니다 삭제할 도서 번호를 입력해주세요", new Scanner(System.in));
			if (bookList.get(i) != null) {
				System.out.printf("isbn %d, %s (이)가 삭제되었습니다.\n", i, bookList.get(i)); // 추가
				System.out.println(); // 추가
				bookList.remove(i); // 리스트에서 삭제 후 유저 바로우북에 담는다.
				break;
			} else {
				System.out.println("존재하지 않는 도서 번호입니다. 다시 입력해주세요.");
				System.out.println("");
			}
		}

	}

	// 유저 삭제
	private static void deluser(File f, ArrayList<User> userList) throws ClassNotFoundException, IOException {
		getUserList(f, userList);
		int i = Library.getInt("계정을 삭제합니다 삭제할 계정번호를 입력해주세요", new Scanner(System.in));

		try {
			userList.remove(i);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("잘못된 번호를 입력하셨습니다.");
		}

	}

}

