package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IReportPrepositionDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Report_preposition;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IReportPrepositionService;

import java.util.ArrayList;
import java.util.List;

public class ReportPrepositionService implements IReportPrepositionService {
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IReportPrepositionDao reportPrepositionDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportPrepositionDao = daoFactory.getReportPrepositionDao();
        } catch (DBException e) {
            //log
        }
    }

    @Override
    public Integer calculateReportPrepositionNumber() throws DBException {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            reportPrepositionDao = daoFactory.getReportPrepositionDao();
            result = reportPrepositionDao.calculateReportPrepositionNumber();
            daoFactory.commitTransaction();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Report_preposition> findAllReportPrepositionInDB() throws DBException {
        List<Report_preposition> report_prepositions = new ArrayList<>();
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportPrepositionDao = daoFactory.getReportPrepositionDao();
            report_prepositions = new ArrayList<>();
            report_prepositions = reportPrepositionDao.findAllReportPrepositionInDB();
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return report_prepositions;
    }

    @Override
    public List<Report_preposition> findReportPreposition(Integer first, Integer offset) throws DBException {
        List<Report_preposition> report_prepositions = new ArrayList<>();
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportPrepositionDao = daoFactory.getReportPrepositionDao();
            report_prepositions = new ArrayList<>();
            report_prepositions = reportPrepositionDao.findReportPreposition(first, offset);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return report_prepositions;
    }

    @Override
    public synchronized boolean addReportPrepositionToDB(Report_preposition report_preposition) {
        boolean result;
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportPrepositionDao = daoFactory.getReportPrepositionDao();
            result = reportPrepositionDao.addReportPrepositionToDB(report_preposition);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateReportPrepositionInDB(Report_preposition report_preposition) {
        boolean result;
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportPrepositionDao = daoFactory.getReportPrepositionDao();
            result = reportPrepositionDao.updateReportPrepositionInDB(report_preposition);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteReportPrepositionFromDB(Report_preposition report_preposition) {
        boolean result;
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportPrepositionDao = daoFactory.getReportPrepositionDao();
            result = reportPrepositionDao.deleteReportPrepositionFromDB(report_preposition);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean createReportWithPropositionDeletion(int id) {
        boolean result;
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportPrepositionDao = daoFactory.getReportPrepositionDao();
            result = reportPrepositionDao.createReportWithPropositionDeletion(id);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }
}
