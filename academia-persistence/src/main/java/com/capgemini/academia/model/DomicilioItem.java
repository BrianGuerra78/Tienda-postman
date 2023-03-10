package com.capgemini.academia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Domicilio")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomicilioItem implements Serializable {

    @Id
    @SequenceGenerator(name = "seqDom", sequenceName = "domicilio_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "seqDom")
    private Long id_direccion;

    @Column(name = "CALLE", nullable = false)
    private String calle;

    @Column(name = "NUMERO_INTERIOR", nullable = false)
    private String no_interior;

    @Column(name = "NUMERO_EXTERIOR", nullable = false)
    private String no_exterior;

    @Column(name = "COLONIA", nullable = false)
    private String colonia;

    @Column(name = "CODIGO_POSTAL", nullable = false)
    private String codigo_postal;

    @ManyToOne
    @JoinColumn(name = "FK_ID_CLIENTE")
    private ClienteItem clienteItem;

    @OneToMany(mappedBy = "id_cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ClienteItem> clientes;
}
