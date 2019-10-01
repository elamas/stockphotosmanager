package stockphotosmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stockphotosmanager.models.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

}
