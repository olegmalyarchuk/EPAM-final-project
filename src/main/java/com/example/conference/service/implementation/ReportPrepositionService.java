package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IReportPrepositionDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Report_preposition;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IReportPrepositionService;

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
        return reportPrepositionDao.calculateReportPrepositionNumber();
    }

    @Override
    public List<Report_preposition> findAllReportPrepositionInDB() throws DBException {
        return reportPrepositionDao.findAllReportPrepositionInDB();
    }

    @Override
    public List<Report_preposition> findReportPreposition(Integer first, Integer offset) throws DBException {
        return reportPrepositionDao.findReportPreposition(first, offset);
    }

    @Override
    public boolean addReportPrepositionToDB(Report_preposition report_preposition) {
        return reportPrepositionDao.addReportPrepositionToDB(report_preposition);
    }

    @Override
    public boolean updateReportPrepositionInDB(Report_preposition report_preposition) {
        return reportPrepositionDao.updateReportPrepositionInDB(report_preposition);
    }

    @Override
    public boolean deleteReportPrepositionFromDB(Report_preposition report_preposition) {
        return reportPrepositionDao.deleteReportPrepositionFromDB(report_preposition);
    }

    @Override
    public boolean createReportWithPropositionDeletion(int id) {
        return reportPrepositionDao.createReportWithPropositionDeletion(id);
    }
}
