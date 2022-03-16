package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IReportSpeakerDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Report_speakers;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IReportSpeakerService;

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
        return reportSpeakerDao.calculateReportSpeakerNumber();
    }

    @Override
    public List<Report_speakers> findAllReportSpeakersInDB() throws DBException {
        return reportSpeakerDao.findAllReportSpeakersInDB();
    }

    @Override
    public List<Report_speakers> findReportSpeaker(Integer first, Integer offset) throws DBException {
        return reportSpeakerDao.findReportSpeaker(first, offset);
    }

    @Override
    public boolean addReportSpeakersToDB(Report_speakers report_speakers) {
        return reportSpeakerDao.addReportSpeakersToDB(report_speakers);
    }

    @Override
    public boolean updateReportSpeakersInDB(Report_speakers report_speakers) {
        return reportSpeakerDao.updateReportSpeakersInDB(report_speakers);
    }

    @Override
    public boolean deleteReportSpeakersFromDB(Report_speakers report_speakers) {
        return reportSpeakerDao.deleteReportSpeakersFromDB(report_speakers);
    }

    @Override
    public boolean saveWithProposalsDeletion(Report_speakers reportTopicSpeaker) {
        return reportSpeakerDao.saveWithProposalsDeletion(reportTopicSpeaker);
    }
}
