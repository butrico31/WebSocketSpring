package com.site.chatteste.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedirectHttpToHttpsConfig implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        if (factory instanceof TomcatServletWebServerFactory) {
            TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) factory;
            tomcat.addAdditionalTomcatConnectors(createHttpConnector());
        }
    }

    private Connector createHttpConnector() {
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8080); // Porta HTTP para redirecionamento
        connector.setSecure(false);
        connector.setRedirectPort(8443); // Redireciona para HTTPS na porta 8443
        return connector;
    }
}
