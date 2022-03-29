package comparators;

import com.example.conference.comparators.ReportsCountComparator;
import com.example.conference.comparators.UsersCountComparator;
import com.example.conference.entity.Events;
import org.junit.Assert;
import org.junit.Test;

public class UserCountComparatorTest {
    @Test
    public void checkBigger() {
        Events event1 = new Events();
        event1.setRegisteredCount(5);
        Events event2 = new Events();
        event2.setRegisteredCount(3);
        UsersCountComparator comparator = new UsersCountComparator();
        int result = comparator.compare(event1, event2);
        Assert.assertTrue(result>0);
    }
    @Test
    public void checkLess() {
        Events event1 = new Events();
        event1.setRegisteredCount(5);
        Events event2 = new Events();
        event2.setRegisteredCount(3);
        UsersCountComparator comparator = new UsersCountComparator();
        int result = comparator.compare(event2, event1);
        Assert.assertTrue(result<0);
    }
    @Test
    public void checkEquals() {
        Events event1 = new Events();
        event1.setRegisteredCount(5);
        Events event2 = new Events();
        event2.setRegisteredCount(5);
        UsersCountComparator comparator = new UsersCountComparator();
        int result = comparator.compare(event1, event2);
        Assert.assertTrue(result==0);
    }
}
