package com.ktk.ktkapp.repos.kiosk;

import com.ktk.ktkapp.model.kiosk.kioskClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface kioskClientRepo extends JpaRepository<kioskClientModel, Long> {
    Optional<kioskClientModel>findByClientId(Long clientId);

    @Query("SELECT k FROM kioskClientModel k WHERE k.clientName = :clientName AND k.city = :city AND k.province = :province AND k.contractStartDate = :contractStartDate AND k.contractEndDate = :contractEndDate AND k.address = :address")
    Optional<kioskClientModel> findDuplicate(
    @Param("clientName") String clientName,
    @Param("city") String city,
    @Param("province") String province,
    @Param("contractStartDate") LocalDate contractStartDate,
    @Param("contractEndDate") LocalDate contractEndDate,
    @Param("address") String address
);

}
