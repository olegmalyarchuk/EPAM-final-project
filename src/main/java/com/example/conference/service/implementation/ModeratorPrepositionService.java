package com.example.conference.service.implementation;

import com.example.conference.dao.DaoFactory;
import com.example.conference.dao.DataBaseSelector;
import com.example.conference.dao.IModeratorPrepositionDao;
import com.example.conference.dao.IUserDao;
import com.example.conference.entity.Moderator_preposition;
import com.example.conference.exceptions.DBException;
import com.example.conference.service.IModeratorPrepositionService;

import java.util.List;

public class ModeratorPrepositionService implements IModeratorPrepositionService {
    public static final DataBaseSelector source = DataBaseSelector.MY_SQL;
    private static DaoFactory daoFactory;
    public static IModeratorPrepositionDao moderatorPrepositionDao;

    static {
        try {
            daoFactory = DaoFactory.getDaoFactory(source);
            moderatorPrepositionDao = daoFactory.getModeratorPrepositionDao();
        } catch (DBException e) {
            //log
        }
    }

    @Override
    public Integer calculateModeratorPrepositionNumber() throws DBException {
        return moderatorPrepositionDao.calculateModeratorPrepositionNumber();
    }

    @Override
    public List<Moderator_preposition> findAllModeratorPrepositionInDB() throws DBException {
        return moderatorPrepositionDao.findAllModeratorPrepositionInDB();
    }

    @Override
    public List<Moderator_preposition> findModeratorPreposition(Integer first, Integer offset) throws DBException {
        return moderatorPrepositionDao.findModeratorPreposition(first, offset);
    }

    @Override
    public boolean addModeratorPrepositionToDB(Moderator_preposition moderator_preposition) {
        return moderatorPrepositionDao.addModeratorPrepositionToDB(moderator_preposition);
    }

    @Override
    public boolean updateModeratorPrepositionInDB(Moderator_preposition moderator_preposition) {
        return moderatorPrepositionDao.updateModeratorPrepositionInDB(moderator_preposition);
    }

    @Override
    public boolean deleteModeratorPrepositionFromDB(Moderator_preposition moderator_preposition) {
        return moderatorPrepositionDao.deleteModeratorPrepositionFromDB(moderator_preposition);
    }

    @Override
    public boolean deleteProposal(Moderator_preposition moderator_preposition) {
        return moderatorPrepositionDao.deleteProposal(moderator_preposition);
    }
}
