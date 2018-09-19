package com.example.demoSpringBoot.hello.dao;

import com.example.demoSpringBoot.hello.model.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GirlDao extends JpaRepository<Girl,Integer> {
}
