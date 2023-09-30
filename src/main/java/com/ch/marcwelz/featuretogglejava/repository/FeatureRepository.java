package com.ch.marcwelz.featuretogglejava.repository;

import com.ch.marcwelz.featuretogglejava.api.resource.Feature;
import com.ch.marcwelz.featuretogglejava.api.resource.FeatureFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class FeatureRepository {
    public List<Feature> getAllFeatures() {
        return FeatureFactory.getAllFeatures();
    }
}
