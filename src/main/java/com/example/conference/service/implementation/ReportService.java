package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IReportDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Reports;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IReportService;

import java.util.List;

public class ReportService implements IReportService {
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IReportDao reportDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportDao = daoFactory.getReportDao();
        } catch (DBException e) {
            //log
        }
    }

    @Override
    public Integer calculateReportsNumber() throws DBException {
        return reportDao.calculateReportsNumber();
    }

    @Override
    public List<Reports> findAllReportsInDB() throws DBException {
        return reportDao.findAllReportsInDB();
    }

    @Override
    public List<Reports> findReport(Integer first, Integer offset) throws DBException {
        return reportDao.findReport(first, offset);
    }

    @Override
    public boolean addReportToDB(Reports reports) {
        return reportDao.addReportToDB(reports);
    }

    @Override
    public boolean updateReportInDB(Reports reports) {
        return reportDao.updateReportInDB(reports);
    }

    @Override
    public boolean deleteReportFromDB(Reports reports) {
        return reportDao.deleteReportFromDB(reports);
    }
}
