package com.carrefour.client;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClientDto implements Serializable {

  private Long id;
  private String firstname;
  private String lastname;
  private int age;

}
