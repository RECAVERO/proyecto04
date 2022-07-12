package com.kafka;

import com.kafka.application.rest.ClientJococoController;
import com.kafka.btask.interfaces.ClientService;
import com.kafka.btask.services.ClientServiceImpl;
import com.kafka.domain.contract.ClientRepository;
import com.kafka.domain.models.ClientDto;
import com.kafka.domain.models.ResponseDto;
import com.kafka.infraestructure.mongodb.ClientRepositoryMongodb;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

public class ProductorJacocoServiceApplicationTest {
  @Autowired
  ClientService clientService;
  @Test
  public void testUpdateJacocoOk(){
    ClientJococoController clientJococoController=new ClientJococoController(clientService);
    ClientDto clientDto=new ClientDto("62c8a457838c53796fb723c0","cli01","estefania","re@hotmail.com");
    ResponseDto responseDto=new ResponseDto();
    responseDto.setStatus(HttpStatus.OK.toString());
    responseDto.setMessage("Se Actualizo el client");
    responseDto.setClient(clientDto);
    Mono<ResponseDto> responseDtoMono= Mono.just(responseDto);
    Mono<ClientDto> clientDtoMono=Mono.just(clientDto);

    Assertions.assertEquals(responseDto,clientJococoController.updateJococoClient(clientDto,"62c8a457838c53796fb723c0"));

  }

  @Test
  public void testUpdateJacocoEmpty(){
    ClientJococoController clientJococoController=new ClientJococoController(clientService);
    ClientDto clientDto=new ClientDto();
    ResponseDto responseDto=new ResponseDto();
    responseDto.setStatus(HttpStatus.NOT_FOUND.toString());
    responseDto.setMessage("No se Existe el client");
    Mono<ResponseDto> responseDtoMono= Mono.just(responseDto);
    Mono<ClientDto> clientDtoMono=Mono.just(clientDto);

    Assertions.assertEquals(responseDto,clientJococoController.updateJococoClient(clientDto,""));

  }

  @Test
  public void testUpdateJacocoNull(){
    ClientJococoController clientJococoController=new ClientJococoController(clientService);
    ClientDto clientDto=new ClientDto();
    ResponseDto responseDto=new ResponseDto();
    responseDto.setStatus(HttpStatus.NOT_FOUND.toString());
    responseDto.setMessage("No se Existe el client");
    Mono<ResponseDto> responseDtoMono= Mono.just(responseDto);
    Mono<ClientDto> clientDtoMono=Mono.just(clientDto);

    Assertions.assertEquals(responseDto,clientJococoController.updateJococoClient(clientDto,null));

  }
}
