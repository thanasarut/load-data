package sunseries.travel.request.handler.configuration;

import org.apache.camel.CamelContext;
import org.apache.camel.ThreadPoolRejectedPolicy;
import org.apache.camel.spi.ThreadPoolProfile;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestHandlerConfiguration {

    @Autowired
    private ShutdownStrategy shutdownStrategy;

    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            public static final String MY_DEFAULT_PROFILE = "myDefaultProfile";

            @Override
            public void beforeApplicationStart(CamelContext context) {

                ThreadPoolProfile threadPoolProfile = new ThreadPoolProfile();
                threadPoolProfile.setId(MY_DEFAULT_PROFILE);
                threadPoolProfile.setPoolSize(20);
                threadPoolProfile.setMaxPoolSize(20);
                threadPoolProfile.setMaxQueueSize(20);
                threadPoolProfile.setKeepAliveTime(25L);
                threadPoolProfile.setRejectedPolicy(ThreadPoolRejectedPolicy.Abort);
                context.getExecutorServiceManager().registerThreadPoolProfile(threadPoolProfile);
                shutdownStrategy.setTimeout(1);
                context.setShutdownStrategy(shutdownStrategy);
            }
        };
    }

}
