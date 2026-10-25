package com.krakedev.servicios;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.krakedev.modelo.Estudiante;

@Service
public class ServicioEstudiantes {

    private final ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public void agregar(Estudiante estudiante) {

        if (estudiante == null || estudiante.getCedula() == null
                || estudiante.getCedula().isBlank()) {
            throw new IllegalArgumentException(
                    "La cedula del estudiante es obligatoria");
        }

        if (buscarPorCedula(estudiante.getCedula()) != null) {
            throw new IllegalArgumentException(
                    "Ya existe un estudiante con esa cedula");
        }

        estudiantes.add(estudiante);
    }

    public Estudiante buscarPorCedula(String cedula) {

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                return estudiante;
            }
        }

        return null;
    }

    public void eliminar(String cedula) {

        Estudiante estudiante = buscarPorCedula(cedula);

        if (estudiante == null) {
            throw new IllegalArgumentException(
                    "No existe un estudiante con esa cedula");
        }

        estudiantes.remove(estudiante);
    }

    public void actualizar(String cedula, Estudiante nuevo) {

        Estudiante actual = buscarPorCedula(cedula);

        if (actual == null) {
            throw new IllegalArgumentException(
                    "No existe un estudiante con esa cedula");
        }

        if (nuevo == null) {
            throw new IllegalArgumentException(
                    "Los datos del estudiante son obligatorios");
        }

        actual.setNombre(nuevo.getNombre());
        actual.setApellido(nuevo.getApellido());
    }

    public ArrayList<Estudiante> listar() {
        return estudiantes;
    }
}