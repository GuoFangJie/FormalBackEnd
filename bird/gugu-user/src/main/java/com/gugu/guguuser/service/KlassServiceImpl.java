package com.gugu.guguuser.service;

import com.gugu.gugumodel.dao.KlassDao;
import com.gugu.gugumodel.dao.KlassDaoImpl;
import com.gugu.gugumodel.pojo.entity.KlassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ren
 */
@Service
public class KlassServiceImpl implements KlassService {
    @Autowired
    KlassDao klassDao;
    @Override
    public Long newKlass(KlassEntity klassEntity) {
        return klassDao.newKlass(klassEntity);
    }
}
