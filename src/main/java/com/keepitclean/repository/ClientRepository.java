package com.keepitclean.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.keepitclean.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>{

}
