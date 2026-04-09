package com.duoc.quickOrder.controller;

import com.duoc.quickOrder.model.Estado;
import com.duoc.quickOrder.model.pedido;
import com.duoc.quickOrder.service.pedidoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Indica que esta clase es un controlador REST
@RestController

// Define la ruta base para todos los endpoints de este controlador
@RequestMapping("/api/v1/QuickOrder")
public class pedidoController {
    private final pedidoService servicio;

    public pedidoController(pedidoService servicio){
        this.servicio = servicio;
    }

    // Endpoint GET para obtener todos los pedidos
    @GetMapping("/pedidos")
    public ResponseEntity<?> mostrarPedidos(){
        try{
            // Llama al servicio para obtener la lista de pedidos
            List<pedido> pedidos = servicio.mostrarPedidos();
            return ResponseEntity.ok(pedidos); // Retorna 200 OK con la lista
        } catch (Exception e){
            // Manejo de error en caso de falla interna
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener los pedidos: " + e.getMessage());
        }
    }

    // Endpoint GET para buscar un pedido por su ID
    @GetMapping("/pedidos/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        try{
            // Busca el pedido en el servicio
            pedido pedidoEncontrado = servicio.buscarPorId(id);

            // Verifica si existe
            if (pedidoEncontrado != null) {
                return ResponseEntity.ok(pedidoEncontrado); // 200 OK
            } else {
                // Retorna 404 si no se encuentra
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Pedido no encontrado con ID: " + id);
            }
        } catch (Exception e){
            // Manejo de errores generales
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar el pedido: " + e.getMessage());
        }
    }

    // Endpoint POST para crear un nuevo pedido
    @PostMapping("/pedidos")
    public ResponseEntity<?> guardarPedido(@Valid @RequestBody pedido nuevoPedido){
        try {
            // Guarda el pedido utilizando el servicio
            pedido pedidoGuardado = servicio.guardarPedido(nuevoPedido);

            // Retorna 201 CREATED con el objeto creado
            return ResponseEntity.status(HttpStatus.CREATED).body(pedidoGuardado);
        } catch (Exception e) {
            // Manejo de errores por datos inválidos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Datos inválidos: " + e.getMessage());
        }
    }

    // Endpoint PUT para actualizar un pedido existente
    @PutMapping("/pedidos/{id}")
    public ResponseEntity<?> actualizarPedido(@PathVariable Long id, @RequestBody pedido pedidoActualizado){
        try{
            // Verifica si el pedido existe
            pedido pedidoExistente = servicio.buscarPorId(id);

            if (pedidoExistente != null) {
                // Actualiza el pedido
                pedido pedidoActualizadoGuardado = servicio.actualizarPedido(id, pedidoActualizado);
                return ResponseEntity.ok(pedidoActualizadoGuardado); // 200 OK
            } else {
                // Retorna 404 si no existe
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Pedido no encontrado con ID: " + id);
            }
        } catch (Exception e){
            // Manejo de errores internos
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el pedido: " + e.getMessage());
        }
    }

    // Endpoint DELETE para eliminar un pedido por ID
    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
        try {
            // Verifica si el pedido existe
            pedido pedidoExistente = servicio.buscarPorId(id);

            if (pedidoExistente != null) {
                // Elimina el pedido
                servicio.eliminarPedido(id);

                // Retorna 204 NO CONTENT (sin contenido)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                // Retorna 404 si no existe
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Pedido no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            // Manejo de errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar: " + e.getMessage());
        }
    }

    // Endpoint GET para filtrar pedidos por estado
    @GetMapping("/pedidos/estado/{estado}")
    public ResponseEntity<?> filtrarPorEstado(@PathVariable Estado estado) {
        try{
            // Obtiene lista de pedidos filtrados por estado
            List<pedido> pedidosFiltrados = servicio.filtrarPorEstado(estado);
            return ResponseEntity.ok(pedidosFiltrados); // 200 OK
        } catch (Exception e){
            // Manejo de errores
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al filtrar los pedidos por estado: " + e.getMessage());
        }
    }
}