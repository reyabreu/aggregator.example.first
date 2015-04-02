package org.aggregator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reyabreu.aggregator.domain.MyItem;
import org.reyabreu.aggregator.integration.MyGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
// @SpringApplicationConfiguration(classes = Application.class)
@ContextConfiguration
public class ApplicationTests {

	static class Fixture {

		private static AtomicInteger idGenerator = new AtomicInteger();

		public static MyItem createItem(final String contents) {
			return new MyItem(idGenerator.incrementAndGet(), contents);
		}

	}

	@Autowired
	MyGateway gateway;

	@Autowired
	PollableChannel outputChannel;

	@Test
	public void shouldAcceptPayload() {
		List<MyItem> payload = new ArrayList<>();
		payload.add(Fixture.createItem("this"));
		payload.add(Fixture.createItem("is"));
		payload.add(Fixture.createItem("a"));
		payload.add(Fixture.createItem("test"));
		gateway.sendItems(payload);

		Message<?> message = outputChannel.receive(1000);
		Assert.assertThat(message, Matchers.notNullValue());
		Assert.assertThat(message.getPayload(),
				Matchers.instanceOf(ArrayList.class));

	}
}
