package pl.edu.wat.ai.app.interfaces.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import pl.edu.wat.ai.app.currency.CurrencyService;

import java.util.Arrays;

@Configuration
@EnableJms
class ReceiverConfig {

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Bean
    public ActiveMQConnectionFactory receiverActiveMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setTrustedPackages(Arrays.asList("java.lang", "pl.edu.wat.ai.app.interfaces.jms"));

        return activeMQConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory
                .setConnectionFactory(receiverActiveMQConnectionFactory());

        return factory;
    }

    @Bean
    public JmsConsumer receiver(CurrencyService currencyService) {
        return new JmsConsumer(currencyService);
    }
}
