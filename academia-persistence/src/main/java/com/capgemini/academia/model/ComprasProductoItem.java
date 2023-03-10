package com.capgemini.academia.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Compras_Productos")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComprasProductoItem implements Serializable {
    @Id
    @SequenceGenerator(name = "seqComPro", sequenceName = "compras_productos_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "seqComPro")
    private Long id_compras_productos;

    @Column(name = "NUMERO_ARTICULOS", nullable = false)
    private int no_articulos;

    @ManyToOne
    @JoinColumn(name = "FK_ID_PRODUCTO")
    private ProductoItem productoItem;

    @ManyToOne
    @JoinColumn(name = "FK_ID_COMPRA")
    private ComprasItem comprasItem;

    @Column(name = "PRECIO", nullable = false)
    private double precio;

}
