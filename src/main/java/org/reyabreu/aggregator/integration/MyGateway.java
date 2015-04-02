package org.reyabreu.aggregator.integration;

import java.util.List;

import org.reyabreu.aggregator.domain.MyItem;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MyGateway {

	@Gateway(requestChannel = "inputChannel")
	public void sendItems(final List<MyItem> payload);

}
