package com.egs.bankservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.egs.bankservice.entity.AuthenticationMethod;


public interface AuthenticationMethodRepository extends JpaRepository<AuthenticationMethod , Long> {

}
