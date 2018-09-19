package com.test.concurrentDisruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongValueEvent>{

	@Override
	public void onEvent(LongValueEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println(event.getValue().longValue());
		
	}

}
