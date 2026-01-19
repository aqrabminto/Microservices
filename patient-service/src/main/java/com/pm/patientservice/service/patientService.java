package com.pm.patientservice.service;

import com.pm.patientservice.dto.patientRequestDTO;
import com.pm.patientservice.dto.patientResponseDTO;
import com.pm.patientservice.exception.emailAlreadyExistsException;
import com.pm.patientservice.exception.patientNotFoundException;
import com.pm.patientservice.grpc.BillingServiceGrpcClient;
//import com.pm.patientservice.kafka.KafkaProducer;
import com.pm.patientservice.kafka.KafkaProducer;
import com.pm.patientservice.mapper.patientMapper;
import com.pm.patientservice.model.patient;
import com.pm.patientservice.repository.patientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class patientService {
    private final patientRepository patientRepository;
    private final BillingServiceGrpcClient billingServiceGrpcClient;
    private final KafkaProducer kafkaProducer;

    patientService(patientRepository patientRepository,
                   BillingServiceGrpcClient billingServiceGrpcClient
            , KafkaProducer kafkaProducer
    ) {
        this.patientRepository = patientRepository;
        this.billingServiceGrpcClient = billingServiceGrpcClient;
        this.kafkaProducer = kafkaProducer;
    }

    public List<patientResponseDTO> getPatients() {
        List<patient> patients = patientRepository.findAll();
        List<patientResponseDTO> patientResponseDTOs = patients.stream().map(patient -> patientMapper.toDTO(patient)).toList();
        return patientResponseDTOs;
    }

    public patientResponseDTO createPatient(patientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new emailAlreadyExistsException("A patient With this email already exists " + patientRequestDTO.getEmail());
        }
        patient newPatient = patientRepository.save(patientMapper.toModel(patientRequestDTO));
        billingServiceGrpcClient.createBillingAccount(newPatient.getId().toString(), newPatient.getName(), newPatient.getEmail());
        kafkaProducer.sendEvent(newPatient);
        return patientMapper.toDTO(newPatient);
    }

    public patientResponseDTO updatePatient(UUID id, patientRequestDTO patientRequestDTO) {
        patient patient = patientRepository.findById(id).orElseThrow(() -> new patientNotFoundException("Patient not found with ID:" + id));
        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new emailAlreadyExistsException("A patient With this email already exists " + patientRequestDTO.getEmail());
        }
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        patient updatedPatient = patientRepository.save(patient);
        return patientMapper.toDTO(updatedPatient);

    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }


}
