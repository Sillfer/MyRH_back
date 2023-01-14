package simplon.back.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simplon.back.company.Company;
import simplon.back.utils.EmailSenderService;
import simplon.back.utils.JobOfferNotFoundException;

import java.util.List;

@Service
public class JobOfferService {

    private final JobOfferRepository jobOfferRepository;

    private final EmailSenderService emailSenderService;


    @Autowired
    public JobOfferService(JobOfferRepository jobOfferRepository, EmailSenderService emailSenderService) {
        this.jobOfferRepository = jobOfferRepository;
        this.emailSenderService = emailSenderService;
    }

    public JobOffer save(JobOffer jobOffer) {
        emailSenderService.sendEmail("gliouinemahdi0@gmail.com", "New Job Offer", "A new job offer has been created");
        return jobOfferRepository.save(jobOffer);
    }

    public JobOffer findById(Long id) {
        return jobOfferRepository.findById(id).get();
    }

    public List<JobOffer> findAll() {
        return jobOfferRepository.findAll();
    }

    public List<JobOffer> findJobOfferByTitle(String title) {

        return jobOfferRepository.findJobOfferByTitleContains(title).orElseThrow(() -> new JobOfferNotFoundException("Job offer with title " + title + " does not exists"));
    }

    public List<JobOffer> findJobOfferByCompany(Company company) {
        return jobOfferRepository.findJobOffersByCompany(company).orElseThrow(() -> new JobOfferNotFoundException("Job offer with company " + company.getName() + " does not exists"));
    }

    public JobOffer updateJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    public void deleteJobOffer(Long id) {
        jobOfferRepository.deleteById(id);
    }


}
