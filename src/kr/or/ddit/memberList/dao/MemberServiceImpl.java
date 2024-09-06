package kr.or.ddit.memberList.dao;
import kr.or.ddit.memberList.service.IMemberDao;
import kr.or.ddit.memberList.service.MemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

import java.util.Collections;
import java.util.List;

public class MemberServiceImpl implements IMemberService{

    private static MemberServiceImpl service;
    private IMemberDao dao;
    private MemberServiceImpl() {
        dao = MemberDaoImpl.getInstance();
    }

    public static MemberServiceImpl getInstance() {
        if (service == null) {
            service = new MemberServiceImpl();
        }
        return service;
    }
    @Override
    public List<MemberVO> selectAllMember() {
        return dao.selectAllMember();
    }

    @Override
    public int insertMember(MemberVO memberVO) {
        return dao.insertMember(memberVO);
    }

    @Override
    public String idCheck(String id) {
        return dao.idCheck(id);
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
        return dao.memberDetail(id);
    }
}
