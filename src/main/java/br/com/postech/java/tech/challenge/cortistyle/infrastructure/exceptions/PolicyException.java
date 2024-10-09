package br.com.postech.java.tech.challenge.cortistyle.infrastructure.exceptions;

@SuppressWarnings("serial")
public class PolicyException extends RuntimeException{

    public PolicyException(String politica){
        super(politica);
    }
}
