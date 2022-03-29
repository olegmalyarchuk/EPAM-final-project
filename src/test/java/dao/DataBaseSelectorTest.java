package dao;

import com.example.conference.dao.DataBaseSelector;
import org.junit.Assert;
import org.junit.Test;

public class DataBaseSelectorTest {
    @Test
    public void mysql() {
        DataBaseSelector db = DataBaseSelector.MY_SQL;
        String res = db.toString();
        Assert.assertEquals(res, "MySQL");
    }
    @Test
    public void mssql() {
        DataBaseSelector db = DataBaseSelector.MS_SQL;
        String res = db.toString();
        Assert.assertEquals(res, "Microsoft SQL Server");
    }
    @Test
    public void oracle() {
        DataBaseSelector db = DataBaseSelector.ORACLE;
        String res = db.toString();
        Assert.assertEquals(res, "Oracle Database");
    }
    @Test
    public void postgress() {
        DataBaseSelector db = DataBaseSelector.POSTGRESS;
        String res = db.toString();
        Assert.assertEquals(res, "PostgreSQL");
    }
}
