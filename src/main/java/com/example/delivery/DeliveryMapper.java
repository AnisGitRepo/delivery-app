package com.example.delivery;

import com.example.client.ClientMapper;
import com.example.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, TimeSlotMapper.class})
public interface DeliveryMapper extends EntityMapper<DeliveryDTO, Delivery> {
}
