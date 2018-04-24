package com.gt.app.rest;

import com.gt.app.commons.Response;
import com.gt.app.utils.Responses;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

/**
 * BaseController
 *
 * @author yousheng
 * @since 2018/4/24
 */
public abstract class BaseController {
    protected Response checkBindingError(BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            String err = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return Responses.err(err);
        } else {
            return null;
        }
    }
}
