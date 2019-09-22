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
	private static File fileAddress = new File("C:\\test\\BitLibrary");; // ÆÄÀÏÀ§Ä¡, ¾ø´Ù¸é »ý¼º
	private static ArrayList<User> userList = null; // À¯Àú¸®½ºÆ®
	private static HashMap<Integer, String> bookList = null; // isbn , book
	private String today; // ÇöÀç½Ã°£
	

	public Library() throws ClassNotFoundException, IOException {
		admin = new Admin();
	}

	
	// µµ¼­°ü ½ÃÀÛ
	public void begin() throws ClassNotFoundException, IOException {

		while (true) {
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡ º°¸¶´çµµ¼­°ü¿¡ ¿À½Å°ÍÀ» È¯¿µÇÕ´Ï´Ù ¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("   ¦£¦¤    ¦£¦¤    ¦£¦¤    ¦£¦¤    ¦£¦¤    ¦£¦¤      ");
			System.out.println("   ¦¦¦¥    ¦¦¦¥    ¦¦¦¥    ¦¦¦¥    ¦¦¦¥    ¦¦¦¥     ");
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("       ¿øÇÏ´Â Ç×¸ñÀ» ¼±ÅÃÇØ ÁÖ¼¼¿ä                      ");
			System.out.println("");
			System.out.println("   0.ÇÁ·Î±×·¥ Á¾·á     1.·Î±×ÀÎ     2.È¸¿øµî·Ï  " );
			int operation = getInt("", new Scanner(System.in));
			if (operation == 1) {
				System.out.println("");
				System.out.println("¿øÇÏ´Â Ç×¸ñÀ» ¼±ÅÃÇÏ¼¼¿ä");
				System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
				int useroradmin = getInt("  0.Á¾·á     1.È¸¿ø·Î±×ÀÎ     2.°ü¸®ÀÚ·Î±×ÀÎ    ", new Scanner(System.in));
				if (useroradmin == 1) {
					this.userLogin(); // À¯Àú·Î±×ÀÎ ¸Þ¼Òµå
					break;
				} else if (useroradmin == 2) {
					this.adminlogin(); // ¾îµå¹Î·Î±×ÀÎ ¸Þ¼Òµå
				} else if (useroradmin == 0) {
					saveFile(); // ÆÄÀÏÀúÀå
					break; // Á¾·á
				} else {
					System.out.println("Àß¸øµÈ °ªÀ» ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù.");
				}
			} else if (operation == 2) {
				this.register(); // È¸¿øµî·Ï ¸Þ¼Òµå
			} else if (operation == 0) {
				saveFile(); // ÆÄÀÏÀúÀå
				break; // Á¾·á
			} else {
				System.out.println("Àß¸øµÈ °ªÀ» ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù.");
			}
		}
	    System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
	}

	// ·Î±×ÀÎ ¸Þ¼Òµå
	public void userLogin() throws ClassNotFoundException, IOException {
		// ·Î±×ÀÎ ÄÜ¼ÖÃ¢
		System.out.println("");
		System.out.println("¡Ü È¸¿ø·Î±×ÀÎ  ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		while (true) {
			System.out.println("ÇÚµåÆù¹øÈ£ Çü½Ä  ex)010-0000-0000");
			System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			String cellNum = getString("ÇÚµåÆù¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.", new Scanner(System.in));
			String name = getString("ÀÌ¸§À» ÀÔ·ÂÇÏ¼¼¿ä.", new Scanner(System.in));
			// ·Î±×ÀÎ µ¥ÀÌÅÍ È®ÀÎ
			boolean access = false; // Çã¿ë¿©ºÎ
			for (User user : getUserList()) {
				if (cellNum.equals(user.getCellNum())) {
					if (name.equals(user.getName())) {
						access = true; // ¾ÆÀÌµð ºñ¹Ð¹øÈ£ ¸ÂÀ¸¸é Çã¿ë°ª true
						setDate(); // ½Ã°£ set
						System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
						System.out.println("   " + name + "´Ô È¯¿µÇÕ´Ï´Ù\t");
						System.out.println("   " + "Á¢¼Ó³¯Â¥ :" + today);
						System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
						System.out.println("");
						userloginyes(user); // ·Î±×ÀÎ
						break;
					}
				}
			}
			if (!access) { // ¾ÆÀÌµð°¡ ¾øÀ»¶§ falseÀÏ¶§
				// ·Î±×ÀÎ ¿¡·¯¸¦ ¸®ÅÏ
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
		// ·Î±×ÀÎ ÄÜ¼ÖÃ¢
		System.out.println("");
		System.out.println("¡Ü °ü¸®ÀÚ·Î±×ÀÎ  ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		while (true) {
			String id = getString("¾ÆÀÌµð¸¦ ÀÔ·ÂÇÏ¼¼¿ä", new Scanner(System.in));
			String pw = getString("ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä", new Scanner(System.in));
			// ·Î±×ÀÎ µ¥ÀÌÅÍ È®ÀÎ
			boolean access = false;
			if (id.equals(admin.ID)) {
				if (pw.equals(admin.PW)) {
					access = true; // ¾ÆÀÌµð ºñ¹Ð¹øÈ£ ¸ÂÀ¸¸é Çã¿ë°ª true
					setDate();
					System.out.println("¦£¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¤");
					System.out.println("    °ü¸®ÀÚ¸ðµå·Î ·Î±×ÀÎÇÏ¼Ì½À´Ï´Ù.\t");
					System.out.print("    " + "Á¢¼Ó³¯Â¥ :" + today + "\n");
					System.out.println("¦¦¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¥");
					adminloginyes(admin); // ·Î±×ÀÎ
					break;
				}
			} else {
				System.out.println("°ü¸®ÀÚ ¾ÆÀÌµð¿Í ºñ¹Ð¹øÈ£ ÀÔ·ÂÀ» È®ÀÎÇØÁÖ¼¼¿ä. ");
				break;
			}

			if (!access) { // ¾ÆÀÌµð°¡ ¾øÀ»¶§ falseÀÏ¶§
				// ·Î±×ÀÎ ¿¡·¯¸¦ ¸®ÅÏ
				int loginerrorreturn = this.loginError();
				if (loginerrorreturn == 1) {
					break;
				}
			} else {
				break;
			}
		}
	}

	
	// È¸¿øµî·Ï
	public void register() throws ClassNotFoundException, IOException {
		System.out.println("¡Ü È¸¿øµî·Ï  ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		String registercellNum;
		while (true) {
			System.out.println("ÇÚµåÆù¹øÈ£ Çü½Ä  ex)010-0000-0000");
			System.out.println("¸Ç ¾Õ ÀÚ¸®´Â 010/011/016/017/018/019¸¸ °¡´É");
			registercellNum = getString("ÇÚµåÆù¹øÈ£¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä", new Scanner(System.in));
			String cellformat = "^01(?:0|1[6-9])[-](\\d{3}|\\d{4})[-](\\d{4})$"; // Á¤±ÔÇ¥Çö½Ä
			boolean cellNumExist = false; // È¸¿ø Á¸Àç ¿©ºÎ
			for (User user : getUserList()) {
				if (user.getCellNum().equals(registercellNum)) {
					cellNumExist = true; // ÇÚµåÆù¹øÈ£°¡ ÀÖ´Ù¸é
					break;
				}
			}
			if (!registercellNum.matches(cellformat)) {
				System.out.println("Àß¸øÀÔ·ÂÇÏ¼Ì½À´Ï´Ù.");
				System.out.println("ÇÚµåÆù¹øÈ£ Çü½Ä  ex)010-0000-0000");
				System.out.println("¸Ç ¾Õ ÀÚ¸®´Â 010/011/016/017/018/019¸¸ °¡´É");
				return;
			} // ¹øÈ£ Çü½ÄÀÌ ´Ù¸£´Ù¸é ³ª¿À´Â ÀÌÇÁ¹®

			if (cellNumExist) { // À¯Àú°¡ Á¸ÀçÇÑ´Ù
				System.out.println("µ¿ÀÏÇÑ ÇÚµåÆù¹øÈ£°¡ Á¸ÀçÇÕ´Ï´Ù");
			} else {
				break; // Á¸ÀçÇÏÁö ¾Ê´Â´Ù¸é while¹® Å»Ãâ
			}
		}

		// È¸¿øÀÌ¸§ ÀÔ·Â
		String registerName = getString("ÀÌ¸§À» ÀÔ·ÂÇØ ÁÖ¼¼¿ä", new Scanner(System.in));
		String nameformat = "^[a-zA-Z°¡-ÆR]*$"; // ÀÌ¸§ Á¤±ÔÇ¥Çö½Ä
		if (!registerName.matches(nameformat)) {
			System.out.println("Àß¸øµÈ Çü½ÄÀ» ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù.");
			System.out.println("¾ËÆÄºª È¤Àº ÇÑ±Û·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			return;
		} else {
			getUserList().add(new User(registercellNum, registerName)); // ¸Â´Ù¸é È¸¿ø¸®½ºÆ®¿¡ µî·Ï
			saveFile();
			System.out.println(registerName + "´Ô È¸¿ø°¡ÀÔÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
			System.out.println("");
		}

	}

	public void userloginyes(User user) throws IOException, ClassNotFoundException {

		while (true) {
			System.out.println("¡Ü È¸¿ø±â´É  ¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("1.µµ¼­¸ñ·Ï                                        ");
			System.out.println("2.´ëÃâ¸ñ·Ï                                        ");
			System.out.println("3.´ëÃâ                                                       ");
			System.out.println("4.¹Ý³³                                                       ");
			System.out.println("5.È¸¿øÅ»Åð                                                       ");
			System.out.println("0.·Î±×¾Æ¿ô                                                       ");
			int operationnum = getInt("", new Scanner(System.in));
			if (operationnum == 0) { // ·Î±×¾Æ¿ô
				System.out.println("Á¤»óÀûÀ¸·Î ·Î±×¾Æ¿ô µÇ¾ú½À´Ï´Ù.");
				System.out.println("ÀÌ¿ëÇØÁÖ¼Å¼­ °¨»çÇÕ´Ï´Ù.");
				break;
			} else if (operationnum == 5) { // È¸¿øÅ»Åð
				accountCancellation(user);
				break;
			}
			this.operationnum(user, operationnum); // 1,2,3,4¸Þ¼Òµå ÇÔ¼ö È£Ãâ
		}
	}

	public void adminloginyes(Admin admin) throws IOException, ClassNotFoundException {
		System.out.println("");

		admin.start();  // ¾îµå¹ÎÀÌ¶ó¸é ¾îµå¹ÎÅ¬·¡½º·Î ÀÌµ¿
	}

	
	// ·Î±×ÀÎ ¿¡·¯ ¸Þ¼Òµå
	public int loginError() throws ClassNotFoundException, IOException {
		System.out.println("");
		System.out.println("Àß¸øÀÔ·ÂÇÏ¼Ì½À´Ï´Ù.");
		int x = 0;
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("1. ÀçÀÔ·Â                                                  ");
		System.out.println("2. È¸¿ø°¡ÀÔ                                                ");
		System.out.println("3. ÇÁ·Î±×·¥ Á¾·á");
		int operationerror = getInt("", new Scanner(System.in));
		switch (operationerror) {
		case 1:
			break; // ÀçÀÔ·Â
		case 2:
			register(); // È¸¿øµî·Ï
			break;
		case 3: 
			x = 1; // Á¾·á
		default:
			System.out.println("Àß¸øµÈ ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù.");
			break;
		}
		return x;
	}

	
	// ÀÚ¼¼ÇÑ Á¶ÀÛ¹æ¹ý
	public void operationnum(User user, int operationnum) throws IOException, ClassNotFoundException {
		// ÀÚ¼¼ÇÑ Á¶ÀÛ switch
		switch (operationnum) {
		case 1: // µµ¼­¸ñ·Ï º¸±â
			showBookList();
			break;
		case 2:
			// ´ëÃâ¸ñ·Ï º¸±â
			System.out.println("");
			showBorrowBookList(user);
			break;
		case 3:
			// ´ëÃâ
			showBookList();
			borrowBook(user);
			break;
		case 4:
			// ¹Ý³³
			giveBack(user);
			break;
		default:
			System.out.println("Á¤È®ÇÑ ¼ýÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä");
			break;
		}
	}

	
	private void showBookList() throws IOException, ClassNotFoundException {
		Set<Map.Entry<Integer, String>> bookListSet = getBookList().entrySet(); // ºÏ¸®½ºÆ® setÀ¸·Î ¹Þ¾Æ¿È
		if (bookListSet.size() != 0) { // »çÀÌÁî°¡ 0ÀÌ ¾Æ´Ï¶ó¸é
			System.out.println("µµ¼­¸ñ·Ï");

		    System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
	  	    System.out.println("isbn     µµ¼­¸í");

			for (Entry<Integer, String> entry : bookListSet) { // ÃÑ Ã¥ ¸®½ºÆ® º¸¿©ÁÖ±â
	              System.out.println(" " + entry.getKey() + "      " + entry.getValue());
			}
		
		    System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
			System.out.println("ÇöÀç ¿ì¸® µµ¼­°üÀÇ ÃÑ µµ¼­ ¼ö´Â [" + bookListSet.size() + "]°³ ÀÔ´Ï´Ù."); // ÃÑ Ã¥ °³¼ö
			System.out.println("");

		} else {
			System.out.println("ÇöÀç µµ¼­°ü¿¡ ´ë¿©°¡´ÉÇÑ µµ¼­°¡ ¾ø½À´Ï´Ù.");
		}
	}

	
	private void showBorrowBookList(User user) { // À¯Àú°¡ ºô¸° Ã¥ ¸®½ºÆ®
		Set<Map.Entry<Integer, String>> borrowBookSet = user.getBorrowBook().entrySet();
		if (borrowBookSet.size() != 0) {
     	   System.out.println("´ëÃâÇÏ½Å µµ¼­ ¸ñ·Ï  ");
		    System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		    System.out.println("isbn     µµ¼­¸í");
			for (Entry<Integer, String> entry : borrowBookSet) {
              System.out.println(" " + entry.getKey() + "      " + entry.getValue());
			}
			
          System.out.println("");
		} else {
          System.out.println(user.getName() + "´ÔÀÇ µµ¼­ ´ë¿© ¸ñ·ÏÀÌ ¾ø½À´Ï´Ù.");
          System.out.println("");
		}
	}


	public void borrowBook(User user) throws IOException, ClassNotFoundException { // ´ëÃâ

		int booki = getInt("¿øÇÏ´Â Ã¥ ¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä", new Scanner(System.in));
		while (true) {
			HashMap<Integer, String> bookList = getBookList();
			if (bookList.get(booki) != null) {
				System.out.println("");
				System.out.printf("%s´Ô µµ¼­ [%s]°¡ Á¤»óÀûÀ¸·Î ´ëÃâ µÇ¾ú½À´Ï´Ù.\n", user.getName(), bookList.get(booki));
				System.out.println("");
				user.getBorrowBook().put(booki, bookList.get(booki));
				bookList.remove(booki);
				saveFile();			
				break;

			} else {
				System.out.println("¾Ë¸Â´Â µµ¼­ ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä");
				break;
			}
		}
	}

	// ¹Ý³³
	public void giveBack(User user) throws IOException, ClassNotFoundException {
		System.out.println("");
		System.out.println(user.getName()+ "´ÔÀÌ" );
		showBorrowBookList(user);
		int booki = getInt("¹Ý³³ÇÒ Ã¥ ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä", new Scanner(System.in));
		while (true) {
			if (user.getBorrowBook().get(booki) != null) {
				System.out.println("");
				System.out.printf("%s´Ô²²¼­ µµ¼­ [%s]ÀÇ ¹Ý³³À» ¿Ï·áÇÏ¼Ì½À´Ï´Ù.\n", user.getName(), user.getBorrowBook().get(booki));
				getBookList().put(booki, user.getBorrowBook().get(booki));
				user.getBorrowBook().remove(booki);
				saveFile();
				break;

			} else {
				System.out.println("¾Ë¸Â´Â µµ¼­ ¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä");
				break;
			}
		}
	}

	
	// È¸¿øÅ»Åð
	public void accountCancellation(User user) throws ClassNotFoundException, IOException {
		System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.println("Å»Åð ÈÄ¿¡´Â ´Ù½Ã °èÁ¤À» »ý¼ºÇØ¾ß ÇÏ¸ç, ´ëÃâ±â·ÏÀº »ç¶óÁý´Ï´Ù. ±×·¡µµ Å»ÅðÇÏ½Ã°Ú½À´Ï±î?");
		System.out.println("1. È®ÀÎ");
		System.out.println("0. µÇµ¹¾Æ°¡±â");
		String operation = getString("¿øÇÏ´Â ¹øÈ£´Â ¼±ÅÃÇÏ¼¼¿ä", new Scanner(System.in)); //0 ÀÌ¿ÜÀÇ ¹øÈ£¸¦ ´©¸£½Ã¸é ´Ù½Ã µÇµ¹¾Æ °©´Ï´Ù.
		
		if(!(user.getBorrowBook() == null)) {
			System.out.println("ÇöÀç ´ë¿©ÁßÀÎ µµ¼­°¡ ÀÖ½À´Ï´Ù.");
			System.out.println("¹Ý³³ ÈÄ ÁøÇàÇØÁÖ¼¼¿ä.");
			System.out.println("");
			userloginyes(user);

			
			if (operation.equals("1")) {
				for (User value : getUserList()) {
					if (user.getName().equals(value.getName())) {
						getUserList().remove(value); // È¸¿ø»èÁ¦
						saveFile();
						System.out.println("ÀÌ¿ëÇØÁÖ¼Å¼­ °¨»çÇÕ´Ï´Ù.");
						break;
					}
				}
			}else {
				userloginyes(user);
				System.out.println("");
			}
		}

	}

	// ÆÄÀÏÁÖ¼Ò °¡Á®¿À±â
	public static File getFileAddress() {
		if (!fileAddress.isDirectory()) {
			fileAddress.mkdirs();
		}
		return fileAddress;
	}

	

	// À¯Àú ¸®½ºÆ® ºÒ·¯¿À±â
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

	// Ã¥ ¸®½ºÆ® ºÒ·¯¿À±â
	public static HashMap<Integer, String> getBookList() throws IOException, IOException, ClassNotFoundException {
		if (bookList == null) { // nullÀÌ¶ó¸é Ã¥ ¸®½ºÆ® ºÒ·¯¿Â´Ù.
			try {
				FileInputStream fisBook = new FileInputStream(new File(fileAddress, "Book.txt"));
				BufferedInputStream bisBook = new BufferedInputStream(fisBook);
				ObjectInputStream ooBook = new ObjectInputStream(bisBook);
				Object obj = ooBook.readObject();
				bookList = (HashMap<Integer, String>) obj;
				fisBook.close();
				bisBook.close();
				ooBook.close();

			} catch (Exception e) { // ¿¡·¯°¡ ¶á´Ù¸é Ã¥À» ³Ö´Â´Ù.
				bookList = new HashMap<Integer, String>();
				bookList.put(1, "ÀÚ¹ÙÀÇ Á¤¼®");
				bookList.put(2, "½±°Ô ¹è¿ì´Â jsp ÇÁ·Î±×·¡¹Ö");
				bookList.put(3, "¾ÆÇÃ¼ö·Ï Ã»ÃáÀÌ´Ù");
				bookList.put(4, "Æò¹üÇÑ »î");
				bookList.put(5, "º¸ÀÌÁö ¾Ê´Â ¼Õ");
				bookList.put(6, "µµ¹Ú»ç");
				bookList.put(7, "°æ¿µÀÚÀÇ ¸¶ÀÎµå");
				bookList.put(8, "½ÅÀÇ Å¾");
				bookList.put(9, "±è¾¾Ç¥·ù±â");
				bookList.put(10, "¿À¶óÅ¬·Î ¹è¿ì´Â µ¥ÀÌÅÍº£ÀÌ½º ÀÔ¹®");

			}
		}
		return bookList;
	}

	// IO¸Þ¼Òµå ÀúÀå±â´É
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
	
	// ½ºÄ³³Ê·Î ½ºÆ®¸µ°ª ¹Þ´Â ¸Þ¼Òµå
	public static String getString(String i, Scanner sc) {
		System.out.println(i);
		String value = sc.nextLine();
		return value;
	}

	// ½ºÄÉ³Ê·Î ÀÎÆ®°ª ¹Þ´Â ¸Þ¼Òµå
	public static int getInt(String i, Scanner sc) {
		System.out.println(i);
	    System.out.println("¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡");
		System.out.print("¦¡¦¡¦¡> ");
		while (true) {
			if (sc.hasNextInt()) {
				boolean intFlag = true;
				while (intFlag) {
					int value = sc.nextInt();
					if (value < 0) {
						intFlag = false;
						System.out.println("Á¤È®ÇÑ °ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä");
					} else {
						return value;
					}
				}
			} else {
				System.out.println("Á¤È®ÇÑ °ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä");
				sc = new Scanner(System.in);
			}
		}
	}

	// ÇöÀç³¯Â¥ ¸¸µå´Â ¸Þ¼Òµå
	public void setDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy³â MM¿ùddÀÏ HH½ÃmmºÐssÃÊ");
		java.util.Date date = new java.util.Date();
		today = sdf.format(date);
	}

}