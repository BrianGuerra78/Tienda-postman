package com.capgemini.academia.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomicilioDTO {

    private Long id_direccion;

    private String calle;

    private String no_interior;

    private String no_exterior;

    private String colonia;

    private String codigo_postal;

    private Long fk_id_cliente;

    private List<ClienteDTO> lstClientes;
}
