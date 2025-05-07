package br.com.desafio.Wayon.code.exemplo.adapter;

public class ThirdPartyEmailSender {

    public void sendEmail(String to, String subject, String body) {
        // Aqui você implementaria a lógica para enviar o e-mail usando um serviço de terceiros
        System.out.println("Enviando e-mail para: " + to);
        System.out.println("Assunto: " + subject);
        System.out.println("Corpo: " + body);
    }
}
