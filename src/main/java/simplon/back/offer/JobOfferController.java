package simplon.back.offer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simplon.back.company.Company;
import simplon.back.company.CompanyService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/job_offer")
public class JobOfferController {

    private final JobOfferService jobOfferService;
    private final CompanyService companyService;

    @Autowired
    public JobOfferController(JobOfferService jobOfferService, CompanyService companyService) {
        this.jobOfferService = jobOfferService;
        this.companyService = companyService;
    }

    @PostMapping("/save")
    public ResponseEntity<JobOffer> save(@RequestBody JobOffer jobOffer,@RequestParam String companyName){
        Company company = companyService.findByName(companyName);
        jobOffer.setCompany(company);

        JobOffer newOffer = jobOfferService.save(jobOffer);
        System.out.println("-----------------"+newOffer.getEducationLevel());

        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(newOffer);
    }

    @GetMapping("/search")
    public JobOffer findById(@RequestParam Long id){
        return jobOfferService.findById(id);
    }

    @GetMapping()
    public ResponseEntity<List<JobOffer>> getAllJobOffers(){
        List<JobOffer> jobOffers = jobOfferService.findAll();


        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jobOffers);
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<JobOffer>> findJobOfferByTitle(@RequestParam String title){
        return ResponseEntity.ok(jobOfferService.findJobOfferByTitle(title));
    }

    @GetMapping("/search/company")
    public ResponseEntity<List<JobOffer>> findJobOfferByCompany(@RequestParam String company){
        Company company1 = companyService.findByName(company);
        return ResponseEntity.ok(jobOfferService.findJobOfferByCompany(company1));
    }
}
