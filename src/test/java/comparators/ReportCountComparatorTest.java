package comparators;

import com.example.conference.comparators.DateComparator;
import com.example.conference.comparators.ReportsCountComparator;
import com.example.conference.entity.Events;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class ReportCountComparatorTest {
    @Test
    public void checkBigger() {
        Events event1 = new Events();
        event1.setReportsCount(5);
        Events event2 = new Events();
        event2.setReportsCount(3);
        ReportsCountComparator comparator = new ReportsCountComparator();
        int result = comparator.compare(event1, event2);
        Assert.assertTrue(result>0);
    }
    @Test
    public void checkLess() {
        Events event1 = new Events();
        event1.setReportsCount(5);
        Events event2 = new Events();
        event2.setReportsCount(3);
        ReportsCountComparator comparator = new ReportsCountComparator();
        int result = comparator.compare(event2, event1);
        Assert.assertTrue(result<0);
    }
    @Test
    public void checkEquals() {
        Events event1 = new Events();
        event1.setReportsCount(5);
        Events event2 = new Events();
        event2.setReportsCount(5);
        ReportsCountComparator comparator = new ReportsCountComparator();
        int result = comparator.compare(event1, event2);
        Assert.assertTrue(result==0);
    }
}
