package kr.or.ddit.session;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class SessionLoginDao {
	private static SessionLoginDao dao;
	private SessionLoginDao(){}
	public static SessionLoginDao getInstance() {
		if(dao == null) dao = new SessionLoginDao();
		return dao;
	}
	
	public MemberVO getLoginMember(MemberVO memVo) {
		MemberVO resVo = null;
		try(SqlSession session = MybatisUtil.getSqlSession();) {
			resVo = session.selectOne("member.getLoginMember", memVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resVo;
	}
}
