package com.github.davi.ms_pedidos.Controller;

import com.github.davi.ms_pedidos.dto.PedidoDto;
import com.github.davi.ms_pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDto>> getAllProduto(){
        List<PedidoDto> list= pedidoService.findAllPedidos();
        return ResponseEntity.ok(list);
    }
}
