package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IReportSpeakerDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Report_speakers;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IReportSpeakerService;

import java.util.ArrayList;
import java.util.List;

public class ReportSpeakerService implements IReportSpeakerService {
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IReportSpeakerDao reportSpeakerDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
        } catch (DBException e) {
            //log
        }
    }

    @Override
    public Integer calculateReportSpeakerNumber() throws DBException {
        Integer result = 0;
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            result = reportSpeakerDao.calculateReportSpeakerNumber();
            daoFactory.commitTransaction();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Report_speakers> findAllReportSpeakersInDB() throws DBException {
        List<Report_speakers> report_speakers = new ArrayList<>();
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            report_speakers = new ArrayList<>();
            report_speakers = reportSpeakerDao.findAllReportSpeakersInDB();
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return report_speakers;
    }

    @Override
    public List<Report_speakers> findReportSpeaker(Integer first, Integer offset) throws DBException {
        List<Report_speakers> report_speakers = new ArrayList<>();
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            report_speakers = new ArrayList<>();
            report_speakers = reportSpeakerDao.findReportSpeaker(first, offset);
            daoFactory.close();
        } catch (DBException e) {
            e.printStackTrace();
        }
        return report_speakers;
    }

    @Override
    public synchronized boolean addReportSpeakersToDB(Report_speakers report_speakers) {
        boolean result;
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            result = reportSpeakerDao.addReportSpeakersToDB(report_speakers);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateReportSpeakersInDB(Report_speakers report_speakers) {
        boolean result;
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            result = reportSpeakerDao.updateReportSpeakersInDB(report_speakers);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteReportSpeakersFromDB(Report_speakers report_speakers) {
        boolean result;
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            result = reportSpeakerDao.deleteReportSpeakersFromDB(report_speakers);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean saveWithProposalsDeletion(Report_speakers reportTopicSpeaker) {
        boolean result;
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            result = reportSpeakerDao.saveWithProposalsDeletion(reportTopicSpeaker);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            // e.printStackTrace();
            return false;
        }
        return result;
    }
}
