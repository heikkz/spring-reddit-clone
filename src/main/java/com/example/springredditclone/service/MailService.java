package com.example.springredditclone.service;

import com.example.springredditclone.exception.SpringRedditException;
import com.example.springredditclone.model.NotificationEmail;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Класс для отправки email сообщений
 */
@Service
@AllArgsConstructor
@Slf4j
public class MailService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    /**
     * Отправить сообщение
     * @param email сообщение, отправляемое по email
     */
    @Async
    void sendMail(NotificationEmail email) {
        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springreddit@email.com");
            messageHelper.setTo(email.getRecipient());
            messageHelper.setSubject(email.getSubject());
            messageHelper.setText(mailContentBuilder.build(email.getBody()));
        };
        try {
            mailSender.send(mimeMessagePreparator);
            log.info("Activation email sent!!");
        } catch (MailException e) {
            throw new SpringRedditException("Exception occurred when sending mail to " + email.getRecipient());
        }
    }
}
