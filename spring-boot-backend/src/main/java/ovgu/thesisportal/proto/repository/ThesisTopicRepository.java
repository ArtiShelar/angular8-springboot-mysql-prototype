package ovgu.thesisportal.proto.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ovgu.thesisportal.proto.model.ThesisTopic;

@Repository
public interface ThesisTopicRepository extends CrudRepository<ThesisTopic, Long> {
}
