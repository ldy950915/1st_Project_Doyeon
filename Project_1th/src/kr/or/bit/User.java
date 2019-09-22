package kr.or.bit;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

public class User implements Serializable {
   private String cellNum;
   private String name;
   private Date date;
   private HashMap<Integer, String> borrowBook = null;
   
   public Date getDate() {
		return date;
	}
	public User() {
       super();
       date = new Date();
   }
	
   public User(String cellNum, String name) {
       super();
       this.cellNum = cellNum;
       this.name = name;
   }
   public String getName() {
       return name;
   }
   public void setName(String name) {
       this.name = name;
   }
   public String getCellNum() {
       return cellNum;
   }
   public void setCellNum(String cellNum) {
       this.cellNum = cellNum;
   }
   public HashMap<Integer, String> getBorrowBook() {
       if (borrowBook == null) {
           borrowBook = new HashMap<Integer, String>();
       }
       return borrowBook;
   }
   @Override
  	public String toString() {
  		return "User [cellNum=" + cellNum + ", name=" + name + "]";
  	}
}

