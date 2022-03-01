package hu.webuni.logistics.repository;


import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import hu.webuni.logistics.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long> {

	@EntityGraph(attributePaths = {"sections", "sections.fromMilestone", "sections.toMilestone" })
	@Query("SELECT p FROM TransportPlan p WHERE p.id =:id")
	public TransportPlan getEagerTransportPlan(Long id);
}
