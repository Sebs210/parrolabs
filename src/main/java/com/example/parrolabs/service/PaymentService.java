package com.example.parrolabs.service;

import com.example.parrolabs.Interface.PaymentTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentTypeRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    public Payment makePayment(PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setAmount(paymentRequest.getAmount());
        payment.setCardNumber(paymentRequest.getCardNumber());
        payment.setCardExpiration(paymentRequest.getCardExpiration());
        payment.setCvv(paymentRequest.getCvv());
        payment.setPaymentDate(new Date());
        payment.setPaymentStatus(PaymentStatus.PENDING);

        Order order = orderService.getOrderById(paymentRequest.getOrderId());
        payment.setOrder(order);

        Payment savedPayment = paymentRepository.save(payment);
        savedPayment.setPaymentStatus(PaymentStatus.COMPLETED);
        paymentRepository.save(savedPayment);

        return savedPayment;
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Payment not found with id " + id));
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

}