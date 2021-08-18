package com.swappee.repository.request;

import com.swappee.domain.request.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the request entity.
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Page<Request> findByOwnerIdAndOwnerHiddenFalse(Long ownerId, Pageable pageable);

    Page<Request> findByTraderIdAndTraderHiddenFalse(Long traderId, Pageable pageable);

    List<Request> findByOwnerItemId(Long ownerItemId);

    List<Request> findByTraderItemId(Long traderItemId);
}
