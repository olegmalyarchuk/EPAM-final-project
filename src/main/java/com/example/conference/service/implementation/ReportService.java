package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IReportDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Events;
import com.example.conference.entity.Reports;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IReportService;
import org.apache.log4j.Logger;
import org.w3c.dom.events.Event;

import java.util.ArrayList;
import java.util.List;

public class ReportService implements IReportService {
    private static final Logger log = Logger.getLogger(ReportService.class);
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IReportDao reportDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportDao = daoFactory.getReportDao();
        } catch (DBException e) {
            log.error(e);
        }
    }

    @Override
    public Integer calculateReportsNumber() throws DBException {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            reportDao = daoFactory.getReportDao();
            result = reportDao.calculateReportsNumber();
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
        }
        return result;
    }

    @Override
    public List<Reports> findAllReportsInDB() throws DBException {
        List<Reports> reports = new ArrayList<>();
        try {
            daoFactory.open();
            reportDao = daoFactory.getReportDao();
            reports = new ArrayList<>();
            reports = reportDao.findAllReportsInDB();
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return reports;
    }

    @Override
    public List<Reports> findReport(Integer first, Integer offset) throws DBException {
        List<Reports> reports = new ArrayList<>();
        try {
            daoFactory.open();
            reportDao = daoFactory.getReportDao();
            reports = new ArrayList<>();
            reports = reportDao.findReport(first, offset);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return reports;
    }

    @Override
    public List<Reports> findReportsByEvent(Events events) {
        List<Reports> reports = new ArrayList<>();
        try {
            daoFactory.open();
            reportDao = daoFactory.getReportDao();
            reports = new ArrayList<>();
            reports = reportDao.findReportsByEvent(events);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return reports;
    }

    @Override
    public Reports findReportsByReportId(Integer report_id) {
        Reports reports = null;
        try {
            daoFactory.open();
            reportDao = daoFactory.getReportDao();
            reports = reportDao.findReportsByReportId(report_id);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return reports;
    }

    @Override
    public synchronized boolean addReportToDB(Reports reports) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            reportDao = daoFactory.getReportDao();
            result = reportDao.addReportToDB(reports);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateReportInDB(Reports reports) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            reportDao = daoFactory.getReportDao();
            result = reportDao.updateReportInDB(reports);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteReportFromDB(Reports reports) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            reportDao = daoFactory.getReportDao();
            result = reportDao.deleteReportFromDB(reports);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }
}
