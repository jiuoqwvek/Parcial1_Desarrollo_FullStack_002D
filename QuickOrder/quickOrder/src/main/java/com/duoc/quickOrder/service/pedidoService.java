package com.duoc.quickOrder.service;


import com.duoc.quickOrder.model.Estado;
import com.duoc.quickOrder.model.pedido;
import com.duoc.quickOrder.repository.pedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class pedidoService {
    @Autowired
    public final pedidoRepository repositorio;

    public pedidoService(pedidoRepository repositorio){
        this.repositorio = repositorio;
    }

    //Método para obtener todos los pedidos
    public List<pedido> mostrarPedidos(){
        // Retorna todos los registros de la base de datos
        return repositorio.findAll();
    }

    // Método para buscar un pedido por su ID
    public pedido buscarPorId(Long id){
        // findById retorna un Optional (puede o no tener valor)
        Optional<pedido> pedido = repositorio.findById(id);

        // Si existe lo devuelve, si no retorna null
        return pedido.orElse(null);
    }

    // Método para guardar un nuevo pedido
    public pedido guardarPedido(pedido nuevoPedido){
        // Asigna la fecha actual al pedido antes de guardarlo
        nuevoPedido.setFechaPedido(LocalDate.now());

        // Guarda el pedido en la base de datos
        return repositorio.save(nuevoPedido);
    }

    // Método para actualizar un pedido existente
    public pedido actualizarPedido(Long id, pedido pedidoActualizado){
        // Verifica si el pedido existe en la base de datos
        if(repositorio.existsById(id)){
            // Asigna el ID al objeto actualizado
            pedidoActualizado.setId(id);

            // Guarda los cambios (update)
            return repositorio.save(pedidoActualizado);
        }

        // Si no existe, retorna el mismo objeto sin cambios
        return pedidoActualizado;
    }

    // Método para eliminar un pedido por ID
    public pedido eliminarPedido(Long id) {
        // Elimina el pedido directamente por su ID
        repositorio.deleteById(id);

        // Retorna null (no se devuelve el objeto eliminado)
        return null;
    }

    // Método para filtrar pedidos por estado
    public List<pedido> filtrarPorEstado (Estado estado){
        // Llama a un método personalizado del repositorio
        return repositorio.findByEstado(estado);
    }
}