package com.gt.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * BootstrapApplication
 *
 * @author yousheng
 * @since 2018/4/23
 */
@SpringBootApplication
public class BootstrapApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(BootstrapApplication.class).run(args);
    }
}
