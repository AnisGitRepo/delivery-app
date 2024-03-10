package com.example.client;

import com.example.exception.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;

  public List<ClientDto> findAll() {
    return clientMapper.toDto(clientRepository.findAll());
  }

  public ClientDto findById(Long id) {
      Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));
      return clientMapper.toDto(client);
  }

  public ClientDto save(ClientDto clientDto) {
      Client client = clientMapper.toEntity(clientDto);
      return clientMapper.toDto(clientRepository.save(client));
  }

  public void deleteById(Long id) {
      Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));
      clientRepository.deleteById(id);  }
}
