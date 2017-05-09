package com.healthserviceapp.areas.protocol.repositories;

import com.healthserviceapp.areas.protocol.entities.Protocol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProtocolRepository extends JpaRepository<Protocol, Long>{

    Protocol findByNumber(String number);
}
