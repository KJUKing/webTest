package kr.or.ddit.filupload.dao;


import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.FileInfoVO;
import org.apache.ibatis.session.SqlSession;

import java.util.Collections;
import java.util.List;

public class FileInfoDaoImpl implements IFileInfoDao {

    private static FileInfoDaoImpl dao;

    private FileInfoDaoImpl() {
    }

    public static FileInfoDaoImpl getInstance() {
        if (dao == null) {
            dao = new FileInfoDaoImpl();
        }
        return dao;
    }


    @Override
    public int insertFileInfo(FileInfoVO fileInfoVo) {
        SqlSession session = null;
        int cnt = 0;

        try {
            session = MybatisUtil.getSqlSession();
            cnt = session.insert("fileinfo.insertFileInfo", fileInfoVo);
            if (cnt > 0) {
                session.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return cnt;
    }

    @Override
    public List<FileInfoVO> getAllFileInfo() {
        SqlSession session = null;
        List<FileInfoVO> fileList = null;

        try {
            session = MybatisUtil.getSqlSession();
            fileList = session.selectList("fileinfo.getAllFileInfo");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return fileList;
    }

    @Override
    public FileInfoVO getFileInfo(int fileNo) {
        SqlSession session = null;
        FileInfoVO fileVo = null;

        try {
            session = MybatisUtil.getSqlSession();
            fileVo = session.selectOne("fileinfo.getFileInfo", fileNo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return fileVo;
    }
}
