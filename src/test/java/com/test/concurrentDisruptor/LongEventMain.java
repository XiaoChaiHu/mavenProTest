package com.test.concurrentDisruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

public class LongEventMain {

	public static void main(String[] args) {
		// 创建缓冲池
		ExecutorService executor = Executors.newCachedThreadPool();
		LongEventFactory longEventFactory = new LongEventFactory();
		int ringBufferSize = 1024 * 1024; //

		/**
		//BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现
		WaitStrategy BLOCKING_WAIT = new BlockingWaitStrategy();
		//SleepingWaitStrategy 的性能表现跟BlockingWaitStrategy差不多，对CPU的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景
		WaitStrategy SLEEPING_WAIT = new SleepingWaitStrategy();
		//YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于CPU逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性
		WaitStrategy YIELDING_WAIT = new YieldingWaitStrategy();
		*/
		Disruptor<LongValueEvent> disruptor = new Disruptor<>(longEventFactory, ringBufferSize, executor,
				ProducerType.SINGLE, new YieldingWaitStrategy());
		//连接消费事件方法
		disruptor.handleEventsWith(new LongEventHandler());
		// 启动
		disruptor.start();
		//Disruptor 的事件发布过程是一个两阶段提交的过程：
		RingBuffer<LongValueEvent> ringBuffer=disruptor.getRingBuffer();
		LongEventProducer longEventProducer=new LongEventProducer(ringBuffer);
		ByteBuffer buffer=ByteBuffer.allocate(8);
		for (int i = 0; i < 100; i++) {
			buffer.putLong(0, i);
			longEventProducer.onData(buffer);
		}
		disruptor.shutdown();
		executor.shutdown();
		
	}
}
