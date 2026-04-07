package tn.esprit.iyed_mohamed_artic10.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import tn.esprit.iyed_mohamed_artic10.Enum.CallSkills;
import tn.esprit.iyed_mohamed_artic10.Enum.CallStatus;
import tn.esprit.iyed_mohamed_artic10.entities.Calls;

import java.util.List;

@Repository
public interface ICallsRepository extends JpaRepository<Calls,Long> {
	List<Calls> findByCallStatus(CallStatus callStatus);

	List<Calls> findByCallStatusAndAssignedAgent_AgentsId(CallStatus callStatus, long agentId);

	List<Calls> findByAssignedAgentIsNull();

	List<Calls> findByRequiredSkillsContains(CallSkills skill);

	List<Calls> findByRequiredSkillsContainsOrderByCallDateTimeAsc(CallSkills skill, Pageable pageable);

	boolean existsByPhoneNumber(String phoneNumber);

	long countByCallStatus(CallStatus callStatus);
}
