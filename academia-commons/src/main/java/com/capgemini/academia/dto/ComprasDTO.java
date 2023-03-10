package com.capgemini.academia.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComprasDTO {

    private Long id_compra;

    private Date fecha_compra;

    private String descripcion;

    private Long fk_id_cliente;

    private double total;

    private Long fk_id_direccion;

    private List<ComprasProductoDTO> lstComprasProductos;
}
