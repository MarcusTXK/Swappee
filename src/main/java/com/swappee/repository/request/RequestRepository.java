package com.swappee.repository.request;

import com.swappee.domain.request.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the request entity.
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByOwnerIdAndHiddenFalseOrderByLastModifiedDateDesc(Long ownerId);
    List<Request> findByTraderIdAndHiddenFalseOrderByLastModifiedDateDesc(Long traderId);
}
