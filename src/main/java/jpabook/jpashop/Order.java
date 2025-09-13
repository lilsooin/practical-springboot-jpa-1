package jpabook.jpashop;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private Long memberId;

    private Long deliveryId;

    private LocalDateTime orderDate;

    private Status status;
}
