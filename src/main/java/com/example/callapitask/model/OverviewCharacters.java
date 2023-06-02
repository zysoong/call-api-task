package com.example.callapitask.model;

import java.util.List;

public record OverviewCharacters (
        InfoCharacters info,
        List<Object> result
) {
}
