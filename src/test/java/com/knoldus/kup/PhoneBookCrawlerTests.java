package com.knoldus.kup;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.assertj.core.api.Assertions.assertThat;

public class PhoneBookCrawlerTests {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final PhoneBookCrawler phoneBookCrawler = new PhoneBookCrawler(new PhoneBook());

    @Test
    public void findPhoneNumberByNameAndPunishIfNothingFound()  {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("No phone number found");
        phoneBookCrawler.findPhoneNumberByNameAndPunishIfNothingFound("Ali");
    }

    @Test
    public void findPhoneNumberByName_CorrectIfFind(){
        String phoneNumberActual = phoneBookCrawler.findPhoneNumberByNameAndPrintPhoneBookIfNothingFound("Harsh");
        assertThat(phoneNumberActual).isEqualTo("8433441064");
    }

    @Test
    public void findPhoneNumberByNameAndReturnEntirePhoneBookIfNothingFound() {
        String phoneBookActual = phoneBookCrawler.findPhoneNumberByNameAndPrintPhoneBookIfNothingFound("Mayank");
        assertThat(phoneBookActual).isEqualTo("PhoneBook{ PhoneBook = {Harsh=8433441064, Ayush=9027986545}}");
    }

    @Test
    public void findPhoneNumberByNameOrNameByPhoneNumber_CorrectIfFoundByName() {
        String phoneNumberActual = phoneBookCrawler.findPhoneNumberByNameOrNameByPhoneNumber("Harsh", "12456578");
        assertThat(phoneNumberActual).isEqualTo("8433441064");
    }

    @Test
    public void findPhoneNumberByNameOrNameByPhoneNumber_CorrectIfFoundByPhoneNumber() {
        String name = phoneBookCrawler.findPhoneNumberByNameOrNameByPhoneNumber("Prakhar", "9027986545");
        assertThat(name).isEqualTo("Ayush");
    }
}
