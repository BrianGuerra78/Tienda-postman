package com.capgemini.academia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "CLIENTE")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteItem implements Serializable {

    @Id
    @SequenceGenerator(name = "seqCliente", sequenceName = "cliente_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "seqCliente")
    private Long id_cliente;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "APELLIDO_PATERNO", nullable = false)
    private String apellido_paterno;

    @Column(name = "APELLIDO_MATERNO", nullable = false)
    private String apellido_materno;

    @Column(name = "FECHA_NACIMIENTO", nullable = false)
    private LocalDate fechaNac;

    @Column(name = "RFC", nullable = false)
    private String rfc;

    @OneToMany(mappedBy = "clienteItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DomicilioItem> direciones;
}
