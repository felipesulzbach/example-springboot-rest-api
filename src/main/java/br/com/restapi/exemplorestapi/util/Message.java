package br.com.restapi.exemplorestapi.util;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * @autor: Felipe Sulzbach
 */
@Component
public class Message {

    @Autowired
    private MessageSource source;

    private MessageSourceAccessor acessor;

    @PostConstruct
    private void init() {
        this.acessor = new MessageSourceAccessor(this.source, Locale.getDefault());
    }

    public String get(String codMessage) {
        return this.acessor.getMessage(codMessage);
    }

    public String get(String codMessage, Object... params) {
        return this.acessor.getMessage(codMessage, params);
    }
}
