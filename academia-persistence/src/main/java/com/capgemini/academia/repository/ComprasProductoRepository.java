package com.capgemini.academia.repository;

import com.capgemini.academia.model.ComprasProductoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComprasProductoRepository extends JpaRepository<ComprasProductoItem, Long> {
}
