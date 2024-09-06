package kr.or.ddit.filupload.controller;

import kr.or.ddit.filupload.service.FileInfoSerivceImpl;
import kr.or.ddit.filupload.service.IFileInfoService;
import kr.or.ddit.vo.FileInfoVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@WebServlet("/images/imageView.do")
public class ImageView extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //파일 번호 받기
        int fileNo = Integer.parseInt(req.getParameter("fileno"));
        System.out.println("fileNo = " + fileNo);
        //DB에서 파일 정보 가져오기
        IFileInfoService service = FileInfoSerivceImpl.getInstance();
        FileInfoVO fileVo = service.getFileInfo(fileNo);

        System.out.println("fileVo.getFile_no() = " + fileVo.getFile_no());

        //이미지 파일 이 저장된 폴더 설정
        String uploadPath = "d:/d_other";

        File file = new File(uploadPath);

        //저장될 폴더가 없으면 새로 생성한다
        if (!file.exists()) {
            file.mkdirs();
        }
        File imageFile = new File(file, fileVo.getSave_file_name());

        if (imageFile.exists()) { //다운 받을 파일이 있을 때
            //서버에 저장된 파일을 읽어서 클라이언트로 전송하기
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {
                //출력용 스트림 객체 생성 -> Response객체 이용
                bos = new BufferedOutputStream(resp.getOutputStream());

                //파일 입력용 스트림 객체 생성
                bis = new BufferedInputStream(new FileInputStream(imageFile));

                byte[] temp = new byte[1024];
                int len = 0;

                while ((len = bis.read(temp)) > 0) {
                    bos.write(temp, 0, len);
                }
                bos.flush();

            }catch (Exception e) {
                System.out.println("입출력 오류 : " + e.getMessage());

            }finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {//다운 받은 파일이 없을때
            resp.setContentType("text/html; charset=utf-8");
            resp.getWriter().println("<h3>" + fileVo.getOrigin_file_name() + "파일이 존재하지 않습니다 </h3>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
