package com.ch.marcwelz.featuretogglejava.service;

import com.ch.marcwelz.featuretogglejava.api.resource.Feature;
import com.ch.marcwelz.featuretogglejava.exception.FeatureToggleException;
import com.ch.marcwelz.featuretogglejava.exception.ResourceNotFoundException;
import com.ch.marcwelz.featuretogglejava.repository.FeatureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class FeatureService {

    private final FeatureRepository repository;

    public List<Feature> getAllFeatures() {
        return getFeatures();
    }

    public Feature searchFeature(String name) {
        Optional<Feature> feature = getFeatures()
                .stream()
                .filter(e -> e.getName().equals(name))
                .findFirst();
        if (feature.isEmpty()) throw new ResourceNotFoundException("No feature with key '" + name + "' could be found");
        return feature.get();
    }

    public HttpStatus createFeature(Feature feature) {
        List<Feature> featureList = getAllFeatures();
        if(searchFeature(feature.getName()) != null) {
            throw new FeatureToggleException("Feature '" + feature.getName() + "' already exists!");
        }

        featureList.add(feature);
        return HttpStatus.CREATED;
    }

    public boolean getValue(String name) {
        Feature feature = searchFeature(name);
        return feature.isValue() && checkTime(feature);
    }

    public void deleteFeature(String name) {
        List<Feature> featureList = getAllFeatures();
        if (!featureList.removeIf(feature -> feature.getName().equals(name)))
            throw new ResourceNotFoundException("No feature with key '" + name + "' could be found");
    }

    private List<Feature> getFeatures() {
        List<Feature> featureList = repository.getAllFeatures();
        if(featureList.isEmpty()) {
            log.info("Feature list is empty");
            return Collections.emptyList();
        }
        return featureList;
    }

    private boolean checkTime(Feature feature) {
        LocalDateTime currentTime = LocalDateTime.now();
        if (feature.getValidFrom() == null && feature.getValidTo() == null) return true;
        return currentTime.isAfter(feature.getValidFrom()) && currentTime.isBefore(feature.getValidTo());
    }
}
