package com.telan.weixincenter.listener;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.util.ServletContextPropertyUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;

public abstract class LogbackWebConfigurer {
    /**
     * Parameter specifying the location of the logback config file
     */
    public static final String CONFIG_LOCATION_PARAM = "logbackConfigLocation";
    /**
     * Parameter specifying whether to expose the web app root system property
     */
    public static final String EXPOSE_WEB_APP_ROOT_PARAM = "logbackExposeWebAppRoot";


    private static LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    private static JoranConfigurator configurator = new JoranConfigurator();


    public static void initLogging(ServletContext servletContext) {
        if (exposeWebAppRoot(servletContext)) {
            WebUtils.setWebAppRootSystemProperty(servletContext);
        }

        String location = servletContext.getInitParameter(CONFIG_LOCATION_PARAM);
        if (location != null) {
            try {
                location = ServletContextPropertyUtils.resolvePlaceholders(location, servletContext);
                if (!ResourceUtils.isUrl(location)) {
                    location = WebUtils.getRealPath(servletContext, location);
                }
                servletContext.log("Initializing logback from [" + location + "]");
                try {
                    configurator.setContext(lc);
                    lc.reset();
                    configurator.doConfigure(location);
                    lc.start();
                } catch (JoranException ex) {
                    throw new IllegalArgumentException("Invalid 'log back configure' parameter: " + ex.getMessage());
                }
            } catch (FileNotFoundException ex) {
                throw new IllegalArgumentException("Invalid 'logbackConfigLocation' parameter: " + ex.getMessage());
            }
        }
    }

    /**
     * @param servletContext
     */
    public static void shutdownLogging(ServletContext servletContext) {
        servletContext.log("Shutting down logback");
        try {
            lc.stop();
        } finally {
            if (exposeWebAppRoot(servletContext)) {
                WebUtils.removeWebAppRootSystemProperty(servletContext);
            }
        }
    }

    /**
     * Return whether to expose the web app root system property,
     * checking the corresponding ServletContext init parameter.
     *
     * @see #EXPOSE_WEB_APP_ROOT_PARAM
     */
    private static boolean exposeWebAppRoot(ServletContext servletContext) {
        String exposeWebAppRootParam = servletContext.getInitParameter(EXPOSE_WEB_APP_ROOT_PARAM);
        return (exposeWebAppRootParam == null || Boolean.valueOf(exposeWebAppRootParam));
    }

}

