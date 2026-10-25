package com.krakedev.servicios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.krakedev.modelo.Asistencia;
import com.krakedev.modelo.Estudiante;
import com.krakedev.modelo.RegistroAsistencia;

@Service
public class ServicioAsistencia {

    private final ArrayList<RegistroAsistencia> registros =
            new ArrayList<>();

    private final ServicioEstudiantes servicioEstudiantes;

    public ServicioAsistencia(
            ServicioEstudiantes servicioEstudiantes) {
        this.servicioEstudiantes = servicioEstudiantes;
    }

    public RegistroAsistencia registrarAsistencia(String cedula) {

        Estudiante estudiante =
                servicioEstudiantes.buscarPorCedula(cedula);

        if (estudiante == null) {
            return null;
        }

        Asistencia asistencia = new Asistencia(
                LocalDate.now(),
                LocalDateTime.now(),
                "P"
        );

        RegistroAsistencia registro =
                new RegistroAsistencia(estudiante, asistencia);

        registros.add(registro);

        return registro;
    }

    public ArrayList<Asistencia> consultarAsistencia(String cedula) {

        ArrayList<Asistencia> asistencias = new ArrayList<>();

        for (RegistroAsistencia registro : registros) {

            if (registro.getEstudiante().getCedula().equals(cedula)) {
                asistencias.add(registro.getAsistencia());
            }
        }

        return asistencias;
    }
}