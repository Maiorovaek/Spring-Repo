package com.example.SensorMeasurementsApp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class MeasurementDTO {
    @NotNull
    @Min(-100)
    @Max(100)
    private Double temperature;
    @NotNull
    private Boolean rainy;
    @NotNull
    private SensorDTO sensor;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Boolean getRainy() {
        return rainy;
    }

    public void setRainy(Boolean rainy) {
        this.rainy = rainy;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
