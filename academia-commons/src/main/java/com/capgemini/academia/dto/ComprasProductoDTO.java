package com.capgemini.academia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComprasProductoDTO {

    private Long id_compras_productos;

    private int no_articulo;

    private Long fk_id_producto;

    private Long fk_id_compra;

    private double precio;
}
