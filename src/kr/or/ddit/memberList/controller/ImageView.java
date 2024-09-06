package kr.or.ddit.memberList.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/imageView.do")
public class ImageView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 파일 경로를 받기
        String filePath = req.getParameter("filepath");
        System.out.println("filePath = " + filePath);

        // 이미지 파일이 저장된 기본 경로 (D:/d_other)
        String uploadPath = "d:/d_other";

        // filePath에서 "upload_images/" 부분을 제거하고 실제 경로에 맞게 처리
        if (filePath.startsWith("upload_images/")) {
            filePath = filePath.substring("upload_images/".length());
        }

        File imageFile = new File(uploadPath, filePath);

        if (imageFile.exists()) { // 이미지 파일이 있을 경우
            // 파일 확장자에 따라 Content-Type 설정
            String fileName = imageFile.getName();
            String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

            switch (fileExt) {
                case "jpg":
                case "jpeg":
                    resp.setContentType("image/jpeg");
                    break;
                case "png":
                    resp.setContentType("image/png");
                    break;
                case "gif":
                    resp.setContentType("image/gif");
                    break;
                default:
                    resp.setContentType("application/octet-stream"); // 기타 파일
                    break;
            }

            // 서버에 저장된 파일을 클라이언트로 전송
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {
                bos = new BufferedOutputStream(resp.getOutputStream());
                bis = new BufferedInputStream(new FileInputStream(imageFile));

                byte[] temp = new byte[1024];
                int len;
                while ((len = bis.read(temp)) > 0) {
                    bos.write(temp, 0, len);
                }
                bos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) bis.close();
                if (bos != null) bos.close();
            }
        } else {
            // 파일이 없을 경우
            resp.setContentType("text/html; charset=utf-8");
            resp.getWriter().println("<h3>해당 파일이 존재하지 않습니다: " + filePath + "</h3>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
