package simplon.back.offer;

import lombok.Getter;
import lombok.Setter;
import simplon.back.company.Company;

@Getter
@Setter
public class OfferDto {

    private String title;
    private String description;
    private String location;
    private String contractType;
    private Company company;
    private String salary;
    private String profile;
    private String educationLevel;
    private int experienceLevel;
}
