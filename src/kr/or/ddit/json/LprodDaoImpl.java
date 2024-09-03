package kr.or.ddit.json;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class LprodDaoImpl implements ILprodDao {

    private static LprodDaoImpl dao;

    public LprodDaoImpl() {
    }

    public static LprodDaoImpl getInstance() {
        if (dao == null) {
            dao = new LprodDaoImpl();
        }
        return dao;
    }

    @Override
    public List<LprodVO> selectLprod() {
        SqlSession session = null;
        List<LprodVO> lprodList = null;
        try {
            session = MybatisUtil.getSqlSession();
            lprodList =  session.selectList("lprod.selectLprod");
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return lprodList;
    }
}
