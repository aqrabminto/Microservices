package com.pm.patientservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    public BillingServiceGrpcClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.grpc.port:9001}") int servicePort
    ) {
        log.info("Connnecting to billing service... {}:{}", serverAddress, servicePort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, servicePort).usePlaintext().build();
        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    public BillingResponse createBillingAccount(String patientId, String firstName, String email) {
        BillingRequest request = BillingRequest.newBuilder().setPatientId(patientId).setName(firstName).setEmail(email).build();
        BillingResponse response = blockingStub.createBillingAccount(request);
        log.info("Receiving response from billing service... {}", response);
        return response;
    }

}
