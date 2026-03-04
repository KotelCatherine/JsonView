package ru.itk.jsonview.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itk.jsonview.enums.Status;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

}
