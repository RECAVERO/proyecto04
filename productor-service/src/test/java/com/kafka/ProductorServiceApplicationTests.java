package com.kafka;

import com.kafka.application.rest.ClientController;
import com.kafka.btask.interfaces.ClientService;
import com.kafka.btask.services.ClientServiceImpl;
import com.kafka.domain.models.ClientDto;
import com.kafka.infraestructure.document.Client;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import  static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(ClientController.class)
class ProductorServiceApplicationTests {
	@Autowired
	private WebTestClient webTestClient;
	@MockBean
	private ClientServiceImpl service;


	@Test
	public void getProductsTest(){
		Flux<ClientDto> productDtoFlux=Flux.just(new ClientDto("62c8a457838c53796fb723c0","cli01","estefania","re@hotmail.com"),
				new ClientDto("62c86f9c49b25732bfe60fbd","cli02","reynaldo","reynaldo@hotmail.com"));
		when(service.findAllClient()).thenReturn(productDtoFlux);

		Flux<ClientDto> responseBody = webTestClient.get().uri("/client")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ClientDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNext(new ClientDto("62c8a457838c53796fb723c0","cli01","estefania","re@hotmail.com"))
				.expectNext(new ClientDto("62c86f9c49b25732bfe60fbd","cli02","reynaldo","reynaldo@hotmail.com"))
				.verifyComplete();

	}

	@Test
	public void addProductTest(){
		Mono<ClientDto> productDtoMono=Mono.just(new ClientDto("62c8a457838c53796fb723c0","cli01","estefania","re@hotmail.com"));
		when(service.saveClient(productDtoMono)).thenReturn(productDtoMono);

		webTestClient.post().uri("/client")
				.body(Mono.just(productDtoMono),ClientDto.class)
				.exchange()
				.expectStatus().isOk();//200

	}

	@Test
	public void getProductTest(){
		Mono<ClientDto> productDtoMono=Mono.just(new ClientDto("62c8a457838c53796fb723c0","cli01","estefania","re@hotmail.com"));
		when(service.findByIdClient(any())).thenReturn(productDtoMono);

		Flux<ClientDto> responseBody = webTestClient.get().uri("/client/62c8a457838c53796fb723c0")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ClientDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNextMatches(p->p.getId().equals("62c8a457838c53796fb723c0"))
				.verifyComplete();
	}

	@Test
	public void updateProductTest(){
		Mono<ClientDto> productDtoMono=Mono.just(new ClientDto("62c8a457838c53796fb723c0","cli01","estefania","re@hotmail.com"));
		when(service.updateClient(productDtoMono,"62c8a457838c53796fb723c0")).thenReturn(productDtoMono);

		webTestClient.put().uri("/client/62c8a457838c53796fb723c0")
				.body(Mono.just(productDtoMono),ClientDto.class)
				.exchange()
				.expectStatus().isOk();//200
	}

	@Test
	public void deleteProductTest(){
		Mono<ClientDto> productDtoMono=Mono.just(new ClientDto("62c8a457838c53796fb723c0","cli01","estefania","re@hotmail.com"));
		when(service.findByIdClient(any())).thenReturn(productDtoMono);

		Flux<ClientDto> responseBody = webTestClient.get().uri("/client/62c8a457838c53796fb723c0")
				.exchange()
				.expectStatus().isOk()
				.returnResult(ClientDto.class)
				.getResponseBody();

		StepVerifier.create(responseBody)
				.expectSubscription()
				.expectNextMatches(p->p.getId().equals("62c8a457838c53796fb723c0"))
				.verifyComplete();

		given(service.deleteByIdClient(any())).willReturn(Mono.empty());
		webTestClient.delete().uri("/client/62c8a457838c53796fb723c0")
				.exchange()
				.expectStatus().isOk();//200
	}

}
