package com.example.delivery;


import com.example.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeSlotMapper extends EntityMapper<TimeSlotDTO, TimeSlot> {
}
