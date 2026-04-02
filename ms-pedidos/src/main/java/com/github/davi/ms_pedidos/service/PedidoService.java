package com.github.davi.ms_pedidos.service;


import com.github.davi.ms_pedidos.dto.ItemDoPedidoDto;
import com.github.davi.ms_pedidos.dto.PedidoDto;
import com.github.davi.ms_pedidos.entities.ItemDoPedido;
import com.github.davi.ms_pedidos.entities.Pedido;
import com.github.davi.ms_pedidos.entities.Status;
import com.github.davi.ms_pedidos.exceptions.ResourceNotFoundException;
import com.github.davi.ms_pedidos.repository.ItemDoPedidoRepository;
import com.github.davi.ms_pedidos.repository.PedidoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemDoPedidoRepository itemDoPedidoRepository;
    
    @Transactional(readOnly = true)
    public List<PedidoDto> findAllPedidos(){
        return pedidoRepository.findAll()

                .stream().map(PedidoDto::new).toList();   }

    @Transactional(readOnly = true)
    public PedidoDto findPedidoById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id));

        return new PedidoDto(pedido);
    };

    @Transactional
    public PedidoDto savePedido(PedidoDto pedidoDto){
        Pedido pedido=new Pedido();
        pedido.setData(LocalDate.now());
        pedido.setStatus(Status.CRIADO);
        mapDtoToPedido(pedidoDto,pedido);
        pedido.calcularValorTotalDoProduto();
        pedido=pedidoRepository.save(pedido);
        return new PedidoDto(pedido);
    }

    private void mapDtoToPedido(PedidoDto pedidoDto, Pedido pedido) {
        pedido.setNome(pedidoDto.getNome());
        pedido.setCpf(pedidoDto.getCpf());

        for (ItemDoPedidoDto itemDTO : pedidoDto.getItens()){
            ItemDoPedido itemDoPedido=new ItemDoPedido();
            itemDoPedido.setQuantidade((itemDTO.getQuantidade()));
            itemDoPedido.setDescricao(itemDTO.getDescricao());
            itemDoPedido.setPrecoUnitario(itemDTO.getPrecoUnitario());
            itemDoPedido.setPedido(pedido);
            pedido.getItens().add(itemDoPedido);

        }
    }

    @Transactional
    public PedidoDto updatePedido(Long id, PedidoDto pedidoDTO) {

        try {
            Pedido pedido = pedidoRepository.getReferenceById(id);
            pedido.getItens().clear();
            pedido.setData(LocalDate.now());
            pedido.setStatus(Status.CRIADO);
            mapDtoToPedido(pedidoDTO, pedido);
            pedido.calcularValorTotalDoProduto();
            pedido = pedidoRepository.save(pedido);
            return new PedidoDto(pedido);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional
    public void deletePedidoById(Long id){
        if (!pedidoRepository.existsById(id)){
        throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
    }
    pedidoRepository.deleteById(id);
    }

}
