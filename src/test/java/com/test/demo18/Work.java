package com.test.demo18;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.expression.spel.ast.OpAnd;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Work implements Runnable {

	private ConcurrentLinkedQueue<Task> workQuere;

	private ConcurrentHashMap<String, Object> resultMap;

	public void setWorkQuere(ConcurrentLinkedQueue<Task> workQuere) {
		this.workQuere = workQuere;
	}

	public void setResultMap(ConcurrentHashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

	@Override
	public void run() {
		while (true) {
			Task input = workQuere.poll();
			if (input == null) {
				break;
			}
			// 真正的业务
			Object object= handle(input);
			resultMap.put(input.getId()+"", object);

		}

	}

	private Object handle(Task input) {
		Object output = new Task();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;

	}

}
