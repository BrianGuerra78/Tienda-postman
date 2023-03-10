package com.capgemini.academia.repository;

import com.capgemini.academia.model.ProductoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoItem, Long> {

    @Query(value = "select * from PRODUCTO where CODIGO like %:codigo%", nativeQuery = true)
    Optional<ProductoItem> findByProducto(String codigo);

    @Query(value = "select * from PRODUCTO where CODIGO like %:codigo%", nativeQuery = true)
    Optional<ProductoItem> findByProductoActualizar(String codigo);
}
