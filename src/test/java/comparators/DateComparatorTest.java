package comparators;

import com.example.conference.comparators.DateComparator;
import com.example.conference.entity.Events;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class DateComparatorTest {
    @Test
    public void checkAfter() {
        Events event1 = new Events();
        event1.setEvent_date(LocalDateTime.now());
        Events event2 = new Events();
        event2.setEvent_date(LocalDateTime.of(2022, 01, 10, 10, 10));
        DateComparator dateComparator = new DateComparator();
        int result = dateComparator.compare(event1, event2);
        Assert.assertTrue(result>0);
    }
    @Test
    public void checkBefore() {
        Events event1 = new Events();
        event1.setEvent_date(LocalDateTime.now());
        Events event2 = new Events();
        event2.setEvent_date(LocalDateTime.of(2022, 01, 10, 10, 10));
        DateComparator dateComparator = new DateComparator();
        int result = dateComparator.compare(event2, event1);
        Assert.assertTrue(result<0);
    }
    @Test
    public void checkEquals() {
        Events event1 = new Events();
        event1.setEvent_date(LocalDateTime.now());
        Events event2 = new Events();
        event2.setEvent_date(LocalDateTime.now());
        DateComparator dateComparator = new DateComparator();
        int result = dateComparator.compare(event1, event2);
        Assert.assertTrue(result==0);
    }
}
