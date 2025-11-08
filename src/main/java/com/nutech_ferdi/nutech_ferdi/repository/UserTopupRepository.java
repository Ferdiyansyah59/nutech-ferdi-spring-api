package com.nutech_ferdi.nutech_ferdi.repository;

import com.nutech_ferdi.nutech_ferdi.model.UserTopup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTopupRepository extends JpaRepository<UserTopup, Long> {
}
