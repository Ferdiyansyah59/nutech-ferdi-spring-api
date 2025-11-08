package com.nutech_ferdi.nutech_ferdi.repository;


import com.nutech_ferdi.nutech_ferdi.model.MasterService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MasterServiceRepository extends JpaRepository<MasterService, Long> {
    Optional<MasterService> findByServiceCode(String serviceCode);
}
