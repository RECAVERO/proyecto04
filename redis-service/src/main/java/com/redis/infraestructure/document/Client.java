package com.redis.infraestructure.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Document("Clients")
public class Client implements Serializable {
  @Id
  private String id;
  @NotEmpty
  private String idClient;
  @NotEmpty
  private String names;
  @NotEmpty
  private String email;
  private static final long serialVersionUID = 1L;
}
