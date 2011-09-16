package tk.jcyfkimi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailRegex {

	public static boolean mailRegex(String email) {
		Pattern p = null;
		Matcher m = null;
		boolean b = false;

		String reg = "^[a-zA-Z0-9][a-zA-Z0-9._-]*@([a-zA-Z0-9_]+\\.)+(com|gov|net|com\\.cn|edu\\.cn)$";
		p = Pattern.compile(reg);
		m = p.matcher(email);
		b = m.matches();
		return b;

	}
}