package com.capgemini.academia.repository;

import com.capgemini.academia.model.ComprasItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<ComprasItem, Long> {

}
