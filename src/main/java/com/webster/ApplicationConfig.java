package com.webster;

import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import java.util.Collections;
import java.util.List;

/**
 * Created by amit on 15/7/16.
 */
public class ApplicationConfig extends AbstractCouchbaseConfiguration implements TransactionManagementConfigurer {

    @Override
    protected List<String> getBootstrapHosts() {
        return Collections.singletonList("172.16.25.108");
    }

    @Override
    protected String getBucketName() {
        return "default";
    }

    @Override
    protected String getBucketPassword() {
        return "";
    }

    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager();
    }

}
