package com.controller.approval;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dto.AppDocFormDTO;
import com.dto.AppDocumentDTO;
import com.dto.AppFileDTO;
import com.dto.AppPageInfoDTO;
import com.dto.AppPaging;
import com.dto.AppReferDTO;
import com.dto.AppSearchConditionDTO;
import com.dto.ApprovalDTO;
import com.dto.MemberDTO;
import com.service.AppReferService;
import com.service.ApprovalFileService;
import com.service.ApprovalService;
import com.service.SaveDocFormService;

@Controller
public class SaveDocFormController {
	
	@Autowired
	SaveDocFormService service;

	@Autowired
	ApprovalFileService fService;
	
	@Autowired
	ApprovalService appService;
	
	@Autowired
	AppReferService refService;
	
	//문서 양식 선택: 품의서, 휴가신청서 등
	@RequestMapping("/docFormName")
	public String DocFormName( @RequestParam("form_name") String form_name, 
								HttpSession session) {
		AppDocFormDTO docForm = service.docFormName(form_name); //문서 이름 보내고, 문서 양식 가져오기
	
		session.setAttribute("form", docForm);
		
		return "app_docForm";
	}
	
	//결재자, 참조자 모달창: 전체 멤버 정보 출력
	@RequestMapping(value="/approverSelect", method = RequestMethod.GET)
	@ResponseBody
	public List<ApprovalDTO> approverSelect(HttpSession session) {
		
		List<ApprovalDTO> list = service.selectAllMemberInfo();
		System.out.println("결재자 or 참조자 정보 출력 : " + list);
		
		return list; 
	}
	
	//결재자, 참조자 모달창: 이름, 부서 등 조건 검색하기
	@RequestMapping(value="/searchMember", method = RequestMethod.GET)
	@ResponseBody
	public List<ApprovalDTO> searchMember(HttpSession session, AppSearchConditionDTO search) {
	
		List<ApprovalDTO> list = service.searchModalMemberInfo(search);
		System.out.println("결재자 or 참조자 정보 출력 : " + list);
		
		return list;
	}
	
	//기안문서함, 결재문서함: 본인이 기안한 문서의 리스트가 출력
	@RequestMapping(value ="/draftList")
	public String draftList( HttpSession session, AppDocumentDTO doc, ApprovalDTO app,
							@RequestParam(value="parameter", required = false) String parameter,
							@RequestParam(value="docStatus", required = false) String doc_status,
							@RequestParam(value="page", required = false) Integer page) {	
		if(parameter.equals("temp")) {
			doc_status = "임시";
		} else if(doc_status == null) {
			doc_status = "전체";
		}
		int member_num = (int)((MemberDTO)session.getAttribute("login")).getMember_num(); 
		
		int currentPage = (page != null) ? page : 1; //page가 존재: page, page 존재 않을 때: 1페이지
		int totalCount = 0; 
		AppPageInfoDTO api = null; //페이징 정보
		
		List<AppDocumentDTO> appDocList = null;
		String returnResult = null;
		if(parameter.equals("draft") || parameter.equals("temp")) { //기안 문서함, 임시 저장함
			doc.setDoc_status(doc_status);
			doc.setMember_num(member_num);			
			
			totalCount = service.getListCount(doc); //전체 게시글 개수
			api = AppPaging.getAppPageInfo(currentPage, totalCount); //현재 페이지, 전체 게시글 개수
			
			appDocList = service.saveDocFormList(doc, api);
			
			System.out.println("============ 기안문서, 임시문서함 리스트 : " + appDocList);
			
			if(parameter.equals("draft")) {
				returnResult = "app_draftList";
			} else if(parameter.equals("temp")){
				returnResult = "app_tempList";
			}
			
		} else if(parameter.equals("app")) { //결재문서함
			doc.setDoc_status(doc_status);
			doc.setMember_num(member_num); //결재라인에 올라온 사람에게 결재 순번(대기)에 맞게 결재문이 올라옴 => 결재자 사원번호로 조회	
			
			totalCount = service.getListCountApp(doc); //전체 게시글 개수
			api = AppPaging.getAppPageInfo(currentPage, totalCount); //현재 페이지, 전체 게시글 개수
			
			appDocList = service.selectListAppDoc(doc, api);
			
			System.out.println("============ 결재 문서함 리스트 : " + appDocList);
			
			returnResult = "app_appList";			
		}
		
		session.setAttribute("appDocList", appDocList); //기안한 문서
		session.setAttribute("api", api);
		session.setAttribute("pageStatus", doc_status); //페이징 처리시 필요한 문서 상태
		
		return returnResult;
	}
	
