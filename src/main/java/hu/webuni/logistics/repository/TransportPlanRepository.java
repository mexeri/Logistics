package hu.webuni.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.logistics.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long> {

}
