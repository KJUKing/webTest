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

@WebServlet("/fileDownload.do")
public class FileDownload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");


        //파라미터로 넘어온 파일번호를 받는다.
        int fileNo = Integer.parseInt(req.getParameter("fileno"));

        //파일번호를 이용하여 DB에서 해당파일의 정보를 가져온다
        IFileInfoService service = FileInfoSerivceImpl.getInstance();
        FileInfoVO fileInfo = service.getFileInfo(fileNo);

        //업로드된 파일들이 저장될 파일 설정
        String uploadPath = "d:/d_other";

        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //------------------------------------------
        //다운 받을 파일 정보를 갖는 File객체 생성
        File downFile = new File(file, fileInfo.getSave_file_name());

        if (downFile.exists()) { //다운 받을 파일이 있을 때
            resp.setContentType("application/octet-stream; charset=utf-8");

            // Response객체의 Header에 'content-disposition'속성을 설정한다.
            String headerKey = "content-disposition";

            //이 헤더 속성에 다운받을 파일 이름을 설정한다. (클라이언트에 저장될 이름을 설정한다
            String headerValue = "attachment; filename*=UTF-8''" +
                    URLEncoder.encode(fileInfo.getOrigin_file_name(), "utf-8")
                            .replaceAll("\\+", "%20");
            resp.setHeader(headerKey, headerValue);

            //서버에 저장된 파일을 읽어서 클라이언트로 전송하기
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {
                //출력용 스트림 객체 생성 -> Response객체 이용
                bos = new BufferedOutputStream(resp.getOutputStream());

                //파일 입력용 스트림 객체 생성
                bis = new BufferedInputStream(new FileInputStream(downFile));

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
            resp.getWriter().println("<h3>" + fileInfo.getOrigin_file_name() + "파일이 존재하지 않습니다 </h3>");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
