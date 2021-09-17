package com.trendyol.linkconverter.repository;

import com.trendyol.linkconverter.repository.model.URLRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * this repository is for crud operations on db
 */
@Repository("dbRepository")
public interface DbRepository extends JpaRepository<URLRequest, Long> {

}
