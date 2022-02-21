package hu.webuni.logistics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.webuni.logistics.model.Milestone;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {

}
