package com.capgemini.academia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCTO")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoItem implements Serializable {

    @Id
    @SequenceGenerator(name = "sequ", sequenceName = "producto_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "sequ")
    private Long id_producto;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "NUMERO_PIEZAS", nullable = false)
    private int no_piezas;

    @Column(name = "PRODUCTO_ACTIVO", nullable = false)
    private int producto_activo;

    @Column(name = "CODIGO", nullable = false)
    private String codigo;

    @Column(name = "PRECIO", nullable = false)
    private double precio;
}
