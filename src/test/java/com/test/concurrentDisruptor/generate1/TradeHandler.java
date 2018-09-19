package com.test.concurrentDisruptor.generate1;

import java.util.UUID;

import com.lmax.disruptor.WorkHandler;

public class TradeHandler implements WorkHandler<Trade>{

	@Override
	public void onEvent(Trade event) throws Exception {
		//这里做具体的消费逻辑  
        event.setId(UUID.randomUUID().toString());//简单生成下ID  
        System.out.println(event.getId());  
	}

}
