package com.example.delivery;



import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TimeSlotDTO implements Serializable {


    private Long id;
    @JsonFormat(pattern = "HH:mm:ss")
    private String startTime;

    @JsonFormat(pattern = "HH:mm:ss")
    private String endTime;



}
