package io.github.example.gateway.configuration;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancedRetryFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.net.URI;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Primary
@Component
public class Scon implements SwaggerResourcesProvider {

    private List<Route> routes;

    private LoadBalancerClientFactory loadBalancedRetryFactory;

    public Scon(RouteLocator routeLocator, LoadBalancerClientFactory loadBalancedRetryFactory) {
        this.routes = routeLocator.getRoutes().collectList().block();
        this.loadBalancedRetryFactory = loadBalancedRetryFactory;
    }

    /**
     * Gets a result.
     *
     * @return a result
     */
    @Override
    public List<SwaggerResource> get() {

        return routes.stream().map(route -> {
            SwaggerResource swaggerResource = new SwaggerResource();
//            swaggerResource.setSwaggerVersion();
            swaggerResource.setName(route.getId());
            CompletableFuture<URI> future = new CompletableFuture();
            loadBalancedRetryFactory.getInstance(route.getUri().getHost()).choose().subscribe(new Subscriber<Response<ServiceInstance>>() {
                private ServiceInstance serviceInstance;
                @Override
                public void onSubscribe(Subscription subscription) {
                    subscription.request(1l);
                }

                @Override
                public void onNext(Response<ServiceInstance> serviceInstanceResponse) {
                    serviceInstance = serviceInstanceResponse.getServer();
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onComplete() {
                    future.complete(serviceInstance.getUri());
                }
            });

//            try {
                swaggerResource.setUrl("/api/hello"
                        +"/v3/api-docs");
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } catch (ExecutionException e) {
//                throw new RuntimeException(e);
//            }
            return swaggerResource;
        }).collect(Collectors.toList());
    }
}
