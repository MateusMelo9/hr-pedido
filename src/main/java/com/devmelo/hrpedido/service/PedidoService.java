package com.devmelo.hrpedido.service;

import com.devmelo.hrpedido.domain.Oficina;
import com.devmelo.hrpedido.domain.Pedido;
import com.devmelo.hrpedido.domain.PedidoRequest;
import com.devmelo.hrpedido.domain.User;
import com.devmelo.hrpedido.feignclients.OficinaFeignClient;
import com.devmelo.hrpedido.feignclients.UserFeignClient;
import com.devmelo.hrpedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private OficinaFeignClient oficinaFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido inserirPedido(PedidoRequest pedidoRequest){
        Oficina oficina = consultarOficina(pedidoRequest.getCnpjOficina());
        User user = consultarUsuario(pedidoRequest.getEmailUser());
        Pedido pedido = new Pedido();
        pedido.setData(new Date());
        pedido.setTipo(pedidoRequest.getTipoServico());
        pedido.setIdOficina(oficina.getId());
        pedido.setIdCliente(user.getId());
        return pedidoRepository.save(pedido);
    }

    public Oficina consultarOficina(Long cnpj){
        Oficina oficina = oficinaFeignClient.consultarOficinaCnpj(cnpj).getBody();
        return oficina;
    }

    public User consultarUsuario(String email){
        return userFeignClient.findUserByEmail(email).getBody();
    }
}
