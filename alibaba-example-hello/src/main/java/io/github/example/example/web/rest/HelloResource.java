package io.github.example.example.web.rest;

import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @apifoxProjectId: 2021987
 */
@RequestMapping("/api/hello")
public interface HelloResource {

    @Trace
    @GetMapping("/world")
    ResponseEntity<String> world(@RequestParam(value = "name",defaultValue = "world",required = false) String name,@RequestParam(value = "millis",defaultValue = "0",required = false) Long millis);
}
