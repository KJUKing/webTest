package kr.or.ddit.filupload.service;

import kr.or.ddit.filupload.dao.FileInfoDaoImpl;
import kr.or.ddit.filupload.dao.IFileInfoDao;
import kr.or.ddit.vo.FileInfoVO;

import java.util.Collections;
import java.util.List;

public class FileInfoSerivceImpl implements IFileInfoService {
    private IFileInfoDao dao;

    private static FileInfoSerivceImpl service;
    private FileInfoSerivceImpl() {
        dao = FileInfoDaoImpl.getInstance();
    }

    public static FileInfoSerivceImpl getInstance() {
        if (service == null) {
            service = new FileInfoSerivceImpl();
        }
        return service;
    }

    @Override
    public int insertFileInfo(FileInfoVO fileInfoVo) {
        return dao.insertFileInfo(fileInfoVo);
    }

    @Override
    public List<FileInfoVO> getAllFileInfo() {
        return dao.getAllFileInfo();
    }

    @Override
    public FileInfoVO getFileInfo(int fileNo) {
        return dao.getFileInfo(fileNo);
    }
}
