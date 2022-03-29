package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IReportSpeakerDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Report_speakers;
import com.example.conference.entity.Reports;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IReportSpeakerService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ReportSpeakerService implements IReportSpeakerService {
    private static final Logger log = Logger.getLogger(ReportSpeakerService.class);
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IReportSpeakerDao reportSpeakerDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
        } catch (DBException e) {
            log.error(e);
        }
    }

    @Override
    public Integer calculateReportSpeakerNumber() throws DBException {
        Integer result = 0;
        try {
             daoFactory.beginTransaction();
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            result = reportSpeakerDao.calculateReportSpeakerNumber();
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
        }
        return result;
    }

    @Override
    public List<Report_speakers> findAllReportSpeakersInDB() throws DBException {
        List<Report_speakers> report_speakers = new ArrayList<>();
        try {
           daoFactory.open();
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            report_speakers = new ArrayList<>();
            report_speakers = reportSpeakerDao.findAllReportSpeakersInDB();
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return report_speakers;
    }

    @Override
    public List<Report_speakers> findReportSpeaker(Integer first, Integer offset) throws DBException {
        List<Report_speakers> report_speakers = new ArrayList<>();
        try {
            daoFactory.open();
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            report_speakers = new ArrayList<>();
            report_speakers = reportSpeakerDao.findReportSpeaker(first, offset);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return report_speakers;
    }

    @Override
    public Report_speakers findReportById(Integer id) {
        Report_speakers report_speakers = null;
        try {
            daoFactory.open();
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            report_speakers = reportSpeakerDao.findReportById(id);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return report_speakers;
    }

    @Override
    public synchronized boolean addReportSpeakersToDB(Report_speakers report_speakers) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            result = reportSpeakerDao.addReportSpeakersToDB(report_speakers);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateReportSpeakersInDB(Report_speakers report_speakers) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            result = reportSpeakerDao.updateReportSpeakersInDB(report_speakers);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteReportSpeakersFromDB(Integer report_id) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            result = reportSpeakerDao.deleteReportSpeakersFromDB(report_id);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean saveWithProposalsDeletion(Report_speakers reportTopicSpeaker) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            reportSpeakerDao = daoFactory.getReportSpeakersDao();
            result = reportSpeakerDao.saveWithProposalsDeletion(reportTopicSpeaker);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }
}
