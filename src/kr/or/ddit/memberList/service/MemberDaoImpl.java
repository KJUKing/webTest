package kr.or.ddit.memberList.service;
import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MemberDaoImpl implements IMemberDao {

    private static MemberDaoImpl dao;

    private MemberDaoImpl() {

    }

    public static MemberDaoImpl getInstance() {
        if (dao == null) {
            dao = new MemberDaoImpl();
        }
        return dao;
    }

    @Override
    public List<MemberVO> selectAllMember() {
        SqlSession session = null;
        List<MemberVO> memberList = null;
        try {
            session = MybatisUtil.getSqlSession();
            memberList =  session.selectList("member.selectAllMember");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }

        return memberList;
    }

    @Override
    public int insertMember(MemberVO memberVO) {
        SqlSession session = null;
        int cnt = 0;

        try {
            session = MybatisUtil.getSqlSession();
            cnt = session.insert("member.insertMember", memberVO);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.commit();
            session.close();
        }
        return cnt;
    }

    @Override
    public String idCheck(String id) {
        //리턴변수선언
        SqlSession session = null;
        String userId = null;

        try {
            //실행
            session = MybatisUtil.getSqlSession();
            userId = session.selectOne("member.idCheck", id);

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            session.commit();
            session.close();
        }
        //리턴
        return userId;
    }

    @Override
    public int updateMember(MemberVO memberVO) {
        return 0;
    }

    @Override
    public int deleteMember(int id) {
        return 0;
    }

    @Override
    public MemberVO memberDetail(String id) {
        SqlSession session = null;
        MemberVO member = null;
        try {
            session = MybatisUtil.getSqlSession();
            member =  session.selectOne("member.memberDetail", id);
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return member;
    }
}
