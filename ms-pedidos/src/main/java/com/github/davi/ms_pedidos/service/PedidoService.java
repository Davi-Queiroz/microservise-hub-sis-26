package com.github.davi.ms_pedidos.service;


import com.github.davi.ms_pedidos.dto.PedidoDto;
import com.github.davi.ms_pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional(readOnly = true)
    public List<PedidoDto> findAllPedidos(){
        return pedidoRepository.findAll()

                .stream().map(PedidoDto::new).toList();   }

}
