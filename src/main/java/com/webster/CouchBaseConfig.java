package com.webster;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.core.convert.CustomConversions;
import org.springframework.data.couchbase.core.convert.MappingCouchbaseConverter;
import org.springframework.data.couchbase.core.mapping.CouchbaseMappingContext;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by amit on 25/7/16.
 */
@Configuration
@EnableCouchbaseRepositories
@EnableJpaRepositories
@ComponentScan(value = "com.webster")
public class CouchBaseConfig extends AbstractCouchbaseConfiguration {
    @Value("${couchbase.cluster.bucket}")
    private String bucketName;

    @Value("${couchbase.cluster.password}")
    private String password;

    @Value("${couchbase.cluster.ip}")
    private String ip;

//    @Override
//    protected List<String> bootstrapHosts() {
//        return Arrays.asList(this.ip);
//    }

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList(this.ip);
    }

    @Override
    protected String getBucketName() {
        return this.bucketName;
    }

    @Override
    protected String getBucketPassword() {
        return this.password;
    }

    @Override
    @Bean(name = "couchbaseConversions")
    public CustomConversions customConversions() {

        List<Object> converters = new ArrayList<Object>();
        //converters.add(CDToBigDecimalConverter.INSTANCE);
        CustomConversions customConversions =  new CustomConversions(converters);
        return customConversions;
    }

    @Bean
    public MappingCouchbaseConverter mappingCouchbaseConverter() throws Exception {
        MappingCouchbaseConverter converter = new MappingCouchbaseConverter(new CouchbaseMappingContext());//ExpiringDocumentCouchbaseConverter(couchbaseMappingContext());
        converter.setCustomConversions(customConversions());
        return converter;
    }



//    public PlatformTransactionManager annotationDrivenTransactionManager() {
//        return new DataSourceTransactionManager();
//    }
}
