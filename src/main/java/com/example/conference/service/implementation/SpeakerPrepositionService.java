package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.ISpeakerPrepositionDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Speaker_preposition;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.ISpeakerPrepositionService;

import java.util.List;

public class SpeakerPrepositionService implements ISpeakerPrepositionService {
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static ISpeakerPrepositionDao speakerPrepositionDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            speakerPrepositionDao = daoFactory.getSpeakerPrepositionDao();
        } catch (DBException e) {
            //log
        }
    }

    @Override
    public Integer calculateSpeakerPrepositionNumber() throws DBException {
        return speakerPrepositionDao.calculateSpeakerPrepositionNumber();
    }

    @Override
    public List<Speaker_preposition> findAllSpeakerPrepositionInDB() throws DBException {
        return speakerPrepositionDao.findAllSpeakerPrepositionInDB();
    }

    @Override
    public List<Speaker_preposition> findUSpeakerPreposition(Integer first, Integer offset) throws DBException {
        return speakerPrepositionDao.findUSpeakerPreposition(first, offset);
    }

    @Override
    public boolean addSpeakerPrepositionToDB(Speaker_preposition speaker_preposition) {
        return speakerPrepositionDao.addSpeakerPrepositionToDB(speaker_preposition);
    }

    @Override
    public boolean updateSpeakerPrepositionInDB(Speaker_preposition speaker_preposition) {
        return speakerPrepositionDao.updateSpeakerPrepositionInDB(speaker_preposition);
    }

    @Override
    public boolean deleteSpeakerPrepositionFromDB(Speaker_preposition speaker_preposition) {
        return speakerPrepositionDao.deleteSpeakerPrepositionFromDB(speaker_preposition);
    }

    @Override
    public List<Speaker_preposition> findAllByReportIdWithSpeaker(int report_id) {
        return speakerPrepositionDao.findAllByReportIdWithSpeaker(report_id);
    }

    @Override
    public List<Integer> findAllSpeakerProposedReportIdsForEvent(int event_id, int speaker_id) {
        return speakerPrepositionDao.findAllSpeakerProposedReportIdsForEvent(event_id, speaker_id);
    }
}
