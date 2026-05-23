package com.sprint.sensor_api.dto;

import com.sprint.sensor_api.model.StatusMedicao;
import java.time.LocalDateTime;

public class MedicaoResponseDTO {

    private Long id;
    private Long sensorId;
    private String sensorNome;
    private Double valor;
    private LocalDateTime data;
    private StatusMedicao status;

    public MedicaoResponseDTO(Long id, Long sensorId, String sensorNome, Double valor, LocalDateTime data, StatusMedicao status) {
        this.id = id;
        this.sensorId = sensorId;
        this.sensorNome = sensorNome;
        this.valor = valor;
        this.data = data;
        this.status = status;
    }

    // Getters
    public Long getId() { return id; }
    public Long getSensorId() { return sensorId; }
    public String getSensorNome() { return sensorNome; }
    public Double getValor() { return valor; }
    public LocalDateTime getData() { return data; }
    public StatusMedicao getStatus() { return status; }
}