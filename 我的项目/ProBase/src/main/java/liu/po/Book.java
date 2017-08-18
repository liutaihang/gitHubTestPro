package liu.po;

import java.io.Serializable;

public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3833059620715195676L;
	private long bookId;
	private String name;
	private int number;
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", number=" + number + "]";
	}
	
	
}
