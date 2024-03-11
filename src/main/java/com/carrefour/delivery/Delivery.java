package com.carrefour.delivery;

import com.carrefour.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "delivery", schema = "public")
public class Delivery {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    @Enumerated(EnumType.STRING)
    private DeliveryMode mode;
    private LocalDate deliveryDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private TimeSlot timeSlot;
    @ManyToOne
    private Client client;



}
