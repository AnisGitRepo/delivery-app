package com.example.delivery;


import com.example.client.ClientDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class DeliveryDTO implements Serializable {


    private Long id;
    private String description;
    private String mode;
    private LocalDate deliveryDate;
    private TimeSlotDTO timeSlot;
    private ClientDto client;



}
