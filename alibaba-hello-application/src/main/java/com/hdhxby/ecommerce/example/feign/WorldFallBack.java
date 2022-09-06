package com.hdhxby.ecommerce.example.feign;

import com.hdhxby.ecommerce.example.web.rest.WorldResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class WorldFallBack implements WorldClient {

    @Override
    public ResponseEntity<String> world(String name,Long millis) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE)
                .body(String.format("fallback %s,sleep %d millis.", name, millis));
    }
}