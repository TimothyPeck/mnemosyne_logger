package ch.hearc.mnemosyne.mnemosyne_logger.activemq;

import javax.jms.TextMessage;

import org.apache.activemq.command.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ch.hearc.mnemosyne.mnemosyne_logger.model.SimpleLog;
import ch.hearc.mnemosyne.mnemosyne_logger.service.LoggerService;

@Component
public class LogConsumer {
    @JmsListener(destination = "${spring.activemq.queue-name}")
    public void receive(Message message) {
        System.out.println("Received message: " + message);

        ObjectMapper mapper = new ObjectMapper();

        TextMessage textMessage = (TextMessage) message;
        String text = null;

        try {
            text = textMessage.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleLog log = null;
        try {
            log = mapper.readValue(text, SimpleLog.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (log != null)
            System.out.println("Received log: " + log.toString());
        else
            System.out.println("Received log: " + text);

        try {
            LoggerService.log(log);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
