package cn.lth.service;

import cn.lth.dto.MsgInfo;
import cn.lth.mongoDao.MsgInfoDao;
import cn.lth.util.JTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MsgInfoService {

    @Autowired
    private MsgInfoDao msgInfoDao;

    public Optional<MsgInfo> getOne(String id){
        return msgInfoDao.findById(id);
    }

    public List<MsgInfo> findSort(Sort sort){
        return msgInfoDao.findAll(sort);
    }

    public Page<MsgInfo> findAll(Pageable pageable){
        return msgInfoDao.findAll(pageable);
    }

    public List<MsgInfo> find(MsgInfo msgInfo){
        return msgInfoDao.findAll(Example.of(msgInfo));
    }

    public List<MsgInfo> findAll(){
        return msgInfoDao.findAll();
    }

    public MsgInfo save(MsgInfo msgInfo){
        msgInfo.setCreateTime(JTimeUtils.getTimestamp(JTimeUtils.format_1));
        return msgInfoDao.save(msgInfo);
    }
}
