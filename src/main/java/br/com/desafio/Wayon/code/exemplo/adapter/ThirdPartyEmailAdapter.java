package br.com.desafio.Wayon.code.exemplo.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThirdPartyEmailAdapter implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThirdPartyEmailAdapter.class);

    private final ThirdPartyEmailSender thirdPartyEmailSender;

    public ThirdPartyEmailAdapter(ThirdPartyEmailSender thirdPartyEmailSender) {
        if (thirdPartyEmailSender == null) {
            throw new IllegalArgumentException("Remetente de e-mail de terceiros não pode ser nulo");
        }
        this.thirdPartyEmailSender = thirdPartyEmailSender;    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        validateParameters(to, subject, body);

        LOGGER.info("Preparando para enviar e-mail para: {}, Assunto: {}", to, subject);

        try {
            thirdPartyEmailSender.sendEmail(to, subject, body);
            LOGGER.info("E-mail enviado com sucesso para: {}", to);
        } catch (Exception e) {
            LOGGER.error("Falha ao enviar e-mail para: {}", to, e);
            throw new EmailSendingException("Falha ao enviar e-mail", e);
        }
    }

    private void validateParameters(String to, String subject, String body) {
        if (to == null || to.isBlank()) {
            throw new IllegalArgumentException("Destinatário não pode ser nulo ou vazio");
        }
        if (subject == null || subject.isBlank()) {
            throw new IllegalArgumentException("Assunto não pode ser nulo ou vazio");
        }
        if (body == null || body.isBlank()) {
            throw new IllegalArgumentException("Corpo do e-mail não pode ser nulo ou vazio");
        }
    }
}