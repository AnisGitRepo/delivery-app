package com.carrefour.delivery;

import com.carrefour.client.ClientMapper;
import com.carrefour.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, TimeSlotMapper.class})
public interface DeliveryMapper extends EntityMapper<DeliveryDTO, Delivery> {
}
