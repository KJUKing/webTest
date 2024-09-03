package kr.or.ddit.session;

import kr.or.ddit.vo.MemberVO;

public class SessionLoginService {
	private SessionLoginDao dao;
	private static SessionLoginService service;
	private SessionLoginService() {
		dao = SessionLoginDao.getInstance();
	}
	public static SessionLoginService getInstance() {
		if(service == null) service = new SessionLoginService();
		return service;
	}
	
	public MemberVO getLoginMember(MemberVO memVo) {
		return dao.getLoginMember(memVo);
	}
}
