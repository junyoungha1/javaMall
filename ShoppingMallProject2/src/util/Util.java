package util;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	static public Scanner sc = new Scanner(System.in);

	static public String getValue(String msg) {
		System.out.print(msg + " >> ");
		return sc.nextLine();
	}

	static public int getValue(String msg, int start, int end) {
		int num = -1;
		while (true) {
			try {
				System.out.print(msg + " >> ");
				num = sc.nextInt();
				if (num < start || num > end) {
					throw new Exception(start + "-" + end + "사이 값 입력");
				}
			} catch (InputMismatchException e) {
				System.err.println("숫자만 입력하세요");
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			if (num >= start && num <= end) {
				break;
			} else {
				sc.nextLine();
			}
		}
		sc.nextLine();
		return num;
	}
}
