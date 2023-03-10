package com.capgemini.academia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Compras")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComprasItem implements Serializable {

    @Id
    @SequenceGenerator(name = "seqComp", sequenceName = "compras_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "seqComp")
    private Long id_compra;

    @Column(name = "FECHA_COMPRA", nullable = false)
    private Date fecha_compra;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "FK_ID_CLIENTE")
    private ClienteItem fk_id_cliente;

    @Column(name = "TOTAL", nullable = false)
    private double total;

    @ManyToOne
    @JoinColumn(name = "FK_ID_DIRECCION")
    private DomicilioItem fk_id_direccion;

    @OneToMany(mappedBy = "comprasItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ComprasProductoItem> compras;
}
