package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IModeratorPrepositionDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Moderator_preposition;
import com.example.conference.entity.User;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IModeratorPrepositionService;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ModeratorPrepositionService implements IModeratorPrepositionService {
    private static final Logger log = Logger.getLogger(ModeratorPrepositionService.class);
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IModeratorPrepositionDao moderatorPrepositionDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            moderatorPrepositionDao = daoFactory.getModeratorPrepositionDao();
        } catch (DBException e) {
            log.error(e);
        }
    }

    @Override
    public Integer calculateModeratorPrepositionNumber() throws DBException {
        Integer result = 0;
        try {
            daoFactory.beginTransaction();
            moderatorPrepositionDao = daoFactory.getModeratorPrepositionDao();
            result = moderatorPrepositionDao.calculateModeratorPrepositionNumber();
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
        }
        return result;
    }

    @Override
    public List<Moderator_preposition> findAllModeratorPrepositionInDB() throws DBException {
        List<Moderator_preposition> moderator_prepositions = new ArrayList<>();
        try {
            daoFactory.open();
            moderatorPrepositionDao = daoFactory.getModeratorPrepositionDao();
            moderator_prepositions = new ArrayList<>();
            moderator_prepositions = moderatorPrepositionDao.findAllModeratorPrepositionInDB();
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return moderator_prepositions;
    }

    @Override
    public List<Moderator_preposition> findModeratorPreposition(Integer first, Integer offset) throws DBException {
        List<Moderator_preposition> moderator_prepositions = new ArrayList<>();
        try {
            daoFactory.open();
            moderatorPrepositionDao = daoFactory.getModeratorPrepositionDao();
            moderator_prepositions = new ArrayList<>();
            moderator_prepositions = moderatorPrepositionDao.findModeratorPreposition(first, offset);
            daoFactory.close();
        } catch (DBException e) {
            log.error(e);
        }
        return moderator_prepositions;
    }

    @Override
    public synchronized boolean addModeratorPrepositionToDB(Moderator_preposition moderator_preposition) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            moderatorPrepositionDao = daoFactory.getModeratorPrepositionDao();
            result = moderatorPrepositionDao.addModeratorPrepositionToDB(moderator_preposition);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean updateModeratorPrepositionInDB(Moderator_preposition moderator_preposition) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            moderatorPrepositionDao = daoFactory.getModeratorPrepositionDao();
            result = moderatorPrepositionDao.updateModeratorPrepositionInDB(moderator_preposition);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteModeratorPrepositionFromDB(Integer report_id) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            moderatorPrepositionDao = daoFactory.getModeratorPrepositionDao();
            result = moderatorPrepositionDao.deleteModeratorPrepositionFromDB(report_id);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }

    @Override
    public synchronized boolean deleteProposal(Moderator_preposition moderator_preposition) {
        boolean result;
        try {
            daoFactory.beginTransaction();
            moderatorPrepositionDao = daoFactory.getModeratorPrepositionDao();
            result = moderatorPrepositionDao.deleteProposal(moderator_preposition);
            daoFactory.commitTransaction();
        } catch (DBException e) {
            log.error(e);
            return false;
        }
        return result;
    }
}
