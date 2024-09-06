package kr.or.ddit.filupload.service;

import kr.or.ddit.vo.FileInfoVO;

import java.util.List;

public interface IFileInfoService {
    public int insertFileInfo(FileInfoVO fileInfoVo);

    public List<FileInfoVO> getAllFileInfo();

    public FileInfoVO getFileInfo(int fileNo);
}
