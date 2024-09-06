package kr.or.ddit.memberList.service;

import kr.or.ddit.vo.MemberVO;

import java.util.List;

public interface IMemberDao {

    public List<MemberVO> selectAllMember();

    public int insertMember(MemberVO memberVO);

    public String idCheck(String id);

    public int updateMember(MemberVO memberVO);

    public int deleteMember(int id);

    public MemberVO memberDetail(String id);
}
