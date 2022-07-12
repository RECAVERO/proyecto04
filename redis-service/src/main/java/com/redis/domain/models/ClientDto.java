package com.redis.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto implements Serializable {
  private String id;
  private String idClient;
  private String names;
  private String email;

  private static final long serialVersionUID = 1L;
}
