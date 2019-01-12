package com.rabbit.second.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

public class MyConsumer extends DefaultConsumer {
		private Channel channel;

		public MyConsumer(Channel channel) {
				super(channel);
				this.channel=channel;
		}

		@Override							//标签
		public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
				throws IOException {
				System.err.println("consumerTag: "+ consumerTag);
				System.err.println("envelope: "+ envelope.getExchange() + "--" + envelope.getDeliveryTag());
				System.err.println("properties: "+ properties);
				System.err.println("body: "+ new String(body));
				channel.basicNack(envelope.getDeliveryTag(),false,false);
		}
}
