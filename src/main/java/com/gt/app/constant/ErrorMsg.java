package com.gt.app.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * ErrorMsg
 *
 * @author yousheng
 * @since 2018/4/25
 */
@Component
public class ErrorMsg {

    @Autowired
    private MessageSource messageSource;

    private final Locale locale = LocaleContextHolder.getLocale();

    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, locale);
    }

    public String getMessage(String code) {
        return getMessage(code, null);
    }

}
