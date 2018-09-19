package com.test.concurrentDisruptor.generate1;

import com.lmax.disruptor.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestMain {

	public static void main(String[] args) {
		int BUFFER_SIZE=1024;  
		EventFactory<Trade>tEventFactory=new EventFactory<Trade>() {

			@Override
			public Trade newInstance() {
				
				return new Trade();
			}
		};
		RingBuffer<Trade>ringBuffer=RingBuffer.createSingleProducer(tEventFactory, BUFFER_SIZE);
		SequenceBarrier sequenceBarrier=ringBuffer.newBarrier();
		ExecutorService executors= Executors.newFixedThreadPool(4);
		WorkHandler<Trade>workHandler=new TradeHandler();
		WorkerPool<Trade>workerPool=new WorkerPool<>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(),workHandler);
		workerPool.start(executors);
		//下面这个生产8个数据
        for(int i=0;i<8;i++){  
            long seq=ringBuffer.next();  
            ringBuffer.get(seq).setPrice(Math.random()*9999);  
            ringBuffer.publish(seq);  
        }  
        try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        workerPool.halt();  
        executors.shutdown(); 
	}
}
