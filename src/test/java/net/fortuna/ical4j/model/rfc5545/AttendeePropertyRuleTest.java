package net.fortuna.ical4j.model.rfc5545;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.junit.Test;

import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.property.Attendee;

public class AttendeePropertyRuleTest {

    @Test
    public void shouldCorrectlyRemoveApostrophes() throws URISyntaxException {
        Attendee attendee = new Attendee("mailto:'mobile-media-applications@1und1.de'");
        attendee.getParameters().add(new Cn("Mobile Media"));
        RuleManager.applyTo(attendee);
        assertEquals("mailto:mobile-media-applications@1und1.de", attendee.getValue());
    }

    @Test
    public void shouldLeaveAttendeeAsItIs() throws URISyntaxException {
        Attendee attendee = new Attendee("mailto:mobile-media-applications@1und1.de");
        attendee.getParameters().add(new Cn("Mobile Media"));
        RuleManager.applyTo(attendee);
        assertEquals("mailto:mobile-media-applications@1und1.de", attendee.getValue());
    }

    @Test
    public void shouldNotThrowExceptionIfAttendeeIsEmpty() throws URISyntaxException {
        RuleManager.applyTo(new Attendee());
    }

    @Test
    public void shouldNotThrowExceptionIfOneApostrophe() throws URISyntaxException {
        RuleManager.applyTo(new Attendee("mailto:'"));
    }

    @Test
    public void shouldNotThrowExceptionIfTwoApostrophes() throws URISyntaxException {
        RuleManager.applyTo(new Attendee("mailto:''"));
    }

    @Test
    public void shouldNotDoAnythingIfAnotherScheem() throws URISyntaxException {
        String value = "http://something";
        Attendee attende = new Attendee(value);
        RuleManager.applyTo(attende);
        assertEquals(value, attende.getValue());
    }

}
