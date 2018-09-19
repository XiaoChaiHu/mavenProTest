package com.test.concurrentDisruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

public class LongEventProducer {

	private RingBuffer<LongValueEvent>ringBuffer;

	public LongEventProducer(RingBuffer<LongValueEvent> ringBuffer) {
		this.ringBuffer = ringBuffer;
	}
	
	public void onData(ByteBuffer buffer) {
		
		//可以把ringBuffer看做一个事件队列，那么next就是得到下面一个事件槽
		long sequence= ringBuffer.next();
		try {
			LongValueEvent event=ringBuffer.get(sequence);
			event.setValue(buffer.getLong(0));
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			//发布事件
			ringBuffer.publish(sequence);
		}
	}
	
	
}
