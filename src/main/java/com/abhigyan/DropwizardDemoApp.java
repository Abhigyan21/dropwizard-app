package com.abhigyan;

import com.abhigyan.config.BasicConfiguration;
import com.abhigyan.model.Brand;
import com.abhigyan.repository.BrandRepository;
import com.abhigyan.resource.BrandResource;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;

import java.util.List;

/**
 * Hello world!
 *
 */
public class DropwizardDemoApp extends Application<BasicConfiguration> {

    public static void main(String[] args) throws Exception {
        new DropwizardDemoApp().run(args);
    }

    @Override
    public void run(BasicConfiguration basicConfiguration, Environment environment) {
        BrandRepository brandRepository = new BrandRepository(initBrands());
        BrandResource brandResource = new BrandResource(5, brandRepository);

        environment
                .jersey()
                .register(brandResource);

        CollectorRegistry collectorRegistry = new CollectorRegistry();
        collectorRegistry.register(new DropwizardExports(environment.metrics()));
    }

    private List<Brand> initBrands() {
        return List.of(new Brand(1L, "Samsung"),
                new Brand(2L, "Apple"),
                new Brand(3L, "Microsoft"),
                new Brand(4L, "Facebook"),
                new Brand(5L, "Google"));
    }

    @Override
    public void initialize(Bootstrap<BasicConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }
}