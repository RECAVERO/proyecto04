package com.kafka.application.rest;

import com.kafka.btask.interfaces.ClientService;
import com.kafka.domain.models.ClientDto;
import com.kafka.domain.models.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
public class ClientJococoController {
  private final ClientService clientService;

  public ClientJococoController( ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping
  public Flux<ClientDto> getListClient() {

    return this.clientService.findAllClient();

  }

  @PostMapping
  public Mono<ClientDto> addClient(@RequestBody Mono<ClientDto> clientDto) {
    return this.clientService.saveClient(clientDto);
  }

  @PutMapping("/jococo/{id}")
  public ResponseDto updateJococoClient(@RequestBody ClientDto clientDto, @PathVariable String id){
    ResponseDto responseDto=new ResponseDto();

      if(id==null || id.equals("")){
        responseDto.setStatus(HttpStatus.NOT_FOUND.toString());
        responseDto.setMessage("No se Existe el client");

        return responseDto;
      }else{

        //return this.clientService.updateClient(clientDto, id).flatMap(p->{
        responseDto.setStatus(HttpStatus.OK.toString());
        responseDto.setMessage("Se Actualizo el client");
        responseDto.setClient(clientDto);
        return responseDto;
        //});
      }


  }

  @PutMapping("/{id}")
  public Mono<ResponseDto> updateClient(@RequestBody Mono<ClientDto> clientDto, @PathVariable String id){
    ResponseDto responseDto=new ResponseDto();
    return this.clientService.updateClient(clientDto, id).flatMap(client->{
      if(client.getId()==null || client.getId().equals("")){
        responseDto.setStatus(HttpStatus.NOT_FOUND.toString());
        responseDto.setMessage("No se Existe el client");

        return Mono.just(responseDto);
      }else{

        //return this.clientService.updateClient(clientDto, id).flatMap(p->{
          responseDto.setStatus(HttpStatus.OK.toString());
          responseDto.setMessage("Se Actualizo el client");
          responseDto.setClient(client);
          return Mono.just(responseDto);
        //});
      }
    });

  }

  @GetMapping("/{id}")
  public Mono<ClientDto> searchClientById(@PathVariable String id){
    return this.clientService.findByIdClient(id);
  }

  @DeleteMapping("/{id}")
  public Mono<Void> deleteClientById(@PathVariable String id){
    return this.clientService.deleteByIdClient(id);
  }
}
