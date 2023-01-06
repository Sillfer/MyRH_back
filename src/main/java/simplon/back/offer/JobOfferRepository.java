package simplon.back.offer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simplon.back.company.Company;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {


    Optional<List<JobOffer>> findJobOfferByTitleContains(String title);

    Optional<List<JobOffer>> findJobOffersByCompany(Company company);

}
