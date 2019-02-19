package my.examples.JBCmart.repository;

import my.examples.JBCmart.domain.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageFileRepository extends JpaRepository <ImageFile, Long> {
}
