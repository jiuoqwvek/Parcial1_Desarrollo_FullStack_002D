package com.duoc.quickOrder.service;

import com.duoc.quickOrder.model.quickOrder;
import com.duoc.quickOrder.repository.quickOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class quickOrderService {
    @Autowired
    public final quickOrderRepository repositorio;

    public quickOrderService (quickOrderRepository repositorio){
        this.repositorio = repositorio;
    }

    public List<quickOrder> mostrarPedidos(){
        return repositorio.findAll();
    }

    public quickOrder buscarPorId(){
        Optional<quickOrder> qck = repositorio.findAll();
        return qck.orElse(null);
    }
    public quickOrder guardarPedido(quickOrder pedido){
        return repositorio.save(pedido);
    }

    public quickOrder eliminarPedido(Long id){
        repositorio.deleteById(id);
    }
}
