package com.yichao.test;

import java.util.Date;

import com.yichao.tools.InputTool;

public class tt {

	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(now);
		InputTool it = InputTool.getInstance();
		String s = "45345";
		if(it.isInt(s)) {
			int i = Integer.parseInt(s);
			System.out.println(i);
		}else {
			System.out.println(s);
		}
		
		

	}

}
