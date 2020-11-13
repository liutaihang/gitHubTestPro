package cn.lth.service;

import cn.lth.base.BaseService;
import cn.lth.dao.UserRepository;
import cn.lth.dto.SysUser;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class SysUserService extends BaseService<UserRepository, SysUser>
//        implements UserDetailsService
{
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(SysUserService.class);


//    @Autowired
//    private PasswordEncoder passwordEncoder;


    /**
     * 判断用户名是否已存在，
     *
     * @param userName
     * @return true 存在，false 不存在
     */
    public boolean existsUserName(String userName){
        Specification<SysUser> search = new Specification<SysUser>() {
            List<Predicate> predicates = Lists.newArrayList();
            @Override
            public Predicate toPredicate(Root<SysUser> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                predicates.add(cb.equal(root.get("username"), userName));
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return count(search) > 0;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // 判断用户是否存在
//
//        //获取用户权限代码
//        List<SimpleGrantedAuthority> authorityList = new ArrayList<SimpleGrantedAuthority>(){
//            {
//                add(new SimpleGrantedAuthority("123"));
//            }
//        };
//        DetailUser user = new DetailUser(username, passwordEncoder.encode("123456"), authorityList);
//        log.info("登录用户：{}", JSON.toJSONString(user));
//        return user;
//    }
}
