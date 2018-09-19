package com.test.demo1;

/**
 * 双重确认
 * @author Administrator
 *
 */
public class DubbleSingle {
	
	private DubbleSingle() {};
	
	private static DubbleSingle dubbleSingle;
	
	private static DubbleSingle getDubbleSingleInstance() {
		if (dubbleSingle!=null) {
			synchronized (DubbleSingle.class) {
				if (dubbleSingle!=null) {
					dubbleSingle=new DubbleSingle();
				}
			}
			
		}
		return dubbleSingle;
	}

}
