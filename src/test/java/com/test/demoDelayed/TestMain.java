package com.test.demoDelayed;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;

import org.joda.time.DateTime;

public class TestMain {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<Task>delayQueue=new DelayQueue<Task>();
		DateTime dateTime=new DateTime();
		dateTime=dateTime.plusMinutes(1);
		Task task=new Task(dateTime.toDate());
		dateTime=dateTime.plusMinutes(5);
		Task task2=new Task(dateTime.toDate());
		delayQueue.put(task);
		delayQueue.put(task2);
		delayQueue.toString();
		while (true) {
			Task task3=delayQueue.take();
			System.out.println("获取到啦！"+task3.toString());
		}
		//Executors
		
	}
}
