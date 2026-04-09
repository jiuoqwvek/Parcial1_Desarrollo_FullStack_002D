package com.duoc.quickOrder.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class pedido {

    @Id // Define este atributo como la Llave Primaria (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el id va autoincremental
    private Long id;

    //valida que el nombre del cliente no este vacio
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombreCliente;

    //valida que el mensaje no este vacio
    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    // El notnull asegura que el campo siempre tenga un valor
    @NotNull(message = "El estado no puede ser nulo")
    @Enumerated(EnumType.STRING) // Guarda el nombre del Enum en la BD (ej: "PENDIENTE") en lugar de un número
    private Estado estado;

    @NotNull(message = "El Tipo del Pedido no puede ser nulo")
    @Enumerated(EnumType.STRING)
    private TipoPedido tipoPedido;

    @NotNull(message = "El monto total no puede estar vacío")
    @Positive(message = "El monto total debe ser mayor a cero") // Validación que evita números negativos o cero
    private Double montoTotal;

    @NotNull(message = "La fecha del pedido es obligatoria")
    private LocalDate fechaPedido;
}