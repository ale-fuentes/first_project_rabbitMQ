package edu.ale;

import edu.ale.config.WebConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

@SpringBootApplication
public class FirstRabbitMQApplication extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final static String QUEUE_NAME = "hiale";
    static Logger logger
            = LoggerFactory.getLogger(FirstRabbitMQApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FirstRabbitMQApplication.class, args);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter charaterEncondingFilter = new CharacterEncodingFilter();
        charaterEncondingFilter.setEncoding("UTF-8");
        charaterEncondingFilter.setForceEncoding(true);

        return new Filter[] { charaterEncondingFilter };
    }
}
