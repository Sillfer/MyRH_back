package simplon.back.recruiter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import simplon.back.company.Company;

@Repository
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {

    public Recruiter findByCompany(Company company);
}

