package com.ld.dhouse.web.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ImportResource({"classpath*:config/dubbo/*.xml"})
public class DubboConfiguration {
}
