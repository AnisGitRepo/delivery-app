package com.example.delivery;



import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class TimeSlotDTO implements Serializable {


    private Long id;
    private LocalTime startTime;
    private LocalTime endTime;



}
