package dao.custom;

import java.util.List;

import dao.CrudDao;
import entity.Member;

public interface MemberDao extends CrudDao<Member, Integer> {
    List<Member> findByEmail(String email) throws Exception;
}
