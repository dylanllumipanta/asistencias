package com.krakedev.controladores;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.modelo.Estudiante;
import com.krakedev.servicios.ServicioEstudiantes;

@RestController
@RequestMapping("/estudiantes")
public class ControladorEstudiantes {

    private final ServicioEstudiantes servicioEstudiantes;

    public ControladorEstudiantes(
            ServicioEstudiantes servicioEstudiantes) {
        this.servicioEstudiantes = servicioEstudiantes;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Estudiante estudiante) {

        try {
            servicioEstudiantes.agregar(estudiante);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(estudiante);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<ArrayList<Estudiante>> listar() {

        return ResponseEntity.ok(servicioEstudiantes.listar());
    }

    @GetMapping("/{cedula}")
    public ResponseEntity<?> buscar(
            @PathVariable String cedula) {

        Estudiante estudiante =
                servicioEstudiantes.buscarPorCedula(cedula);

        if (estudiante == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Estudiante no encontrado");
        }

        return ResponseEntity.ok(estudiante);
    }

    @PutMapping("/{cedula}")
    public ResponseEntity<?> actualizar(
            @PathVariable String cedula,
            @RequestBody Estudiante estudiante) {

        try {
            servicioEstudiantes.actualizar(cedula, estudiante);

            return ResponseEntity.ok(
                    servicioEstudiantes.buscarPorCedula(cedula));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{cedula}")
    public ResponseEntity<?> eliminar(
            @PathVariable String cedula) {

        try {
            servicioEstudiantes.eliminar(cedula);

            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }
}