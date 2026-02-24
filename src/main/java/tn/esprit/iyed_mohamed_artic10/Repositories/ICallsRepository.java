package tn.esprit.iyed_mohamed_artic10.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.iyed_mohamed_artic10.entities.Calls;

@Repository
public interface ICallsRepository extends JpaRepository<Calls,Long> {

}
