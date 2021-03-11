package main;

import main.annotation.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

@Constrained
class BookingForm {

    @NotNull
    @Size(min = 1, max = 5)
    private final List<@NotNull GuestForm> guests;
    @NotNull
    private final List<@AnyOf({"TV", "Kitchen"}) String> amenities;
    @NotNull
    @AnyOf({"House", "Hostel"})
    private final String propertyType;
    @NotNull
    private final Unrelated unrelated;

    public BookingForm(List<GuestForm> guests, List<String> amenities, String
            propertyType, Unrelated unrelated) {
        this.guests = guests;
        this.amenities = amenities;
        this.unrelated = unrelated;
        this.propertyType = propertyType;
    }
}

@Constrained
class GuestForm {
    @NotNull
    @NotBlank
    private final String firstName;
    @NotBlank
    @NotNull
    private final String lastName;
    @InRange(min = 0, max = 200)
    private final int age;

    public GuestForm(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
}

class Unrelated {
    @Positive
    private final int x;

    public Unrelated(int x) {
        this.x = x;
    }
}

@Constrained
class Example {
    @Negative
    @InRange(min = 5, max = -1)
    long longValue;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 3)
    String stringValue;

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 2)
    final Map<Short, String> map = new LinkedHashMap<>();
    @Positive
    @Negative
    int intValue;
    @NotNull
    private final List<@Size(min = -2, max = -7)
            List<@Size(min = 10, max = 11)
                    List<@Positive Integer>>> listListList = new ArrayList<>();

    public Example() {
        longValue = 2;
        stringValue = "Hi";
        map.put((short) 1, "English");
        intValue = 15;
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(-4);
        List<List<Integer>> listList = new ArrayList<>();
        listList.add(list);
        listList.add(list);
        listList.add(list);
        listList.add(list);
        listListList.add(listList);
        listListList.add(listList);
        listListList.add(listList);
        listListList.add(listList);
    }
}


class ValidatorClassTest {
    final ValidatorClass validator = new ValidatorClass();
    static BookingForm bookingForm;
    final String EXPECTED_1 = "guests[0].firstName   must not be null   null" +
            "\nguests[1].firstName   must not be blank   " +
            "\nguests[1].age   must be in range between 0 and 200   -3" +
            "\namenities[1]   must be one of 'TV', 'Kitchen'   Piano" +
            "\npropertyType   must be one of 'House', 'Hostel'   Apartment\n";
    final String EXPECTED_2 = "longValue   must be negative   2\n" +
            "longValue   the field has annotation with min: 5 more than max: -1 , so always will be a problem with the field value, whatever it's   2\n" +
            "stringValue   the size must be equal to 3   Hi\n" +
            "map   the size must be equal to 2   {1=English}\n" +
            "intValue   must be negative   15\n" +
            "listListList[0]   the field has annotation with min: -2 more than max: -7 , so always will be a problem with the field size, whatever it's   [[1, -4], [1, -4], [1, -4], [1, -4]]\n" +
            "listListList[0][0]   the size must be in range between 10 and 11   [1, -4]\n" +
            "listListList[0][0][1]   must be positive   -4\n" +
            "listListList[0][1]   the size must be in range between 10 and 11   [1, -4]\n" +
            "listListList[0][1][1]   must be positive   -4\n" +
            "listListList[0][2]   the size must be in range between 10 and 11   [1, -4]\n" +
            "listListList[0][2][1]   must be positive   -4\n" +
            "listListList[0][3]   the size must be in range between 10 and 11   [1, -4]\n" +
            "listListList[0][3][1]   must be positive   -4\n" +
            "listListList[1]   the field has annotation with min: -2 more than max: -7 , so always will be a problem with the field size, whatever it's   [[1, -4], [1, -4], [1, -4], [1, -4]]\n" +
            "listListList[1][0][1]   must be positive   -4\n" +
            "listListList[1][1][1]   must be positive   -4\n" +
            "listListList[1][2][1]   must be positive   -4\n" +
            "listListList[1][3][1]   must be positive   -4\n" +
            "listListList[2]   the field has annotation with min: -2 more than max: -7 , so always will be a problem with the field size, whatever it's   [[1, -4], [1, -4], [1, -4], [1, -4]]\n" +
            "listListList[2][0][1]   must be positive   -4\n" +
            "listListList[2][1][1]   must be positive   -4\n" +
            "listListList[2][2][1]   must be positive   -4\n" +
            "listListList[2][3][1]   must be positive   -4\n" +
            "listListList[3]   the field has annotation with min: -2 more than max: -7 , so always will be a problem with the field size, whatever it's   [[1, -4], [1, -4], [1, -4], [1, -4]]\n" +
            "listListList[3][0][1]   must be positive   -4\n" +
            "listListList[3][1][1]   must be positive   -4\n" +
            "listListList[3][2][1]   must be positive   -4\n" +
            "listListList[3][3][1]   must be positive   -4\n";

    @BeforeAll
    static void setUp() {
        List<GuestForm> guests = List.of(
                new GuestForm(/*firstName*/ null, /*lastName*/ "Def", /*age*/ 21),
                new GuestForm(/*firstName*/ "", /*lastName*/ "Ijk", /*age*/ -3)
        );
        Unrelated unrelated = new Unrelated(-1);
        bookingForm = new BookingForm(
                guests,
                /*amenities*/ List.of("TV", "Piano"),
                /*propertyType*/ "Apartment",
                unrelated
        );
    }

    @Test
    void validate_1() {
        Set<ValidationError> validationErrorSet = validator.validate(bookingForm);
        StringBuilder actual = new StringBuilder();
        for (ValidationError validationError : validationErrorSet) {
            actual.append(validationError.getPath()).append("   ").
                    append(validationError.getMessage()).append("   ").
                    append(validationError.getFailedValue()).append("\n");
        }
        assertEquals(EXPECTED_1, actual.toString());
    }

    @Test
    void validate_2() {
        Set<ValidationError> validationErrorSet = validator.validate(new Example());
        StringBuilder actual = new StringBuilder();
        for (ValidationError validationError : validationErrorSet) {
            actual.append(validationError.getPath()).append("   ").
                    append(validationError.getMessage()).append("   ").
                    append(validationError.getFailedValue()).append("\n");

        }
        assertEquals(EXPECTED_2, actual.toString());
    }
}