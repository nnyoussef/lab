package nyo.lu.appdeployer.jee.domain.bdd.repository;

import nyo.lu.appdeployer.jee.domain.bdd.entities.IndexedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndexedFileRepository extends JpaRepository<IndexedFile, Long> {
}
