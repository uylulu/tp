package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

public class JsonAdaptedPersonTest {

    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_ALLERGY = "^^dust";
    private static final String INVALID_UNIQUE_ID = null;

    private static final String VALID_CHILD_NAME = BENSON.getChildName().toString();
    private static final String VALID_PARENT_NAME = BENSON.getParentName().toString();
    private static final String VALID_PARENT_PHONE = BENSON.getParentPhone().toString();
    private static final String VALID_PARENT_EMAIL = BENSON.getParentEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<String> VALID_ALLERGIES = BENSON.getAllergies().getAllergyList().stream()
            .map(Object::toString)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_UNIQUE_ID = Integer.toString(BENSON.getUniqueId());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidChildName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                INVALID_NAME, VALID_PARENT_NAME, VALID_PARENT_PHONE, VALID_PARENT_EMAIL,
                VALID_ADDRESS, VALID_ALLERGIES, VALID_TAGS, VALID_UNIQUE_ID);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullChildName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                null, VALID_PARENT_NAME, VALID_PARENT_PHONE, VALID_PARENT_EMAIL,
                VALID_ADDRESS, VALID_ALLERGIES, VALID_TAGS, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidParentName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_CHILD_NAME, INVALID_NAME, VALID_PARENT_PHONE, VALID_PARENT_EMAIL,
                VALID_ADDRESS, VALID_ALLERGIES, VALID_TAGS, VALID_UNIQUE_ID);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_CHILD_NAME, VALID_PARENT_NAME, INVALID_PHONE, VALID_PARENT_EMAIL,
                VALID_ADDRESS, VALID_ALLERGIES, VALID_TAGS, VALID_UNIQUE_ID);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_CHILD_NAME, VALID_PARENT_NAME, null, VALID_PARENT_EMAIL,
                VALID_ADDRESS, VALID_ALLERGIES, VALID_TAGS, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_CHILD_NAME, VALID_PARENT_NAME, VALID_PARENT_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_ALLERGIES, VALID_TAGS, VALID_UNIQUE_ID);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_CHILD_NAME, VALID_PARENT_NAME, VALID_PARENT_PHONE, null,
                VALID_ADDRESS, VALID_ALLERGIES, VALID_TAGS, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_CHILD_NAME, VALID_PARENT_NAME, VALID_PARENT_PHONE, VALID_PARENT_EMAIL,
                INVALID_ADDRESS, VALID_ALLERGIES, VALID_TAGS, VALID_UNIQUE_ID);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_CHILD_NAME, VALID_PARENT_NAME, VALID_PARENT_PHONE, VALID_PARENT_EMAIL,
                null, VALID_ALLERGIES, VALID_TAGS, VALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAllergies_throwsIllegalValueException() {
        List<String> invalidAllergies = new ArrayList<>(VALID_ALLERGIES);
        invalidAllergies.add(INVALID_ALLERGY);
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_CHILD_NAME, VALID_PARENT_NAME, VALID_PARENT_PHONE, VALID_PARENT_EMAIL,
                VALID_ADDRESS, invalidAllergies, VALID_TAGS, VALID_UNIQUE_ID);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_CHILD_NAME, VALID_PARENT_NAME, VALID_PARENT_PHONE, VALID_PARENT_EMAIL,
                VALID_ADDRESS, VALID_ALLERGIES, invalidTags, VALID_UNIQUE_ID);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidUniqueId_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_CHILD_NAME, VALID_PARENT_NAME, VALID_PARENT_PHONE, VALID_PARENT_EMAIL,
                VALID_ADDRESS, VALID_ALLERGIES, VALID_TAGS, INVALID_UNIQUE_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "Unique Id");
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
