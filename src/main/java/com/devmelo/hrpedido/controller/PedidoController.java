package com.devmelo.hrpedido.controller;

import com.devmelo.hrpedido.domain.Oficina;
import com.devmelo.hrpedido.domain.Pedido;
import com.devmelo.hrpedido.domain.PedidoRequest;
import com.devmelo.hrpedido.repository.PedidoRepository;
import com.devmelo.hrpedido.service.PedidoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<List<Pedido>> findAll(){
        List<Pedido> list = repository.findAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id){
        Pedido pedido = repository.findById(id).get();
        return ResponseEntity.ok(pedido);
    }

    @HystrixCommand(fallbackMethod = "inserirPedidoAlternative")
    @PostMapping(value = "/criar")
    public ResponseEntity<Pedido> inserirPedido(@RequestBody PedidoRequest pedido){
        Pedido response = service.inserirPedido(pedido);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Pedido> inserirPedidoAlternative(Long oficinaId){
        Pedido pedido = new Pedido(3L,"Oficina Default",new Date(),2L,3L);
        return ResponseEntity.ok(pedido);
    }
}
