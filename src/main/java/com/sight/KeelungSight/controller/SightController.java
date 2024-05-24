package com.sight.KeelungSight.controller;

import com.sight.KeelungSight.entity.Sight;
import com.sight.KeelungSight.parameter.SightQueryParameter;
import com.sight.KeelungSight.service.SightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Sight", description = "The Sight API")
@RestController
@RequestMapping(value = "/SightAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class SightController {
    @Autowired
    private SightService sightice;

    @Operation(summary = "#api_S1", description = "Get a list of sights")
    @GetMapping
    public ResponseEntity<List<Sight>> getSights(@ModelAttribute @Parameter(description = "zone") SightQueryParameter param) {
        List<Sight> sights = sightice.getSights(param);
        return ResponseEntity.ok(sights);
    }
}
