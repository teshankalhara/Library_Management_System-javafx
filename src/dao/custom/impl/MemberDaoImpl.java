package dao.custom.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.CrudUtil;
import dao.custom.MemberDao;
import db.DBConnection;
import entity.Member;

public class MemberDaoImpl implements MemberDao {

    @Override
    public String save(Member member) throws Exception {
        String sql = "INSERT INTO members (name, email, phone_number) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql,
                    member.getName(),
                    member.getEmail(),
                    member.getPhoneNumber());
            if (stmt.executeUpdate() > 0) {
                return "Added";
            } else {
                return "Failed";
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public String update(Member member) throws Exception {
        String sql = "UPDATE members SET name = ?, email = ?, phone_number = ? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql,
                    member.getName(),
                    member.getEmail(),
                    member.getPhoneNumber(),
                    member.getId());
            if (stmt.executeUpdate() > 0) {
                return "Updated";
            } else {
                return "Failed";
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public String delete(Integer id) throws Exception {
        String sql = "DELETE FROM members WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, id);
            if (stmt.executeUpdate() > 0) {
                return "Deleted";
            } else {
                return "Failed";
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public Member get(Integer id) throws Exception {
        String sql = "SELECT * FROM members WHERE id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhoneNumber(rs.getString("phone_number"));
                return member;
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public ArrayList<Member> getAll() throws Exception {
        String sql = "SELECT * FROM members";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = DBConnection.getInstance().getConnection().prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhoneNumber(rs.getString("phone_number"));
                members.add(member);
            }
            return members;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public List<Member> findByEmail(String email) throws Exception {
        String sql = "SELECT * FROM members WHERE email LIKE ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = CrudUtil.getPreparedStatement(sql, "%" + email + "%");
            rs = stmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setPhoneNumber(rs.getString("phone_number"));
                members.add(member);
            }
            return members;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
