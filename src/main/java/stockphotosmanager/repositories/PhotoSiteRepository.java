package stockphotosmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stockphotosmanager.models.PhotoSite;

@Repository
public interface PhotoSiteRepository extends JpaRepository<PhotoSite, Long> {

}
