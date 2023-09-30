package com.ch.marcwelz.featuretogglejava.api.resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class FeatureFactory {
    public static List<Feature> getAllFeatures() {
        return Arrays.asList(
                new Feature("feature-1", "to be implemented", false, LocalDateTime.now(), null),
                new Feature("feature-2", "done", false, null, null),
                new Feature("feature-3", "todo", false, null, null),
                new Feature("feature-4", "finish after release xxx", false, null, null),
                new Feature("feature-5", "done", false,
                        LocalDateTime.parse("18.08.2023 12:30:30", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")),
                        LocalDateTime.parse("20.08.2023 17:43:22", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))),
                new Feature("feature-6", "in progress", false, LocalDateTime.now(),
                        LocalDateTime.parse("30.10.2023 10:00:00", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")))
        );
    }
}
