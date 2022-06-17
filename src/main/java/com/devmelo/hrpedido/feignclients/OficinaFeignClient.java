package com.devmelo.hrpedido.feignclients;

import com.devmelo.hrpedido.domain.Oficina;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "hr-oficina", url = "localhost:59652", path = "/oficinas")
public interface OficinaFeignClient {

    @GetMapping(value = "/{cnpj}")
    public ResponseEntity<Oficina> consultarOficinaCnpj(@PathVariable Long cnpj);
}
