package com.sprint.sensor_api.controller;

import com.sprint.sensor_api.model.Sensor;
import com.sprint.sensor_api.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sensores")
public class SensorController {

    @Autowired
    private SensorService service;

    // Rota para CRIAR um sensor (POST)
    @PostMapping
    public ResponseEntity<Sensor> criar(@RequestBody Sensor sensor) {
        Sensor novoSensor = service.salvar(sensor);
        return ResponseEntity.status(201).body(novoSensor);
    }

    // Rota para LISTAR todos os sensores (GET)
    @GetMapping
    public ResponseEntity<List<Sensor>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // Rota para BUSCAR um sensor pelo ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> buscarPorId(@PathVariable Long id) {
        Optional<Sensor> sensor = service.buscarPorId(id);
        return sensor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Rota para ATUALIZAR um sensor existente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Sensor> atualizar(@PathVariable Long id, @RequestBody Sensor sensor) {
        Sensor atualizado = service.atualizar(id, sensor);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // Rota para DELETAR um sensor (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}