	//검색한 리스트 가져오기
	@RequestMapping(value="/searchConditionList")
	public String searchCondicionList( HttpSession session, 
										AppDocumentDTO doc, AppSearchConditionDTO search,
										@RequestParam(value="parameter", required = false) String parameter,
										@RequestParam(value="page", required = false) Integer page ) {	
		if(parameter.equals("temp")) {
			search.setType("임시");
		} else if(parameter.equals("draft")) {
			search.setType("기안");
		}
		
		int member_num = (int)((MemberDTO)session.getAttribute("login")).getMember_num(); 
		search.setMember_num(member_num);
		
		System.out.println(" >>>> searchsearch : " + search);
				
		int currentPage = (page != null) ? page : 1; //page가 존재: page, page 존재 않을 때: 1페이지
		int totalCount = 0;
		AppPageInfoDTO api = null; //페이징 정보
		List<AppDocumentDTO> searchDoc = null; //문서 리스트
		String returnResult = null; //
		
		if(parameter.equals("draft") || parameter.equals("temp")) { //기안 문서함
			System.out.println("parameter : " + parameter);
			
			totalCount = service.searchDraftCount(search);
			System.out.println("totalCount : " + totalCount);
			api = AppPaging.getAppPageInfo(currentPage, totalCount); //현재 페이지, 전체 게시글 개수
			System.out.println("api : " + api);
			searchDoc = service.allSearchDraft(search, api);
			
			System.out.println("searchDoc : " + searchDoc);
			if(parameter.equals("draft")) {
				returnResult = "app_draftList";
			} 
			else if(parameter.equals("temp")) {
				returnResult = "app_tempList";
			}
		} 
		else if(parameter.equals("app")) { //결재 문서함
			totalCount = service.searchAppCount(search);
			System.out.println("totalCount : " + totalCount);
			api = AppPaging.getAppPageInfo(currentPage, totalCount); //현재 페이지, 전체 게시글 개수
			System.out.println("api : " + api);
			searchDoc = service.allSearchApp(search, api);
			System.out.println("search : " + searchDoc);
			
			returnResult = "app_appList";
		}
			
		session.setAttribute("appDocList", searchDoc);
		session.setAttribute("search", search);
		session.setAttribute("api", api);
		
		return returnResult;
	}

