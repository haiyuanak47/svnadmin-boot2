package org.svnadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@EnableCaching
@EnableAsync
@EnableScheduling
public class SVNAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(SVNAdminApplication.class, args
        );

    }

}
