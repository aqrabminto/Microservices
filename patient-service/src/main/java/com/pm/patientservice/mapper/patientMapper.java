package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.patientRequestDTO;
import com.pm.patientservice.dto.patientResponseDTO;
import com.pm.patientservice.model.patient;

import java.time.LocalDate;

public class patientMapper {
    public static patientResponseDTO toDTO(patient patient) {
        patientResponseDTO patientResponseDTO = new patientResponseDTO();
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setAddress(patient.getAddress())
        ;
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        patientResponseDTO.setEmail(patient.getEmail());
        return patientResponseDTO;
    }

    public static patient toModel(patientRequestDTO patientRequestDTO) {
        patient patient = new patient();
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patient;

    }
}
