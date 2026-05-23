package com.sprint.sensor_api.service;

import com.sprint.sensor_api.dto.MedicaoResponseDTO;
import com.sprint.sensor_api.model.Medicao;
import com.sprint.sensor_api.model.Sensor;
import com.sprint.sensor_api.model.StatusMedicao;
import com.sprint.sensor_api.repository.MedicaoRepository;
import com.sprint.sensor_api.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicaoService {

    @Autowired
    private MedicaoRepository medicaoRepository;

    @Autowired
    private SensorRepository sensorRepository;

    public MedicaoResponseDTO registrarMedicao(Medicao medicao) {
        // Verifica se o sensor existe no banco
        Sensor sensor = sensorRepository.findById(medicao.getSensor().getId())
                .orElseThrow(() -> new RuntimeException("Sensor não encontrado"));

        medicao.setSensor(sensor);
        Medicao salva = medicaoRepository.save(medicao);

        return converterParaDTO(salva);
    }

    public List<MedicaoResponseDTO> listarTodas() {
        return medicaoRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public MedicaoResponseDTO buscarPorId(Long id) {
        Medicao medicao = medicaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Medição não encontrada"));
        return converterParaDTO(medicao);
    }

    public List<MedicaoResponseDTO> buscarPorSensor(Long sensorId) {
        return medicaoRepository.findBySensorId(sensorId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    // --- MÉTODOS PRIVADOS PARA LÓGICA DE NEGÓCIO ---

    private MedicaoResponseDTO converterParaDTO(Medicao medicao) {
        StatusMedicao statusCalculado = calcularStatus(medicao.getValor(), medicao.getSensor());

        return new MedicaoResponseDTO(
                medicao.getId(),
                medicao.getSensor().getId(),
                medicao.getSensor().getNome(),
                medicao.getValor(),
                medicao.getData(),
                statusCalculado
        );
    }

    private StatusMedicao calcularStatus(Double valor, Sensor sensor) {
        Double min = sensor.getLimiteMinimo();
        Double max = sensor.getLimiteMaximo();

        // Se estiver fora do limite absoluto
        if (valor < min || valor > max) {
            return StatusMedicao.CRITICO;
        }

        // Lógica de alerta: Considera 10% de margem próxima aos limites.
        // Modifique este multiplicador (0.10) se em aula foi passada uma fórmula diferente.
        double margem = (max - min) * 0.10;

        if (valor <= (min + margem) || valor >= (max - margem)) {
            return StatusMedicao.ALERTA;
        }

        return StatusMedicao.NORMAL;
    }
}