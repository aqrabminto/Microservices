package com.pm.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@GrpcService
public class billingGrpcService  extends BillingServiceImplBase{

    private static final Logger log = LoggerFactory.getLogger(billingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest BillingRequest,
                                     StreamObserver<BillingResponse> responseObserver) {
        log.info("CreateBillingAccount request received {}",BillingRequest.toString());

        //business logic - save to database, perform calculate etc
        BillingResponse response =BillingResponse.newBuilder().setAccountId("12").setStatus("OK").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }

}
