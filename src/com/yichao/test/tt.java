package com.yichao.test;

import com.yichao.tools.InputTool;

public class tt {

	public static void main(String[] args) {
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
