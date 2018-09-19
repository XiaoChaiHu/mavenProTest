package com.test.concurrentDisruptor;

import com.lmax.disruptor.EventFactory;

public class LongEventFactory implements EventFactory<LongValueEvent>{

	@Override
	public LongValueEvent newInstance() {
		
		return new LongValueEvent();
	}

}
