package com.sprint.sensor_api.controller;

import com.sprint.sensor_api.dto.MedicaoResponseDTO;
import com.sprint.sensor_api.model.Medicao;
import com.sprint.sensor_api.service.MedicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicoes")
@CrossOrigin(origins = "*")
public class MedicaoController {

    @Autowired
    private MedicaoService service;

    @PostMapping
    public ResponseEntity<MedicaoResponseDTO> registrar(@RequestBody Medicao medicao) {
        try {
            MedicaoResponseDTO novaMedicao = service.registrarMedicao(medicao);
            return ResponseEntity.status(201).body(novaMedicao);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MedicaoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicaoResponseDTO> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sensor/{id}")
    public ResponseEntity<List<MedicaoResponseDTO>> buscarPorSensor(@PathVariable Long id) {
        List<MedicaoResponseDTO> medicoes = service.buscarPorSensor(id);
        if (medicoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicoes);
    }
}