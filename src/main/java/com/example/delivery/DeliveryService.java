package com.example.delivery;

import com.example.exception.DeliveryNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

  private final DeliveryRepository deliveryRepository;
  private final DeliveryMapper deliveryMapper;

  public List<DeliveryDTO> findAll() {
    return deliveryMapper.toDto(deliveryRepository.findAll());
  }

  public DeliveryDTO findById(Long id) {
      Delivery delivery = deliveryRepository.findById(id).orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));
      return deliveryMapper.toDto(delivery);
  }

  public DeliveryDTO save(DeliveryDTO deliveryDTO) {
      Delivery delivery = deliveryMapper.toEntity(deliveryDTO);
      return deliveryMapper.toDto(deliveryRepository.save(delivery));
  }

  public void deleteById(Long id) {
      Delivery delivery = deliveryRepository.findById(id).orElseThrow(() -> new DeliveryNotFoundException("Delivery not found"));
      deliveryRepository.deleteById(id);
  }
}
