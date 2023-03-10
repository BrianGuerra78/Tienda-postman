package com.capgemini.academia.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long id_producto;

    private String nombre;

    private String descripcion;

    private int no_piezas;

    private int producto_activo;

    private String codigo;

    private double precio;

    // private List<Module> lstProductos;
}