package com.duoc.quickOrder.repository;

import com.duoc.quickOrder.model.quickOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface quickOrderRepository extends JpaRepository<quickOrder, Long>{
}
