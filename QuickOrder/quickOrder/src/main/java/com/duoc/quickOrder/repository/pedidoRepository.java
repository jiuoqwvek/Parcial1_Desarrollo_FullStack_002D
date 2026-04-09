package com.duoc.quickOrder.repository;
import com.duoc.quickOrder.model.pedido;
import com.duoc.quickOrder.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface pedidoRepository extends JpaRepository<pedido, Long>{

    //**Método personalizado para buscar pedidos por estado
    List<pedido> findByEstado(Estado estado);
}