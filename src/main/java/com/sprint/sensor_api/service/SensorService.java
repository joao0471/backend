package com.sprint.sensor_api.service;

import com.sprint.sensor_api.model.Sensor;
import com.sprint.sensor_api.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository repository;

    // CREATE (Criar)
    public Sensor salvar(Sensor sensor) {
        return repository.save(sensor);
    }

    // READ (Buscar todos)
    public List<Sensor> listarTodos() {
        return repository.findAll();
    }

    // READ (Buscar por ID)
    public Optional<Sensor> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // UPDATE (Atualizar)
    public Sensor atualizar(Long id, Sensor sensorAtualizado) {
        if (repository.existsById(id)) {
            sensorAtualizado.setId(id);
            return repository.save(sensorAtualizado);
        }
        return null; // Se não achar o ID, retorna nulo
    }

    // DELETE (Deletar)
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}