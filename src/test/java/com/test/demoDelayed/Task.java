package com.test.demoDelayed;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Task implements Delayed{

	/**
	 * 到期时间
	 */
	private Date endTime;
	
	

	public Task(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public int compareTo(Delayed other) {
		if (other.equals(this)) {
			return 0;
		}
		if (other instanceof Task) {
			Task task=(Task) other;
			long diff =this.endTime.getTime()-task.getEndTime().getTime();
			if (diff < 0) {
				return -1;
			}else if (diff > 0) {
				return 1;
			}else {
				return 0;
            }	
		}
		long d = (this.getDelay(TimeUnit.MILLISECONDS) - other.getDelay(TimeUnit.MILLISECONDS));
		return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
	}
	
	

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return  unit.convert(this.endTime.getTime()-new Date().getTime(), TimeUnit.MILLISECONDS);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Task [endTime=");
		builder.append(endTime);
		builder.append("]");
		return builder.toString();
	}
	
	

}
