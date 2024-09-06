package kr.or.ddit.filupload.dao;

import kr.or.ddit.vo.FileInfoVO;

import java.util.List;

public interface IFileInfoDao {
    public int insertFileInfo(FileInfoVO fileInfoVo);

    public List<FileInfoVO> getAllFileInfo();

    public FileInfoVO getFileInfo(int fileNo);
}
