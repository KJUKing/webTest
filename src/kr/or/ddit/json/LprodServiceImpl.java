package kr.or.ddit.json;

import kr.or.ddit.vo.LprodVO;

import java.util.List;

public class LprodServiceImpl implements ILprodService{


    private static LprodServiceImpl service;
    private ILprodDao dao;

    private LprodServiceImpl() {
        dao = LprodDaoImpl.getInstance();
    }

    public static LprodServiceImpl getInstance() {
        if (service == null) {
            service = new LprodServiceImpl();
        }
        return service;
    }

    @Override
    public List<LprodVO> selectLprod() {
        return dao.selectLprod();
    }
}
