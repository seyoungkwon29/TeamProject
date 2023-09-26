package com.controller.approval;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.AppDocumentDTO;
import com.dto.AppDocumentMapDTO;
import com.dto.AppReferDTO;
import com.dto.AppReferMapDTO;
import com.dto.ApprovalDTO;
import com.dto.AppFileDTO;
import com.dto.ApprovalMapDTO;
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
	
	
	//기안 문서함: 본인이 기안한 문서의 리스트가 출력
	@RequestMapping(value ="/draftList")
	public String draftList( HttpSession session, Map<String, Object> map) {	
		
		int member_num = (int)((MemberDTO)session.getAttribute("login")).getMember_num(); 
		map.put("temp", "임시"); //임시만 제외하고, 전자결재함에 출력
		map.put("member_num", member_num); //사원번호로 조회
		
		List<AppDocumentDTO> appDocList = service.saveDocFormList(map);
		System.out.println(">>>>>>>>>> 기안 문서함 : " + appDocList);
		
		session.setAttribute("appDocList", appDocList); //기안한 문서
		
		return "app_draftList";
	}

	//결재요청, 반려문서 재상신 : 기안 문서를 올리면, 문서 저장
	@RequestMapping(value ="/SaveDocForm")
	public String SaveDocForm( AppDocumentMapDTO doc, AppFileDTO file, ApprovalMapDTO app,
							   AppReferDTO ref, HttpSession session,
							   @RequestParam(value="type", required = false) String type,
							   @RequestParam(value="refMemNum") String refMemNum,  
							   @RequestParam(value="appMemNum") String appMemNum) {
		System.out.println(">>>>>>>>> SaveDocForm : " + doc);
		System.out.println("저장한 파일 확인: " + file);
		
//		if(type.equals("Doc") || type.equals("RejDoc")) { //기안 문서/반려 문서 결재 요청
//			doc.setForm_name(doc.getForm_name());
//		}
		//기안 문서 저장
        int docResult = service.saveDocForm(doc);
        
        //파일 첨부 
        int fileResult = 0;
        if( file.getFile_path() != null ) {
        	fileResult = fService.saveDocFile(file);
        }
        
        //결재자 등록
        int appResult = 0;
		String [] appArray = appMemNum.split(",");


		for(int i = 0; i < appArray.length; i++) {
			app.setMember_num( Integer.parseInt(appArray[i]) ); //결재자 사원번호
			app.setApp_level(i+1); //결재자 순번: 1번부터 순서대로
			
			if(i == 0) { //첫번째 결재자인 경우
				app.setApp_status("대기");; //첫 번째 결재자는 대기
			} else {
				app.setApp_status("예정"); //두 번째 결재자부터 결재 예정
			}

			appResult = appService.registerAppMem(app); //결재자 등록
		}
//		} else { //반려 문서 재상신인데 결재자 선택하지 않은 경우
//			List<Approval> aList = aService.printAllApp(appDoc.getDocNo());
//			if(!aList.isEmpty()) {
//				for(int i = 0; i < aList.size(); i++) {
//					app.setDocNo(0);
//					app.setMemNum(aList.get(i).getMemNum());
//					app.setAppLevel(aList.get(i).getAppLevel());
//					if(!parameter.equals("RejTem")) { // 임시 저장이 아니면
//						if(i == 0) {
//							app.setAppStatus("대기"); // 첫 번째 결재자는 대기
//							alarmRegister(app.getMemNum(), appDoc.getMemNum(), 0, "요청"); // 알림 등록(첫 번째 결재자에게 결재 요청)
//						}else {
//							app.setAppStatus("예정"); // 두 번째 결재자부터는 예정
//						}
//					}else if(parameter.equals("RejTem")) { // 임시 저장이면
//						app.setAppStatus("임시");
//					}
//					aResult = aService.registerApp(app); // 결재자 등록
//				}
//			}
//		}
		
		
		
		
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
			refResult = 1;
		}
		
		return "redirect: draftList"; // draftList 주소로 다시 가서(기안 문서함), 기안 문서가 뜨도록 해줌
	}
		
	
	//상신취소 및 삭제
	@RequestMapping(value ="/draftDocCancel")
	public String draftDocCancel(@RequestParam(value="doc_no", required = false) int doc_no,
							     @RequestParam(value="type", required = false) String type,
								 AppFileDTO file) {
        //기안된 문서 삭제
	    int docResult = service.draftDocCancel(doc_no);
        
	    //파일 있다면 삭제
	    int fileResult = 0;
        if( file.getFile_path() != null ) {
        	fileResult = fService.draftfileCancel(doc_no);
        }
        
        //결재자 삭제
        int appResult = appService.draftAppMemCancel(doc_no);
        
        //참조자 삭제
        int refResult = appService.draftRefMemCancel(doc_no);
			
		return "redirect: draftList"; // /draftList 주소로 다시 가서, 기안 문서가 뜨도록 해줌
	}
		
		
	//임시 저장: 임시 저장함에 추가하기
	@RequestMapping(value="/saveTempDocForm")
	public String saveTempList( AppDocumentDTO doc, AppFileDTO file, HttpSession session) {

		//기안 문서 저장
		doc.setDoc_status("임시");
        int docResult = service.saveTempDocForm(doc);  
        
        //파일 저장
        int fileResult = 0;
        if( file.getFile_path() != null) {
        	System.out.println(">>>>>>>>> saveTempDocForm " + file);
        	fileResult = fService.saveDocFile(file);
        }
        
        System.out.println("임시 저장함 성공 ! " + fileResult);
		
		return "redirect: callTempList"; //임시 저장함 저장 후 => 임시 저장한 문서 리스트 출력
	}
	
	//임시 저장함 전체 문서
	@RequestMapping(value ="/callTempList")
	public String callTempList( HttpSession session ) {
		String doc_status = "임시";
		List<AppDocumentDTO> tempList = service.callTempList(doc_status);
		System.out.println(">>>>>>>>>> 임시 저장함 : " + tempList);
		
		session.setAttribute("tempList", tempList); //저장한 문서 list
		
		return "app_tempList";
	}
	
	//임시기안 문서 상세 페이지
	@RequestMapping(value ="/tempClickDocContent")
	public String tempClickDocContent(  @RequestParam(value="docNo") int doc_no, 
										@RequestParam(value="docStatus") String doc_status,
										AppDocumentDTO doc, HttpSession session ) {
		doc.setDoc_status(doc_status); //상태: 임시
		doc.setDoc_no(doc_no);  //문서 번호
				
		AppDocumentDTO tempDocContent = service.tempDetailDocContent(doc);
		System.out.println(">>>>>>>>> tempClickDocContent : " + tempDocContent);
		
		System.out.println("doc_no : " + doc_no);
		AppFileDTO fileList = fService.fileContent(doc_no);
		System.out.println("임시저장함 file 찾아옴 === : " + fileList);
		
		session.setAttribute("tempDocContent", tempDocContent); //저장한 문서 list
		session.setAttribute("fileList", fileList); //저장한 file
		
		return "app_tempDocForm";
	}
	
	
	//임시저장함  결재 상신 : 문서 등록 및 임시저장함 문서 삭제
	@RequestMapping(value ="/SaveDocFormAndDelete")
	public String SaveDocFormAndDelete(AppDocumentMapDTO doc, AppFileDTO file, HttpSession session) {
		System.out.println(">>>>>>>>> SaveDocFormAndDelete : " + doc);
		System.out.println("파일 확인 : " + file);

        //결재 문서 등록, 임시 문서 삭제
	    int doc_no = doc.getDoc_no(); //문서 번호 가져오기
	    int docResult = service.SaveDocFormAndDelete(doc, doc_no); //결재 문서 등록, 임시 문서 삭제
	    
        //파일 첨부
	    int fileResult = 0;
        if( file.getFile_path() != null ) {
        	fileResult = fService.saveDocFile(file);
        }

		return "redirect: draftList"; // /draftList 주소로 다시 가서, 기안 문서가 뜨도록 해줌
	}	
	
	//결재 대기함
	@RequestMapping(value ="/appList")
	public String appDocList(HttpSession session) {
		MemberDTO memDto = (MemberDTO)session.getAttribute("login");
		
		//결재라인에 올라온 사람에게 결재 순번(대기)에 맞게 결재문이 올라옴 => 결재자 사원번호로 조회
		List<AppDocumentMapDTO> appDocList = service.selectListAppDoc(memDto.getMember_num());
		
		System.out.println(">>>>>>>>>> 결재 대기함 : appDocList " + appDocList);
		session.setAttribute("appDocList", appDocList); 
		
		return "app_appList";
	}
	
		
	//결재 승인/반려
	@RequestMapping(value ="/approveAppStatus")
	public String approveAppStatus( @RequestParam(value="docNo", required = false) int doc_no, 
	 								@RequestParam(value="type", required = false) String type, 
	 								@RequestParam(value = "rejReason", required = false) String rej_reason,
	 								HttpSession session, ApprovalMapDTO app) {

		MemberDTO memDto = (MemberDTO)session.getAttribute("login");		
		app.setMember_num(memDto.getMember_num()); //로그인 세션에서 결재자 사원번호
		app.setDoc_no(doc_no); //문서 번호
		
		Date nowTime = new Date(); // 현재 날짜 가져오기
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		app.setApp_date(sf.format(nowTime)); //결재 승인 일자
		
		int appResult = 0;
		int docResult = 0;
		
		if(type.equals("app")) { //결재 승인
			List<ApprovalMapDTO> appMemList = appService.selectAllWaitAppStatus(doc_no); //문서 번호에 해당하는 결재자 중에 예정이 있는지 확인
			
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
		
		System.out.println(">>>>>>>>> 결재 승인 : ApprovalMapDTO : " + app);
		
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
		Date nowTime = new Date(); // 현재 날짜 가져오기
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		
		AppDocumentMapDTO docDetail = service.detailDocContent(doc_no); //문서 내용 찾아오기
		System.out.println(">>>>>>>>>> 문서 상세보기 : docDetail : " + docDetail); 
		
		AppFileDTO fileList = fService.fileContent(doc_no); //파일 정보 찾아오기
		System.out.println("파일 : " + fileList);
		
		List<ApprovalMapDTO> appMemList = appService.searchAppMem(doc_no); //결재자 정보 찾아오기
		List<AppReferMapDTO> refMemList = refService.searchRefMem(doc_no); //참조자 정보 찾아오기

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
		} else if(type.equals("tem") || type.equals("rej")){ //반려 후 재상신
			returnResult = "app_docRedraft";
		}
		
		return returnResult;
	}

}
