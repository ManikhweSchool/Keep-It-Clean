package com.keepitclean.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keepitclean.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{

}
