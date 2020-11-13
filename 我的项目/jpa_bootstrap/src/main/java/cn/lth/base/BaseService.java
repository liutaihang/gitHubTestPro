package cn.lth.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class BaseService <T extends BaseRepository<D>, D extends BaseEntity>{

    @Autowired
    private T dao;

    public D getOne(String id){
        return dao.getOne(id);
    }

    public D insert(D entity){
        entity.preInsert();
        return dao.save(entity);
    }
    public void del(D entity){
        dao.delete(entity);
    }

    public List<D> findList(){
        return dao.findAll();
    }

    public Page<D> findByList(Specification<D> spc, Pageable pageable){
        return dao.findAll(spc, pageable);
    }

    public Page<D> findByList(Example<D> example, Pageable pageable){
        return dao.findAll(example, pageable);
    }

    public List<D> findByList(Example<D> example, Sort sort){
        return dao.findAll(example, sort);
    }

    public List<D> findByList(Specification<D> spc, Sort sort){
        return dao.findAll(spc, sort);
    }

    public long count(){
        return dao.count();
    }

    public long count(Example<D> example){
        return dao.count(example);
    }

    public long count(Specification<D> spec){
        return dao.count(spec);
    }
}
