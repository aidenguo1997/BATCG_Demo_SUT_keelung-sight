package com.sight.KeelungSight.service;

import com.sight.KeelungSight.entity.Sight;
import com.sight.KeelungSight.exception.NotFoundException;
import com.sight.KeelungSight.parameter.SightQueryParameter;
import com.sight.KeelungSight.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SightService {
    @Autowired
    private SightRepository repository;

    public List<Sight> getSights(SightQueryParameter param) {
        String zone = Optional.ofNullable(param.getZone()).orElse("");
        boolean isNotFound = repository.findByZoneLike(zone).isEmpty();
        if (isNotFound) {
            throw new NotFoundException("Can't find sight.");
        }
        return repository.findByZoneLike(zone);
    }
}
