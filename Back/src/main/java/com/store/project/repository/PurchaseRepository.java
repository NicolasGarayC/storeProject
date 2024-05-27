package com.store.project.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.store.project.model.PurchaseCosmosDb;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends CosmosRepository<PurchaseCosmosDb, String> {
}
