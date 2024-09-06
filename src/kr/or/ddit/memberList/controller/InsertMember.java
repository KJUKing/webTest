package kr.or.ddit.memberList.controller;

import kr.or.ddit.memberList.dao.IMemberService;
import kr.or.ddit.memberList.dao.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30,
        maxRequestSize = 1024 * 1024 * 50
)@WebServlet("/insertMember.do")
public class InsertMember extends HttpServlet {

    private static final String UPLOAD_DIR = "d:/d_other";  // 실제 저장 경로

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String memId = req.getParameter("mem_id");
        String memPass = req.getParameter("mem_pass");
        String memName = req.getParameter("mem_name");
        String memTel = req.getParameter("mem_tel");
        String memAddr = req.getParameter("mem_addr");

        // 사진 파일 처리
        Part part = req.getPart("mem_photo");
        String fileName = extractFileName(part);

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdir();  // 디렉토리 생성

        // 파일 저장 로직
        String saveFileName = null;
        if (fileName != null && !fileName.isEmpty()) {
            saveFileName = UUID.randomUUID().toString() + "_" + fileName;  // 파일 이름을 UUID로 저장
            part.write(UPLOAD_DIR + File.separator + saveFileName);    // 파일을 지정된 경로에 저장
        }

        // MemberVO에 회원 정보 저장
        MemberVO member = new MemberVO();
        member.setMem_id(memId);
        member.setMem_pass(memPass);
        member.setMem_name(memName);
        member.setMem_tel(memTel);
        member.setMem_addr(memAddr);
        member.setMem_photo(saveFileName);  // 저장된 파일 이름만 저장

        // 서비스 호출
        IMemberService service = MemberServiceImpl.getInstance();
        int result = service.insertMember(member);
        System.out.println("result = " + result);

        // 회원 목록 페이지로 리다이렉트
        resp.sendRedirect(req.getContextPath() + "/memberList.do");
    }

    // Part 객체에서 파일 이름 추출 메소드
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] items = contentDisposition.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;  // 파일 이름이 없을 경우 null 반환
    }
}


