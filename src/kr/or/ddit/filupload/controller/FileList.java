package kr.or.ddit.filupload.controller;

import kr.or.ddit.filupload.service.FileInfoSerivceImpl;
import kr.or.ddit.filupload.service.IFileInfoService;
import kr.or.ddit.vo.FileInfoVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/fileList.do")
public class FileList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        IFileInfoService service = FileInfoSerivceImpl.getInstance();

        //파일 정보가 저장된 전체 자료를 DB에서 읽어온다
        List<FileInfoVO> fileList = service.getAllFileInfo();

        //가져온 파일 목록정보를 View페이지로 보낸다
        req.setAttribute("fileList", fileList);

        req.getRequestDispatcher("/basic/fileUpload/fileList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