	//결재요청, 임시저장, 반려문서 재상신(결재요청) : 결재 시, 문서 저장
	@RequestMapping(value ="/SaveDocForm", method= RequestMethod.POST)
	public String SaveDocForm( AppDocumentDTO doc, ApprovalDTO app,
							   AppReferDTO ref, HttpSession session, 
							   @RequestParam(value="type", required = false) String type,
							   @RequestParam(value="parameter", required = false) String parameter,
							   @RequestParam(value="refMemNum", required = false) String refMemNum,  
							   @RequestParam(value="appMemNum", required = false) String appMemNum,
							   @RequestParam(value="upload_file", required=false) MultipartFile multipartFile,
							   HttpServletRequest request) {
		System.out.println(" =========== 결재하기 =========== SaveDocForm : " + doc);
		System.out.println("refMemNum: " + refMemNum);
		System.out.println("appMemNum: " + appMemNum);
		System.out.println("type: " + type);
		System.out.println("parameter: " + parameter);
		System.out.println("multipartFile: " + multipartFile);
		System.out.println(" =========== 결재하기 =========== SaveDocForm");
		
		if(parameter.equals("Doc") || parameter.equals("rejDoc") || parameter.equals("tempRedraft")) { 
			doc.setDoc_status("대기"); //기안 문서, 반려 문서, 임시저장 문서: 결재 요청
		} else if(parameter.equals("Temporary") || parameter.equals("RejTemp") || parameter.equals("tempTemp") ) { 
			doc.setDoc_status("임시"); //임시 저장, 임시저장함의 임시저장, 반려 문서 임시 저장
		} 

		//기안 문서 저장
        int docResult = service.saveDocForm(doc);
        System.out.println("문서 저장 완료 : " + docResult);
        
        ///////파일 첨부   
        int fileResult = 0;

        if(multipartFile != null && !multipartFile.getOriginalFilename().equals("")) {
	        String realPath = "C:/mail_upload";
	        String fileName = multipartFile.getOriginalFilename(); //사용자 지정 파일 이름
			UUID uuid = UUID.randomUUID(); //파일 이름 중복 방지를 위한 식별자 
			String fileReName = uuid+"_"+ fileName; //식별자 이름 + 사용자 지정파일 이름으로 파일이름 중복 방지
			
			//파일 I.O처리
			File saveFile = new File(realPath, fileReName);
			try {
				multipartFile.transferTo(saveFile);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("IO에러");
			}
				
			//DB에 넘겨줄 fileDTO
			AppFileDTO file = new AppFileDTO(); // FileDTO 객체를 생성
			if(realPath != null && !realPath.equals("")) {
				file.setFile_name(fileName);
				file.setFile_path(realPath);
				file.setFile_rename(fileReName);
				file.setDoc_no(0); //doc_no = 0일때, SEQ_DOC.CURRVAL;
				file.setFile_no(0);
				
				fileResult = fService.registerFile(file);
			} else {
				fileResult = 0;
			}
        }
		System.out.println("파일 저장 완료? : " + fileResult);
        
        //결재자 등록
        int appResult = 0;
		String [] appArray = appMemNum.split(",");

		if( !appMemNum.isEmpty() || parameter.equals("Temporary") || parameter.equals("Doc") ) { 
			//!appMemNum.isEmpty(): appMemNum가 비어있지 않은 경우 == 결재자 새로 선택한 경우
			for(int i = 0; i < appArray.length; i++) {
				System.out.println("!appMemNum.isEmpty()");
				app.setMember_num(Integer.parseInt(appArray[i])); //결재자 사원번호
				app.setDoc_no(0); //새로 만들어진 문서 번호에 문서를 저장
				System.out.println("결재자 사원 번호 : " + app.getMember_num());
				app.setApp_level(i+1); //결재자 순번: 1번부터 순서대로
				if(!parameter.equals("Temporary")) {
					if(i == 0) { //첫번째 결재자인 경우
						app.setApp_status("대기");; //첫 번째 결재자는 대기
					} else {
						app.setApp_status("예정"); //두 번째 결재자부터 결재 예정
					}	
				} else if(parameter.equals("Temporary")) {
					app.setApp_status("임시");
				}
				appResult = appService.registerAppMem(app); //결재자 등록
			}
		} else if ( appMemNum.isEmpty() || parameter.equals("tempRedraft") || parameter.equals("tempTemp")) { 
			//appMemNum.isEmpty(): appMemNum 비어있는 경우 == 반려 문서 재상신인데 결재자 재선택하지 않은 경우(결재자 그대로)			
			List<ApprovalDTO> appList = appService.searchAppMem(doc.getDoc_no()); //결재자 정보 조회
			System.out.println("appMemNum.isEmpty()");
			if( !appList.isEmpty() ) { //결재자 존재
				for(int i = 0; i < appList.size(); i++) {
					app.setDoc_no(0); //문서번호가 0일 때, 새로 만들어진 문서 번호에 문서를 저장
					app.setMember_num( appList.get(i).getMember_num() );
					app.setApp_level(i+1);
					
					if(parameter.equals("RejTem") || parameter.equals("tempTemp")) {
						app.setApp_status("임시");
					}
					else { //임시 저장이 아닌 경우
						if(i == 0) {
							app.setApp_status("대기"); //첫 번째 결재자는 대기
						} else {
							app.setApp_status("예정"); //두 번째 결재자부터는 예정
						}
					}
					appResult = appService.registerAppMem(app); //결재자 등록
				}
			}
		}
		
		//참조자 등록
		int refResult = 0;
		if( !refMemNum.isEmpty() ) { //참조자가 있는 경우
			String[] refArray = refMemNum.split(","); //배열에 참조자 넣기
			for(int i = 0; i < refArray.length; i++) {
				ref.setMember_num(Integer.parseInt(refArray[i])); //참조자 사원번호
				
				if( !refMemNum.equals("Temporary")) {
					ref.setRef_status("참조");
				} else {
					ref.setRef_status("임시");
				}
				refResult = refService.registerRefMem(ref); // 참조자 등록
			}
		} else {
			refResult = 0;
		}
        
		
		String returnResult = null;
		if(parameter.equals("Doc") || parameter.equals("rejDoc") || parameter.equals("tempRedraft")) {
			returnResult = "draft";
		} else if(parameter.equals("Temporary") || parameter.equals("tempTemp") || parameter.equals("RejTemp")) {
			returnResult = "temp";
		}
		
		return "redirect: draftList?parameter=" + returnResult; //기안, 결재, 임시 결재함 문서
	}
	
