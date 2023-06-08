package com.spring.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.memo.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
