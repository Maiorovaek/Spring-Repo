package com.example.SensorMeasurementsApp.controllers;

import com.example.SensorMeasurementsApp.models.Measurement;
import com.example.SensorMeasurementsApp.services.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @GetMapping
    private List<Measurement> getMeasurements() {
        return measurementService.findAll();
    }

    @GetMapping("/rainyDaysCount")
    private Long getRainyDaysCount() {
        return measurementService.findAll().stream().filter(Measurement::isRainy).count();
    }

    @PostMapping("/add")
    private ResponseEntity<HttpStatus> add(@RequestBody Measurement measurement) {
        measurementService.save(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
