package simplon.back.offer;

import org.springframework.stereotype.Service;
import simplon.back.company.Company;
import simplon.back.utils.JobOfferNotFoundException;

import java.util.List;

@Service
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;


    public JobOfferService(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    public JobOffer save(JobOffer jobOffer){
        return jobOfferRepository.save(jobOffer);
    }

    public JobOffer findById(Long id){
        return jobOfferRepository.findById(id).get();
    }

    public List<JobOffer> findAll(){
        return jobOfferRepository.findAll();
    }

    public List<JobOffer> findJobOfferByTitle(String title){

        return jobOfferRepository.findJobOfferByTitleContains(title).orElseThrow(() -> new JobOfferNotFoundException("Job offer with title " + title + " does not exists"));
    }

    public List<JobOffer>  findJobOfferByCompany(Company company){
        return jobOfferRepository.findJobOffersByCompany(company).orElseThrow( () -> new JobOfferNotFoundException("Job offer with company " + company.getName() + " does not exists"));
    }

    public JobOffer updateJobOffer(JobOffer jobOffer){
        return jobOfferRepository.save(jobOffer);
    }

    public void deleteJobOffer(Long id){
        jobOfferRepository.deleteById(id);
    }



}
