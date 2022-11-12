package com.egs.bankservice.common.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.egs.bankservice.common.entity.AbstractEntity;


public interface BaseRepository<T extends AbstractEntity> extends JpaRepository<T , Long> {

}
