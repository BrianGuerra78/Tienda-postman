package com.capgemini.academia.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private Long id_cliente;

    private String nombre;

    private String apellido_paterno;

    private String apellido_materno;

    private LocalDate fechaNac;

    private String rfc;

    private List<DomicilioDTO> lstDomicilios;
}
