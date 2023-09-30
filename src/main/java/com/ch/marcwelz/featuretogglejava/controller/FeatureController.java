package com.ch.marcwelz.featuretogglejava.controller;

import com.ch.marcwelz.featuretogglejava.api.resource.Feature;
import com.ch.marcwelz.featuretogglejava.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/features")
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService service;

    private final String REQUEST_KEY_PARAM = "/{name}";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Feature> getAllFeatures() {
        return service.getAllFeatures();
    }

    @GetMapping(
            value = REQUEST_KEY_PARAM,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Feature searchFeatures(@NotBlank @PathVariable String name) {
        return service.searchFeature(name);
    }

    @GetMapping(value = "/value" + REQUEST_KEY_PARAM, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getAccessValue(@NotBlank @PathVariable String name) {
        return service.getValue(name);
    }

    @PostMapping(
            value = REQUEST_KEY_PARAM,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus createFeature(
            @NotEmpty @RequestBody @Valid Feature feature) {
        return service.createFeature(feature);
    }

    @DeleteMapping(value = REQUEST_KEY_PARAM)
    public void deleteFeature(@NotBlank @PathVariable String name) {
        service.deleteFeature(name);
    }
}
