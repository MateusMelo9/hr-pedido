package com.devmelo.hrpedido.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Oficina {

    private Long id;
    private String nome;
    private String endereco;
    private Integer cep;
    private Integer telefone;

}
