package junit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisaApplicationsTest {



    @Test
    //Test1 T T T T T T
    public void testApplicantsForBothVisas() {

        List<String> ukApplications = Arrays.asList("ID1", "ID2", "ID3");
        List<String> usaApplications = Arrays.asList("ID2", "ID3", "ID4");

        List<String> expectedResult = Arrays.asList("ID2", "ID3");

        List<String> result = VisaApplications.applicantsForBothVisas(ukApplications, usaApplications);

        assertEquals(expectedResult, result);
    }

    @Test
    //Test T F T F F F
    public void testApplicantsForBothVisasEmptyLists() {

        List<String> ukApplications = Arrays.asList();
        List<String> usaApplications = Arrays.asList();

        List<String> expectedResult = Arrays.asList();

        List<String> result = VisaApplications.applicantsForBothVisas(ukApplications, usaApplications);

        assertEquals(null, result);
    }

    @Test
    //Test3 T F T T F F AND Test5 T T T F F F
    public void testApplicantsForBothVisasEmptyList() {

        List<String> ukApplications = Arrays.asList();
        List<String> usaApplications = Arrays.asList("ID1", "ID2", "ID3");

        List<String> expectedResult = Arrays.asList();

        List<String> result = VisaApplications.applicantsForBothVisas(ukApplications, usaApplications);

        assertEquals(null, result);
    }


    @Test
    //Test6 T T T T F F
    public void testApplicantsForBothVisasNoCommonIDs() {

        List<String> ukApplications = Arrays.asList("ID1", "ID2");
        List<String> usaApplications = Arrays.asList("ID3", "ID4");

        List<String> expectedResult = Arrays.asList();

        List<String> result = VisaApplications.applicantsForBothVisas(ukApplications, usaApplications);

        assertEquals(null, result);
    }

    @Test()
    //Test2 F F T T F F AND Test4 T T F F F F
    public void testApplicantsForBothVisasNullList() {

        List<String> ukApplications = Arrays.asList("ID6", "ID7", "ID8");
        List<String> usaApplications = null;

        List<String> result = VisaApplications.applicantsForBothVisas(ukApplications, usaApplications);

        assertThrows(NullPointerException.class, () -> {
            VisaApplications.applicantsForBothVisas(ukApplications, usaApplications);
        });
    }

    @Test
    public void testApplicantsForBothVisasExtraIDsInResult() {
        List<String> ukApplications = Arrays.asList("ID1", "ID2", "ID3");
        List<String> usaApplications = Arrays.asList("ID2", "ID3", "ID4");

        List<String> expectedResult = Arrays.asList("ID2", "ID3", "ID4");
        List<String> result = VisaApplications.applicantsForBothVisas(ukApplications, usaApplications);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testApplicantsForBothVisasUnorderedResult() {
        List<String> ukApplications = Arrays.asList("ID1", "ID2", "ID3");
        List<String> usaApplications = Arrays.asList("ID2", "ID3", "ID4");

        List<String> expectedResult = Arrays.asList("ID2", "ID3");
        List<String> result = VisaApplications.applicantsForBothVisas(ukApplications, usaApplications);

        assertNotEquals(expectedResult, result);
    }
}
