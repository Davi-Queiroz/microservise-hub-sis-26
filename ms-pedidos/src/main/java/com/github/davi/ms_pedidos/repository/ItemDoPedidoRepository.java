package com.github.davi.ms_pedidos.repository;

import com.github.davi.ms_pedidos.entities.ItemDoPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDoPedidoRepository extends JpaRepository<ItemDoPedido,Long> {
}
