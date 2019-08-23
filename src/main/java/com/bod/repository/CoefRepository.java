package com.bod.repository;

import com.bod.domain.Coeficients;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoefRepository extends CrudRepository<Coeficients, Long> {
    List<Coeficients> findAll();
}
