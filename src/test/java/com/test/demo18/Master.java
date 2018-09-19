package com.test.demo18;

import java.lang.Thread.State;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Master {
	/**
	 * 承装任务的集合
	 */
	private ConcurrentLinkedQueue<Task> workQuere = new ConcurrentLinkedQueue<Task>();
	
	/**
	 * 使用HashMap去承装所有的work对象
	 */
	private Map<String, Thread>works=new HashMap<String, Thread>();
	
	/**
	 * 使用一个容器承装所有的线程执行结果
	 */
	private ConcurrentHashMap<String, Object>resultMap=new ConcurrentHashMap<String, Object>();

	public Master(Work work,int count) {
		//每个任务中都需要有workQuere，和resultMap的引用
		//workQuere用于任务的领取
		//resultMap用于结果的写入
		work.setWorkQuere(workQuere);
		work.setResultMap(resultMap);
		for (int i = 0; i < count; i++) {
			works.put("第"+i+"个线程", new Thread(work));
		}
	}
	
	/**
	 * 用于提交任务
	 * @param task
	 */
	public void submit(Task task) {
		workQuere.add(task);
	}
	
	/**
	 * 执行所有的方法
	 */
	public void excude() {
		for (Map.Entry<String, Thread> task : works.entrySet()) {
			task.getValue().start();
		}
	}
	
	/**
	 * 检测状态
	 * @return
	 */
	public boolean completed() {
		boolean flag=true;
		for (Map.Entry<String, Thread> task : works.entrySet()) {
			//只要有一个不是完成状态都返回false
			if (task.getValue().getState()!=State.TERMINATED) {
				flag=false;
				break;
			}
		}
		return flag;
	}
	
	

}
