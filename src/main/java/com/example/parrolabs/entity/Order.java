package com.example.parrolabs.entity;


import com.example.parrolabs.dto.CustomerDto;
import com.example.parrolabs.dto.OrderDto;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends OrderDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_number", unique = true)
    private String orderNumber;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private ShippingAddress shippingAddress;
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "payment_type_id", nullable = false)
    private PaymentType paymentType;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }



    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Order(String orderNumber, LocalDate date, Customer customer, ShippingAddress shippingAddress, PaymentType paymentType, List<OrderItem> orderItems) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        this.paymentType = paymentType;
        this.orderItems = orderItems;
    }
    public OrderStatus getStatus() {
        return status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }


    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    // Method to add a new OrderItem to the order
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    // Method to calculate the total value of the order
    public BigDecimal calculateTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            BigDecimal price = BigDecimal.valueOf(item.getProduct().getPrice());
            BigDecimal quantity = BigDecimal.valueOf(item.getQuantity());
            total = total.add(price.multiply(quantity));
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", date=" + date +
                ", customer=" + customer +
                ", shippingAddress=" + shippingAddress +
                ", paymentType=" + paymentType +
                ", orderItems=" + orderItems +
                '}';
    }

    public void setTotalValue(BigDecimal totalValue) {
    }
}
