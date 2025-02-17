package homework;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;


public class ConferenceTest {


    private static final double TICKET_PRICE = 10.90;
    private static final double EMT_DISCOUNT = 0.5;
    private static final double WEB_DISCOUNT = 0.3;

    private Conference conference;
    private Student student1;
    private Student student2;
    private Student student3;


    @Before
    public void setUp() {
        conference = new Conference(2);
        student1 = new Student("John", "Doe", Course.EMT, 20);
        student2 = new Student("Jane", "Doe", Course.WEB, 21);
        student3 = new Student("Jim", "Beam", Course.OS, 22);
    }


    @Test
    public void testCalculateTotalPricePaidWithWebCourse() {
        Conference conference = new Conference(100);
        conference.addAttendeeToConference(new Student("WebStudent", "LastName", Course.WEB, 20));
        double totalPrice = conference.calculateTotalPricePaid();
        assertEquals(10.90 * 0.7, totalPrice, 0.01);  // WEB_DISCOUNT = 0.3
    }


    @Test
    public void testAddAttendeeToConferenceWithinCapacity() {
        boolean result1 = conference.addAttendeeToConference(student1);
        boolean result2 = conference.addAttendeeToConference(student2);
        List<Student> attendees = conference.getAttendees();

        assertTrue(result1);
        assertTrue(result2);
        assertEquals(2, attendees.size());
        assertTrue(attendees.contains(student1));
        assertTrue(attendees.contains(student2));
    }


    @Test
    public void testAddAttendeeToConferenceExceedCapacity() {
        conference.addAttendeeToConference(student1);
        conference.addAttendeeToConference(student2);
        boolean result3 = conference.addAttendeeToConference(student3);
        List<Student> attendees = conference.getAttendees();

        assertTrue(result3);
        assertEquals(3, attendees.size());
        assertTrue(attendees.contains(student1));
        assertTrue(attendees.contains(student2));
        assertTrue(attendees.contains(student3));
        assertEquals(6, conference.getCapacity());
    }

    @Test
    public void testCalculateTotalPricePaid() {
        conference.addAttendeeToConference(student1); // EMT discount
        conference.addAttendeeToConference(student2); // WEB discount
        conference.addAttendeeToConference(student3); // no discount
        double expectedPrice = (1 - EMT_DISCOUNT) * TICKET_PRICE +
                (1 - WEB_DISCOUNT) * TICKET_PRICE +
                TICKET_PRICE;

        double actualPrice = conference.calculateTotalPricePaid();
        assertEquals(expectedPrice, actualPrice, 0.01);
    }


    @Test
    public void testTripleCapacityNotExceedLimit() {
        Conference smallConference = new Conference(3333);
        boolean result = smallConference.tripleCapacity();

        assertTrue(result);
        assertEquals(9999, smallConference.getCapacity());
    }

    @Test
    public void testTripleCapacityExceedLimit() {
        Conference largeConference = new Conference(4000);
        boolean result = largeConference.tripleCapacity();

        assertFalse(result);
        assertEquals(4000, largeConference.getCapacity()); // (4000 * 3 = 12000)
    }


    @Test
    public void testGetCapacity() {
        int capacity = conference.getCapacity();
        assertEquals(2, capacity);
    }

    @Test
    public void testAddAttendeeToConferenceExceedingCapacity() {
        Conference largeConference = new Conference(3340);
        for (int i = 0; i < 3340; i++) {
            largeConference.addAttendeeToConference(new Student("Student" + i, "LastName" + i, Course.OS, 20));
        }
        boolean result = largeConference.addAttendeeToConference(new Student("Overflow", "Student", Course.OS, 20));
        assertFalse(result);  // (3340 * 3 = 10020)
    }

    @Test
    public void testTripleCapacityWithinLimit() {
        Conference conference = new Conference(2000);
        boolean result = conference.tripleCapacity();
        assertTrue(result);
        assertEquals(6000, conference.getCapacity());
    }


    // TESTS FOR STUDENT CLASS

    @Test
    public void testStudentGetName() {
        assertEquals("John", student1.getName());
    }

    @Test
    public void testStudentSetName() {
        student1.setName("Jane");
        assertEquals("Jane", student1.getName());
    }

    @Test
    public void testStudentGetSurname() {
        assertEquals("Doe", student1.getSurname());
    }

    @Test
    public void testStudentSetSurname() {
        student1.setSurname("Smith");
        assertEquals("Smith", student1.getSurname());
    }

    @Test
    public void testStudentGetCourse() {
        assertEquals(Course.EMT, student1.getCourse());
    }

    @Test
    public void testStudentSetCourse() {
        student1.setCourse(Course.WEB);
        assertEquals(Course.WEB, student1.getCourse());
    }

    @Test
    public void testStudentGetAge() {
        assertEquals(20, student1.getAge());
    }

    @Test
    public void testStudentSetAge() {
        student1.setAge(25);
        assertEquals(25, student1.getAge());
    }

    @Test
    public void testStudentToString() {
        String expected = "Student{name='John', surname='Doe', course=EMT, age=20}";
        assertEquals(expected, student1.toString());
    }
}
