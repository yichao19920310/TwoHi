package com.yichao.tools;

import java.util.Scanner;

public class InputTool {

	Scanner input = new Scanner(System.in);
	private static InputTool instance;
	private InputTool() {
		
	}
	
	public static InputTool getInstance() {
		
		if(instance == null) {
			instance = new InputTool();
		}
		return instance;
	}
	
	public int getInt() {
		int i = 0;
		while(true) {
			try {
				i = input.nextInt();
				break;
			} catch (Exception e) {
				System.out.println("输入有误,请重新输入!");
				input.next();
			}
		}
		return i;
	}
	public byte getByte() {
		byte i = 0;
		while(true) {
			try {
				i = input.nextByte();
				break;
			} catch (Exception e) {
				System.out.println("输入有误,请重新输入!");
				input.next();
			}
		}
		return i;
	}
	public short getShort() {
		short i = 0;
		while(true) {
			try {
				i = input.nextShort();
				break;
			} catch (Exception e) {
				System.out.println("输入有误,请重新输入!");
				input.next();
			}
		}
		return i;
	}
	public char getChar() {
		String i = "";
		char j = 0;
		while(true) {
			i = input.next();
			if(i.length() != 1) {
				System.out.println("输入有误,请重新输入!");
			} else {
				
				return j = i.charAt(0);
			}
		}
		
	}
	public float getFloat() {
		float i = 0;
		while(true) {
			try {
				i = input.nextFloat();
				break;
			} catch (Exception e) {
				System.out.println("输入有误,请重新输入!");
				input.next();
			}
		}
		return i;
	}
	public double getDouble() {
		double i = 0;
		while(true) {
			try {
				i = input.nextDouble();
				break;
			} catch (Exception e) {
				System.out.println("输入有误,请重新输入!");
				input.next();
			}
		}
		return i;
	}
	public boolean getBoolean() {
		boolean i = false;
		while(true) {
			try {
				i = input.nextBoolean();
				break;
			} catch (Exception e) {
				System.out.println("输入有误,请重新输入!");
				input.next();
			}
		}
		return i;
	}
	public long getLong() {
		long i = 0;
		while(true) {
			try {
				i = input.nextLong();
				break;
			} catch (Exception e) {
				System.out.println("输入有误,请重新输入!");
				input.next();
			}
		}
		return i;
	}
	
	public String getString() {
		String str = "";
		while(true) {
			str = input.next();
			if(str.length() == 0) {
				System.out.println("输入有误,请重新输入!");
			}else {
				break;
			}
		}
		
		return str;
	}
}
