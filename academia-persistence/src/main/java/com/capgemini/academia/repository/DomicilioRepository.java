package com.capgemini.academia.repository;

import com.capgemini.academia.model.DomicilioItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DomicilioRepository extends JpaRepository<DomicilioItem, Long> {

}
