package com.github.ttwd80.simplesendsns;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishResult;

@ContextConfiguration({ "classpath:app.xml" })
public class AppTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	AmazonSNSClient amazonSNSClient;
	
	@Value("${aws.sns.topic}")
	String topic;

	@Test
	public void test() throws InterruptedException {
		Logger logger = LoggerFactory.getLogger(getClass().getName());
		PublishResult publishResult1 = amazonSNSClient.publish(topic, "message 1");
		logger.info("message id for message #1 -> {}", publishResult1.getMessageId());
		logger.info("SLEEPING NOW");
		Thread.sleep(45_000L);
		logger.info("JUST WOKE UP");
		PublishResult publishResult2 = amazonSNSClient.publish(topic, "message 2");
		logger.info("message id for message #2 -> {}", publishResult2.getMessageId());
	}
}
