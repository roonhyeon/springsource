package com.spring.memo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.memo.entity.Memo;

// T: Entity 객체명 작성, ID: Entity 클래스의 id 컬럼 타입
public interface MemoRepository extends JpaRepository<Memo, Long> {
	
	// JpaRepository: CRUD 작업
	// save(): 저장 및 수정, delete(): 삭제, count(): 개수 반환, findAll(): 모든 엔티티 조회, findById(): 특정 엔티티 조회

}
