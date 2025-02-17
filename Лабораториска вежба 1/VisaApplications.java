package junit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VisaApplications {
    public static List<String> applicantsForBothVisas(List<String> ukVisaApplications, List<String> usaVisaApplications) {

        if (ukVisaApplications == null || usaVisaApplications == null) {
            throw new NullPointerException("Visa applications lists cannot be null");
        }

        Set<String> ukSet = new HashSet<>(ukVisaApplications);
        Set<String> usaSet = new HashSet<>(usaVisaApplications);

        Set<String> bothVisasSet = new HashSet<>(ukSet);
        bothVisasSet.retainAll(usaSet);

        if (bothVisasSet.isEmpty()) {
            return null;
        }

        return new ArrayList<>(bothVisasSet);
    }

    public static void main(String[] args) {

        List<String> ukApplications = new ArrayList<>();
        ukApplications.add("ID1");
        ukApplications.add("ID2");
        ukApplications.add("ID3");

        List<String> usaApplications = new ArrayList<>();
        usaApplications.add("ID2");
        usaApplications.add("ID3");
        usaApplications.add("ID4");

        List<String> bothVisaApplications = applicantsForBothVisas(ukApplications, usaApplications);
        System.out.println("Applicants for both visas: " + bothVisaApplications);
    }
}
