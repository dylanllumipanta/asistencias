package com.krakedev.controladores;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.modelo.Asistencia;
import com.krakedev.modelo.RegistroAsistencia;
import com.krakedev.servicios.ServicioAsistencia;

@RestController
@RequestMapping("/asistencias")
public class ControladorAsistencias {

    private final ServicioAsistencia servicioAsistencia;

    public ControladorAsistencias(
            ServicioAsistencia servicioAsistencia) {
        this.servicioAsistencia = servicioAsistencia;
    }

    @PostMapping("/{cedula}")
    public ResponseEntity<?> registrar(
            @PathVariable String cedula) {

        RegistroAsistencia registro =
                servicioAsistencia.registrarAsistencia(cedula);

        if (registro == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un estudiante con esa cedula");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(registro);
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<ArrayList<Asistencia>> consultar(
            @PathVariable String cedula) {

        ArrayList<Asistencia> asistencias =
                servicioAsistencia.consultarAsistencia(cedula);

        return ResponseEntity.ok(asistencias);
    }
}