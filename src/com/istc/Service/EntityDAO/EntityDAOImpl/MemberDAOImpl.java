package com.istc.Service.EntityDAO.EntityDAOImpl;

import com.istc.Entities.Entity.Member;
import com.istc.Service.EntityDAO.EntityDAOInterfaces.MemberDAO;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lurui on 2017/2/4 0004.
 */
@Repository("memberDAO")
public class MemberDAOImpl<E extends Member, PK extends Serializable> extends PersonDAOImpl<Member, String> implements MemberDAO<E, PK>{
    @Override
    public void save(E[] members) {
        this.save(members);
    }

    @Override
    public Member get(String id){
        return (Member)this.getSession().createQuery("from Member m where m.id =:id").setParameter("id", id).uniqueResult();
    }

    public Member[] get(String[] ids){
        StringBuilder strb = new StringBuilder("from Member m where m.id = ").append(ids[0]);
        for(int i = 1; i < ids.length; i++)
            strb.append(" and m.id = ").append(ids[i]);
        List<Member> list = (List<Member>)this.getSession().createQuery(strb.toString()).list();
        if(list == null || list.size() <= 0)return null;
        Member[] members = new Member[list.size()];
        for(int i = 0;i < members.length; i++)
            members[i] = list.get(i);
        return members;
    }
}
