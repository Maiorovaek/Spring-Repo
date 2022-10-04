package com.example.SensorMeasurementsApp.services;

import com.example.SensorMeasurementsApp.models.Measurement;
import com.example.SensorMeasurementsApp.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository,
                              SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }


    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setMeasurementDateTime(LocalDateTime.now());
        String name = measurement.getSensor().getName();
        measurement.setSensor(sensorService.findByName(name).get());
        measurementRepository.save(measurement);
    }
}
