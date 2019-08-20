package com.bod.repository;

import com.bod.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<User, Long> {
    //TODO use something like @NamedQuery
}
