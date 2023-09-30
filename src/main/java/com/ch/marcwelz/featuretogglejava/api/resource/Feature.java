package com.ch.marcwelz.featuretogglejava.api.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Feature {

    private String name;
    private String comment;
    private boolean value;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;
}
