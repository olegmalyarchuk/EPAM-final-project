package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.ISpeakerPrepositionDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Speaker_preposition;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.ISpeakerPrepositionService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class SpeakerPrepositionService implements ISpeakerPrepositionService {
    private static final Logger log = Logger.getLogger(SpeakerPrepositionService.class);
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static ISpeakerPrepositionDao speakerPrepositionDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            speakerPrepositionDao = daoFactory.getSpeakerPrepositionDao();
        } catch (DBException e) {
            log.error(e);
        }
    }

    @Override
    public Integer calculateSpeakerPrepositionNumber() throws DBException {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            speakerPrepositionDao = daoFactory.getSpeakerPrepositionDao();
            result = speakerPrepositionDao.calculateSpeakerPrepositionNumber();
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
        }
        return result;
    }

    @Override
    public List<Speaker_preposition> findAllSpeakerPrepositionInDB() throws DBException {
        List<Speaker_preposition> speakerPrepositions = new ArrayList<>();
        try {
            daoFactory.open();
            speakerPrepositionDao = daoFactory.getSpeakerPrepositionDao();
            speakerPrepositions = new ArrayList<>();
            speakerPrepositions = speakerPrepositionDao.findAllSpeakerPrepositionInDB();
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return speakerPrepositions;
    }

    @Override
    public List<Speaker_preposition> findUSpeakerPreposition(Integer first, Integer offset) throws DBException {
        List<Speaker_preposition> speakerPrepositions = new ArrayList<>();
        try {
            daoFactory.open();
            speakerPrepositionDao = daoFactory.getSpeakerPrepositionDao();
            speakerPrepositions = new ArrayList<>();
            speakerPrepositions = speakerPrepositionDao.findUSpeakerPreposition(first, offset);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return speakerPrepositions;
    }


    @Override
    public List<Speaker_preposition> findAllByReportIdWithSpeaker(int report_id) {
        List<Speaker_preposition> speakerPrepositions = new ArrayList<>();
        try {
            daoFactory.open();
            speakerPrepositionDao = daoFactory.getSpeakerPrepositionDao();
            speakerPrepositions = new ArrayList<>();
            speakerPrepositions = speakerPrepositionDao.findAllByReportIdWithSpeaker(report_id);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return speakerPrepositions;
    }

    @Override
    public List<Integer> findAllSpeakerProposedReportIdsForEvent(int event_id, int speaker_id) {
        List<Integer> ids = new ArrayList<>();
        try {
            daoFactory.open();
            speakerPrepositionDao = daoFactory.getSpeakerPrepositionDao();
            ids = new ArrayList<>();
            ids = speakerPrepositionDao.findAllSpeakerProposedReportIdsForEvent(event_id, speaker_id);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return ids;
    }

    @Override
    public synchronized boolean addSpeakerPrepositionToDB(Speaker_preposition speaker_preposition) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            speakerPrepositionDao = daoFactory.getSpeakerPrepositionDao();
            result = speakerPrepositionDao.addSpeakerPrepositionToDB(speaker_preposition);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }


    @Override
    public synchronized boolean updateSpeakerPrepositionInDB(Speaker_preposition speaker_preposition) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            speakerPrepositionDao = daoFactory.getSpeakerPrepositionDao();
            result = speakerPrepositionDao.updateSpeakerPrepositionInDB(speaker_preposition);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteSpeakerPrepositionFromDB(Integer id) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            speakerPrepositionDao = daoFactory.getSpeakerPrepositionDao();
            result = speakerPrepositionDao.deleteSpeakerPrepositionFromDB(id);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteSpeakerPrepositionByReportIdFromDB(Integer role_id) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            speakerPrepositionDao = daoFactory.getSpeakerPrepositionDao();
            result = speakerPrepositionDao.deleteSpeakerPrepositionFromDB(role_id);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }
}
