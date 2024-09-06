package kr.or.ddit.filupload.controller;

import kr.or.ddit.filupload.service.FileInfoSerivceImpl;
import kr.or.ddit.filupload.service.IFileInfoService;
import kr.or.ddit.vo.FileInfoVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
    -servlet 3.0이상에서 파일 업로드를 하려명 서블릿에
    @MultipartConfig 어노테이션 설정해야함
    1) location : 업로드한 파일이 임시적으로 저장될 경로 지정(기본값 : "")
    2) fileSizeThreshold : 이 곳에 설정한 값보다 큰 파일이 전송되면 location에 지정한
                            임시 디렉토리에 저장한다. (단위 :byte 기본값 : 0 (무조건 임시 디렉토리 이용)
    3) maxFileSize : 한 개 파일의 최대크기 지정(단위 : byte, 기본값 : -1L (무제한))
    4) maxRequestSize : 서버로 전송되는 Request의 전체 데이터의 최대 크기
                        (모든 파일의 크기 + formData)(단위 : byte, 기본값 : -1L (무제한))

 */

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, maxFileSize = 1024 * 1024 * 30,
        maxRequestSize = 1024 * 1024 * 50
)
@WebServlet("/fileUpload.do")
public class FileUpload extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Get방식으로 호출하면 파일 입력 폼 문서가 나타나도록한다
        req.getRequestDispatcher("/basic/fileUpload/fileUploadForm.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //파일이 전송되어 오면 처리하기
        req.setCharacterEncoding("UTF-8");

        //업로드된 파일들이 저장될 파일 설정
        String uploadPath = "d:/d_other";

        File file = new File(uploadPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        // --------------------------------------
        // 파일이 아닌 일반 데이터들은 getParameter()메소드나
        // getParameterValues()메소드를 이용해서 구한다

        String userName = req.getParameter("userName");

        System.out.println("일반 파라미터 데이터 : " + userName);


        //-------------------------------------------------
        //수신 받은 파일 데이터를 처리하기
        //Upload한 파일이 여러개일 경우 Upload한 파일정보가 저장될 List객체 생성
        List<FileInfoVO> fileList = new ArrayList<FileInfoVO>();
        // - servlet 3.0이상에서 새롭게 추가된 upload용 메소드
        // 1) request.getParts() => 전체 part객체를 Collection에 담아서 반환
        // 2) request.getParts("이름") => 지정된 '이름'을 갖는 개별 part객체를 반환한다
        //                  '이름'은 <form>태그안의 입력요소의 name속성값으로 구별한다

        //전체 Part객체 개수 만큼 반복 처리
        for(Part part : req.getParts()) {

            //upload한 파일명 구하기
            String originFileName = extractFileName(part);
            //찾은 파일명이 빈문자열일때 이것은 파일이 아닌 일반 파라미터 데이터
            if (!"".equals(originFileName)) {//파일인지 검사한다
                FileInfoVO fileVo = new FileInfoVO();

                fileVo.setFile_writer(userName);    //작성자 저장
                fileVo.setOrigin_file_name(originFileName); // 실제 파일명저장

                // 실제 저장되는 파일 이름이 중복되는 것을 방지하기 위해서 UUID클래스를 이용하여
                // 저장할 파일명을 만든다.
                String saveFileName = UUID.randomUUID().toString() + "_" + originFileName;
                fileVo.setSave_file_name(saveFileName); // 실제 저장할 파일명 저장

                // 전송된 파일의 크기 part.getSize()메소드로 구할 수 있다.
                fileVo.setFile_size(part.getSize());


                //--------------------------------------------------
                // part.write()메소드를 이용하여 upload된 파일을 지정된 폴더에 저장한다
                try {
                    part.write(uploadPath + File.separator + saveFileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // List에 1개의 파일 정보 추가
                fileList.add(fileVo);
            }//if문 끝

            //List에 추가된 파일 정보를 DB에 저장한다

            IFileInfoService service = FileInfoSerivceImpl.getInstance();

            for (FileInfoVO fvo : fileList) {
                service.insertFileInfo(fvo);
            }


        }
        //저장이 완료된 후에 파일목록 보여주기
        resp.sendRedirect(req.getContextPath() + "/fileList.do");

    }

    // Part구조안에서 파일명을 찾는 메소드
    private String extractFileName(Part part) {
        String fileName = "";

        String content = part.getHeader("content-disposition");
        String[] items = content.split(";");

        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                fileName = item.substring(item.indexOf('=') + 1).trim().replace("\"", "");

            }
        }
        return  fileName;
    }

    //-------------------------------------
    /*
        - Part구조
        1) 파일이 아닌 일반 파라미터 데이터일경우
        -------1283a9d8ad78fd8aq0 -> Part를 구분한 구준선
    content-disposition : form-data; name="username" => 파라미터 명이된다
                                           => 빈줄
         hong                              => 파라미터 값

     */
    /*
    ---------------------182739128379 -> Part를 구분한 구분선
    content-disposition : form-data; name="upFile1"; filename="test1.txt"
    content-type : text/plain                         ==> 파일의종류
                                                      ==> 빈줄
    abcd1234안녕                                       ==> 파일의 내용

     */
}



