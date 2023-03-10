package com.capgemini.academia.repository;

import com.capgemini.academia.model.ClienteItem;
import com.capgemini.academia.model.DomicilioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteItem, Long> {

    @Query(value = "select * from CLIENTE where RFC like %:rfc%", nativeQuery = true)
    Optional<ClienteItem> findByCliente(String rfc);

    @Query(value = "select * from DOMICILIO  where CALLE like %:calle% AND NUMERO_INTERIOR like %:no_interior%" +
            " AND NUMERO_EXTERIOR like %:no_exterior% AND COLONIA like %:colonia% AND CODIGO_POSTAL like %:codigo_postal%" +
            " AND FK_ID_CLIENTE like %:fk_id_cliente%", nativeQuery = true)
    Optional<ClienteItem> findByDomicilio(String calle, String no_interior, String no_exterior, String colonia,
                                          String codigo_postal, Long fk_id_cliente);

}
