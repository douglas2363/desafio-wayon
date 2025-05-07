package br.com.desafio.Wayon.code.exemplo.adapter;

public class EmailSendingException  extends RuntimeException{
    private static final long serialVersionUID = 1L;


    public EmailSendingException(String message) {
        super(message);
    }

    public EmailSendingException(String message, Throwable cause) {
        super(message, cause);
    }
}
