package com.duoc.quickOrder.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NotBlank
@AllArgsConstructor
@NoArgsConstructor
@NotNull
@Entity

public class quickOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "El ID no puede ser nulo")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombreCliente;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotNull(message = "El estado no puede ser nulo")
    private Estado estado;

    @NotNull(message = "El Tipo del Pedido no puede ser nulo")
    private TipoPedido tipoPedido;

    @NotBlank(message = "El monto total no puede estar vacio")
    private Double montoTotal;

    @NotNull(message = "La fecha del Pedido no puede ser nulo")
    private LocalDate fechaPedido;
}
