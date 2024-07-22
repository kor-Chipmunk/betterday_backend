package com.mashup.betterday.fcm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FcmJpaRepository extends JpaRepository<FcmEntity, Long> {
}