	//임시 저장 수정 화면에서 선택했던 파일 삭제
	@RequestMapping(value="uploadFileDelete", method=RequestMethod.GET)
	public String uploadFileDelete(@RequestParam(value = "filePath", required = false) String file_path, 
										@RequestParam(value = "docNo", required = false) int doc_no, 
										HttpServletRequest request){
		
		//파일 삭제하기
		File deleteFile = new File(file_path); //저장 폴더 선택
		if(deleteFile.exists()) { //파일이 존재하면
			deleteFile.delete(); // 파일 삭제
		}
		
		int fileResult = fService.removeFile(doc_no);
		
		String docStatus = null;
		try {
			docStatus = URLEncoder.encode("임시", "UTF-8"); //한글 파라미터 깨짐 방지
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return "redirect: clickDocContent?docNo=" + doc_no + "&type=temp" + "&docStatus=" + docStatus;
	}	
		

	//상신취소 및 임시저장 삭제
	@RequestMapping(value ="/draftDocCancel")
	public String draftDocCancel(@RequestParam(value="docNo", required = false) int doc_no,
							     @RequestParam(value="type", required = false) String type,
								 AppFileDTO file) {
        //기안된 문서 삭제
	    int docResult = service.draftDocCancel(doc_no);
        
	    //파일 있다면 삭제
	    int fileResult = 0;
        if( file.getFile_path() != null ) {
        	fileResult = fService.removeFile(doc_no);
        }
        
        //결재자 삭제
        int appResult = appService.draftAppMemCancel(doc_no);
        
        //참조자 삭제
        int refResult = appService.draftRefMemCancel(doc_no);
        
		return "redirect: draftList?parameter=" + type;
	}
		
	//결재 승인/반려
	@RequestMapping(value ="/approveAppStatus")
	public String approveAppStatus( @RequestParam(value="docNo", required = false) int doc_no, 
	 								@RequestParam(value="type", required = false) String type, 
	 								@RequestParam(value = "rejReason", required = false) String rej_reason,
	 								HttpSession session, ApprovalDTO app) {

		MemberDTO memDto = (MemberDTO)session.getAttribute("login");		
		app.setMember_num(memDto.getMember_num()); //로그인 세션에서 결재자 사원번호
		app.setDoc_no(doc_no); //문서 번호
		
		Date nowTime = new Date(); // 현재 날짜 가져오기
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		app.setApp_date(sf.format(nowTime)); //결재 승인 일자
		
		int appResult = 0;
		int docResult = 0;
		
		if(type.equals("app")) { //결재 승인
			List<ApprovalDTO> appMemList = appService.selectAllWaitAppStatus(doc_no); //문서 번호에 해당하는 결재자 중에 예정이 있는지 확인
			
			if( !appMemList.isEmpty() ) { //결재 예정자 존재
				appService.modifyNextAppMemStatus(appMemList.get(0).getApp_no()); //다음 결재자 상태 변경(예정 -> 대기)
				//appList(결재자 중 예정인 자, app_level로 정렬, app_no으로 접근)중 가장 첫번째 사람의 상태 변경 
				
				app.setApp_status("완료"); //현재 결재자 상태: 완료
				app.setDoc_status("진행"); //문서 상태: 대기 -> 진행
			} 
			else { //결재 예정자 없음
				app.setApp_status("완료"); //현재 결재자 상태: 완료, 다음 결재자 없음
				app.setDoc_status("완료");
			}
		} 
		else if(type.equals("rej")) { //결재 반려
			app.setRej_reason(rej_reason); 
			app.setApp_status("반려"); //현재 결재자 상태: 반려 -> 다음 결재자와 상관 없이 반려처리
			app.setDoc_status("반려"); //문서 상태: 대기 -> 반려
			type = "app"; //반려(rej) 처리 후 결재(app)대기함 다시 보기
		}
		appResult = appService.updateAppMemStatus(app); //결재자 상태 변경
		docResult = appService.updateDocStatus(app); //문서 상태 변경
		
		System.out.println("======== 결재 승인 완료 ======== 결재자 : " + app);
		
		//결과 보내기
		String allResult = "";
		
		if(appResult > 0 && docResult > 0) { //결재자, 문서상태 모두 변경
			allResult = "?docNo=" + doc_no + "&type=" + type + "&docStatus=" + app.getDoc_status();

		} else {
			allResult = "?docNo=" + doc_no + "&type=" + type + "&docStatus=대기";
		}
				
		return "redirect: clickDocContent" + allResult;
	}
	
	//문서상세보기: 기안문 제목을 클릭했을 때
	@RequestMapping(value ="/clickDocContent")
	public String draftClickDocContent(  @RequestParam(value="docNo") int doc_no, 
										 @RequestParam(value="docStatus") String doc_status,
										 @RequestParam(value="type") String type,
										 HttpSession session ) {
		Date nowTime = new Date(); //현재 날짜 가져오기
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		AppDocumentDTO docDetail = service.detailDocContent(doc_no); //문서 내용 찾아오기
		System.out.println("============= 문서 상세보기 : " + docDetail); 
		
		AppFileDTO fileList = fService.fileContent(doc_no); //파일 정보 찾아오기
		System.out.println("파일 : " + fileList);
		
		List<ApprovalDTO> appMemList = appService.searchAppMem(doc_no); //결재자 정보 찾아오기
		List<AppReferDTO> refMemList = refService.searchRefMem(doc_no); //참조자 정보 찾아오기

		System.out.println("결재자 : " + appMemList);
		System.out.println("참조자 : " + refMemList);
		System.out.println("type : " + type);
		System.out.println("문서 상태 : " + doc_status);
				
		session.setAttribute("docDetail", docDetail); //문서 내용
		session.setAttribute("fileList", fileList); //파일 정보
		session.setAttribute("appMemList", appMemList); //결재자 정보
		session.setAttribute("refMemList", refMemList); //참조자 정보
		session.setAttribute("type", type); //type: draft
		session.setAttribute("doc_status", doc_status); //결재문서함의 결재 상태
		session.setAttribute("nowDate", sf.format(nowTime)); //현재 날짜
		
		String returnResult = "";
		if(type.equals("draft") || type.equals("app")) { // 기안함/결재함
			returnResult = "app_docDetail";
		} else if(type.equals("temp") || type.equals("rej")){ //반려 후 재상신
			returnResult = "app_docRedraft";
		}
		
		return returnResult;
	}

}
