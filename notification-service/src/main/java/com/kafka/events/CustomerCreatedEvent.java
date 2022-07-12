package com.kafka.events;

import com.kafka.entity.ClientDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerCreatedEvent extends Event<ClientDto> {

}
