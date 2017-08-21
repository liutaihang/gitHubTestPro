package liu.common;

import java.io.UnsupportedEncodingException;

public class AppTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "123";
		System.out.println(new String(str.getBytes("utf-8"), "UTF-8"));
	}
}
