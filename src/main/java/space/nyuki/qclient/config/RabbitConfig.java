package space.nyuki.qclient.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.nyuki.qclient.exception.AckFailException;

@Configuration
public class RabbitConfig {

	@Bean
	public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate) {
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		return new RabbitAdmin(rabbitTemplate);
	}


}
