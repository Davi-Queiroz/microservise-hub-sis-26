package com.github.davi.ms_pedidos.dto;

import com.github.davi.ms_pedidos.entities.ItemDoPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ItemDoPedidoDto {
    private Long id;

    @NotNull(message = "quantidade requerido")
    @Positive(message = "quantidade deve ser um numeor positivo")
    private Integer quantidade;

    @NotBlank(message = "Descrição requerido")
    private String descricao;

    @NotNull(message = "peço unitario requerido")
    @Positive(message = "O preço unitario deve ser um valor possitivo")
    private BigDecimal precoUnitario;

    public ItemDoPedidoDto(ItemDoPedido itemPedido){
        id=itemPedido.getId();
        quantidade= itemPedido.getQuantidade();
        descricao= itemPedido.getDescricao();
        precoUnitario=itemPedido.getPrecoUnitario();
    }
}
