package simplon.back.offer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
//        send the email to the company that created the job offer
        emailSenderService.sendEmail("gliouinemahdi0@gmail.com",
                "New Job Offer", "A new job offer has been created by " + jobOffer.getCompany().getName() + "\n For the position of " + jobOffer.getTitle()
                        + " \n Please Login to this Link to see the details : http://localhost:4200/auth/agent");
        return jobOfferRepository.save(jobOffer);
    }

    public JobOffer findById(Long id) {
        return jobOfferRepository.findById(id).get();
    }

    public Page<JobOffer> findAll(Pageable pageable) {
        return jobOfferRepository.findAll(pageable);
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
