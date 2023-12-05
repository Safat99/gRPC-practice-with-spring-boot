package com.safatPay.grpcpracticeorderservice.service;

import com.example.orderService.OrderOuterClass.OrderStatusRequest;
import com.example.orderService.OrderOuterClass.OrderStatusUpdate;
import com.example.orderService.OrderOuterClass.OrderResponse;
import com.example.orderService.OrderOuterClass.Order;
import com.example.orderService.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public void placeOrder(Order request, StreamObserver<OrderResponse> responseObserver) {

        OrderResponse response = OrderResponse.newBuilder()
                .setStatus("created")
                .build();

        logger.info("placeOrder grpc-api invoked");

        // sending the response back to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void streamOrders(OrderStatusRequest request, StreamObserver<OrderStatusUpdate> responseObserver) {

        List<String> orderIds = request.getOrderIdsList();
        List<OrderStatusUpdate> orderStatusUpdates = getOrderStatusUpdate(orderIds);

        try {
            for (OrderStatusUpdate update: orderStatusUpdates) {
                responseObserver.onNext(update);
                Thread.sleep(1000); // optional delay
            }
        } catch (InterruptedException e) {
//        } catch (Exception e) {
            e.printStackTrace();
            logger.error("streamOrders api catch block invoked");
        } finally {
            responseObserver.onCompleted();
        }
    }

    /*
    private method,
    will fetch data from db later
     */
    private List<OrderStatusUpdate> getOrderStatusUpdate(List<String> orderIds) {

        List<OrderStatusUpdate> orderStatusUpdateList = new ArrayList<>();
        for (String orderId: orderIds) {
            OrderStatusUpdate order = OrderStatusUpdate.newBuilder()
                    .setOrderId(orderId)
                    .setProductName("orderName: " + orderId)
                    .setStatus("static: shipped")
                    .build();
            orderStatusUpdateList.add(order);
        }
        return orderStatusUpdateList;
    }

}
