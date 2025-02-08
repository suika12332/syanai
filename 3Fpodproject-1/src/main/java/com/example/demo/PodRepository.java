package com.example.demo;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PodRepository extends JpaRepository<Shigoto, Integer>{
	
	Optional<Shigoto> findByTitle(String title);
	
	@Query(value = "SELECT * FROM shigoto p where "
			+ "p.iraibango like concat('%',:iraibango,'%') or p.kakoujouhou = :kakoujouhou "
			+ "and p.shurui = :shurui and p.shukkabi between date(:shukkabi) and(:shukkabi2) "
			+ "and p.sagyousya = :sagyousya order by id desc", nativeQuery = true)
	List<Shigoto> Jouken(@Param("iraibango")String irabango, @Param("kakoujouhou")String kakoujouhou, @Param("shurui")int shurui, @Param("shukkabi") LocalDate shukkabi,@Param("shukkabi2") LocalDate shukkabi2, @Param("sagyousya")String sagyousya);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM shigoto p WHERE p.id = :id", nativeQuery = true)
	void deleteById(Long id);

	
	/*
	 * @Modifying
	 * 
	 * @Transactional
	 * 
	 * @Query(value = "insert into podsyain values(:id ,:pw ,:name)", nativeQuery =
	 * true) int savequery(@Param("id")String id, @Param("pw")String
	 * pw, @Param("name")String name);
	 * 
	 * @Query(value = "select * from podsyain p where p.id = :id and p.pw=:pw",
	 * nativeQuery = true) List<PodInterface> findID(@Param("id")String
	 * id, @Param("pw")String pw);
	 */

	
}
