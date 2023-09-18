<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title>결재자 선택</title>
<link rel="stylesheet" href="resources/css/approverModal.css"> 
<script src="https://cdn.jsdelivr.net/npm/lodash@4.17.21/lodash.min.js"></script> <!-- 배열, 객체, 문자열 및 함수와 관련된 다양한 작업 -->
</head>

<body>
	<!-- 결재자 및 참조자 선택 모달창 -->
	<div class="app-t-modal" id="app-t-modal"> <!-- 전체 모달창 지정 -->
		<div class="app-modal-content" title="close on click"> <!-- 모달 내용 부분 -->	
				<div class="app-modal-header">
						<span class="m-header-title" id="header"></span> <!-- 결재자 or 참조자 선택 -->
				</div>
				
				<div class="app-modal-body">
					<div class="a-m-search"> <!-- 선택 -->
						<select class="m-s-select" id="s-condition" name="searchCondition" style="text-align:left; width:80px;">
							<option value="all">전체</option> <!-- s-condition의 value -->
							<option value="div_name">부서</option>
							<option value="member_name">이름</option>
						</select>
						
						<div class="a-m-input"> <!-- 검색창 -->
							<input type="text" id="s-value" name="searchValue" class="m-select-text">
							<button type="button" id="search-btn" class="m-i-search"> 검색 </button>
						</div>
					</div>
						
					<div class="a-m-list">
						<table class="m-s-list-table" id="m-list-table"> <!-- 사원 전체 정보 뿌려주기 --> </table>
					</div>
					
					<div class="s-m-select">
						<strong class="m-s-text" id="s-text"> <!-- 결재자, 참조자 글씨 --> </strong><br>
						<div class="s-list-div" id="s-list-div" >
							<p id="s-list"> <!-- 결재자, 참조자 선택 뿌려질 화면 -->
						</div>
					</div>
				</div>
				
				<div class="app-modal-footer" id="m-footer"> &nbsp;
					<button class="m-confirm-btn" id="m-confirm-btn">확인</button>
					<button class="m-cancel-btn" id="m-cancel-btn">취소</button>
				</div>
		</div>
	</div>
</body>

<script>
	var varType; //결재자/참조자 구분 넣을 변수 선언
	var appArr = new Array(); //선택한 결재자 담을 배열 선언
	var refArr = new Array(); //선택한 참조자` 담을 배열 선언
	var appArrText = new Array(); //화면에 보여줄 결재자 리스트 배열 선언
	var refArrText = new Array(); //화면에 보여줄 참조자 리스트 배열 선언
	
	//모달 중 결재자 선택 or 참조자 선택
	function appBtn(type) {		
		varType = type; // 변수에 type 넣어주기
		
		if(type == "app") { //버튼을 눌렀을 때 매개변수로 넘어온 type가 app일 때: 결재자
			$("#header").html("결재자 선택");
			$("#s-text").html("결재자");
			$("#s-list").html(appArrText.join("<br>")); //배열 내의 모든 요소를 문자열로 합치고, 요소 사이에 <br>태그
		}
		else if(type == "ref") { //버튼을 눌렀을 때 매개변수로 넘어온 type가 ref일 때: 참조자
			$("#header").html("참조자 선택");
			$("#s-text").html("참조자");
			$("#s-list").html(refArrText.join("<br>")); //배열 내의 모든 요소를 문자열로 합치고, 요소 사이에 <br>태그
		}
		$("#app-t-modal").fadeIn(); //클릭시, 모달창 나옴
		
		$.ajax({
			url : "approverSelect",
			type : "get",
			dataType : "json",
			success : function(mList) { //mList를 받아오기		
				console.log("성공!" + mList);
 				$("#s-value").val(""); //검색 입력창 지우기
 				appList(mList, type);
			},
			error : function() {
				alert("사원 목록 조회 실패");
			}
		})
	}
	
	//결재자, 참조자 선택
	$("#m-confirm-btn").click(function() {
		$("#app-t-modal").fadeOut(); //클릭시, 모달창이 꺼짐
		appFormView(); //모달창이 꺼지면서, form에 결재자 정보가 표시됨
	 });
	
	//결재자, 참조자 선택 취소
	$("#m-cancel-btn").click(function(){
		$("#app-t-modal").fadeOut(); //클릭시, 모달창이 꺼짐
	});
	
		
	//검색 내용 입력 후 엔터 눌러도 검색되도록 처리
	$("#s-value").keyup(function (e) { //s-value창에서 키가 떼어질 때 이벤트 발생
		e.preventDefault(); //기본 동작 방지: 현재 입력란 내용을 제출 방지
		var code = e.keyCode ? e.keyCode : e.which; 
		//key 이벤트 처리시 1.대부분의 브라우저: e.which, 2.e.keyCode: 오래된 브라우저
		if(code == 13) { //엔터 키(키 코드 13)를 눌렀을 때
			$("#search-btn").click(); //검색 버튼이 클릭 실행
		}
	})

 				
	//검색 조건(조건, 내용)에 맞는 사원 검색하기(ajax)
	$("#search-btn").click(function() {
		var searchCondition = $("#s-condition").val(); //어떤 조건(전체, 부서, 이름) 선택했는지를 나타내는 값
		var searchValue = $("#s-value").val(); //어떤 내용 검색헀는지 나타내는 값
			
		$.ajax({//서버로 데이터를 비동기적으로 요청하는 부분
			url : "searchMember", //요청을 보낼 URL
			type : "get",
			data : { "searchCondition" : searchCondition,  "searchValue" : searchValue }, 
			//보낼 데이터: 검색 조건, 검색 값을 객체 형태(json)로 전달
			dataType : "json",			
			success : function( mList ) { //mList(사원 리스트)를 매개변수로 appList 함수를 호출
				console.log("searchAppList 성공!");
				appList(mList, varType); //아래 appList 함수 호출
			},
			error : function() {
				alert("사원 목록 검색 실패");
			}
		})

	});
	
	//사원 목록에 저장된 사원 정보 불러오기
	function appList(mList, type) { //mList: 사원 목록, type: 결재자/참조자 변수
		$("#m-list-table").html(""); //이전에 표시되었던 테이블 내용 삭제
		var tr; //테이블의 행(row)을 생성할 때 사용될 문자열을 담을 것
		
		$.each(mList, function(i) { //$.each() 반복문: mList 배열을 순회하며 사원 정보 추출
			tr += '<tr class="tr"><td style="display:none;">'  
					+ mList[i].member_num + '&nbsp;' //숨긴 셀은 화면 출력X
					+ '</td><td>' + mList[i].div_name + '&nbsp;'
					+ '</td><td>' + mList[i].member_name + '&nbsp;'
					+ '</td><td>' + mList[i].rank + '</td></tr>';
		});
		
		$("#m-list-table").append(tr); //테이블에 + 생성한 행(tr 변수에 저장된 HTML 문자열)을 추가
		//append: HTML 요소에 새로운 내용을 추가
		
		appSelect(type); //결재자 or 참조자 선택
	}
	
	
	//결재자/참조자 선택
	function appSelect(type) {//type: 결제자/참조자 변수
		$("#m-list-table tr").click( function(){ //행 클릭시 함수 실행
			var trArr = new Object(); //한 행의 배열 담을 객체를 생성
			var tdArr = new Array(); //각 셀(사원번호, 부서, 이름, 직급)의 데이터를 담을 배열을 생성
			
			//현재 클릭된 Row(<tr>)
			var tr = $(this); //현재 클릭된 행
			var td = tr.children(); //선택한 행의 모든 자식요소(td)를 td변수에 저장
						
			//반복문을 이용해서 배열에 값을 담아 사용
			td.each( function(i){ //=$("td").each(): 각 셀의 데이터를 추출하고, tdArr 배열에 순서대로 push
				tdArr.push( td.eq(i).text() ); //td.eq(i).text(): 현재 td의 텍스트값 가져옴
			}); //tdArr배열에 td 텍스트 값을 저장
			
			
			//td.eq(index)를 통해 값 가져와서 trArr 객체에 넣기
			trArr.memberNum = td.eq(0).text(); //td의 0번째 text값 객체에 저장
			trArr.division = td.eq(1).text();
			trArr.memberName = td.eq(2).text();
			trArr.rank = td.eq(3).text();
			
			//객체에 데이터가 있는지 여부 판단
			if(type == "app") { //결재자인 경우 동작 정의
				//appArr 순회하면서, memberNum속성이 trArr.memberNum과 일치하는 요소의 인덱스 저장
				var checkedArrIdx = _.findIndex(appArr, { memberNum : trArr.memberNum }); //동일한 값 인덱스 찾기
							
				appArrText = []; // 배열 비우기
				
				//선택한 행에 memberNum이 appArr배열에 있으면 해당 항목을 제거하고, 그렇지 않으면 배열에 추가
				if(checkedArrIdx > -1) { //checkedArrIdx가 -1보다 크면 : 동일한 값을 찾은 경우, 일치 요소 찾지 못할 시: checkedArrIdx는 -1 반환
					_.remove(appArr, { memberNum : trArr.memberNum }); //동일한 값 인덱스 지우기
				}
				else {
					if(appArr.length < 3) { //선택한 결재자 수가 3보다 작으면
						appArr.push(trArr); //객체를 배열에 담기
					}
					else {
						alert("결재자는 3명까지만 선택할 수 있습니다.");
					}
				}
				
				appArr.forEach(function(ele, index) {//appArr순회 => 배열 내의 객체 정보를 appArrText배열에 문자열 추가
					appArrText.push(ele.division + " " + ele.memberName + " " + ele.rank); //화면에 표시할 결재자 목록 저장
				});
				
				$("#s-list").html(appArrText.join("<br>")); //join("<br>")을 통해 개행을 추가하여 s-list 영역에 텍스트를 출력
			}
			else if(type == "ref") { // 참조자
				var checkedArrIdx = _.findIndex(refArr, { memberNum : trArr.memberNum }); //동일한 값 인덱스 찾기
				
				refArrText = []; // 배열 비우기
				
				if(checkedArrIdx > -1) {//checkedArrIdx가 -1보다 크면 : 동일한 값을 찾은 경우
					_.remove(refArr, { memberNum : trArr.memberNum }); //동일한 값 지우기
				}
				else {
					refArr.push(trArr);
				}
				
				refArr.forEach(function(el, index) {
					refArrText.push(el.division +" "+ el.memberName +" "+ el.rank);
				});
				
				$("#s-list").html(refArrText.join("<br>")); // 개행해서 s-list 영역에 출력
			}
		});
	}
	
	// 선택한 결재자/참조자 문서 작성 페이지에 표시
	function appFormView() { //결재자(appArr)와 참조자(refArr) 데이터를 해당 <form>hidden 필드에 저장
		if(varType == "app") { //결재자
			for(var i = 0; i < 3; i++) { //전에 입력한 값이 있을 경우, 결재자 정보 화면 초기화
				$("#r-app" + i).text(""); 
				$("#name-app" + i).text(""); 
				$("#num-app" + i).val("");
			}
			var app = []; //결재자 정보 담을 배열 선언
			
			appArr.forEach(function(ele, i){
				$("#r-app" + i).text(ele.rank); //결재자의 직급 정보
				$("#name-app" + i).text(ele.memberName); //결재자의 사번 정보
				
				app[i] = ele.memberNum; //결재자 사번: app 배열에 순서대로 저장
			});
			
			$("#num-app").val(app); //결재자 사번 순서대로 저장한 app배열 => 문자열로 변환
		}
		else if(varType == "ref"){ //참조자
			$("#ref-list").text(refArrText.join(", ")); //refArrText배열의 요소 사이: 쉼표로 연결한 문자열을 생성
		
			var ref = []; //참조자 정보 담을 배열 선언
			refArr.forEach(function(ele, i){
				ref[i] = ele.memberNum; //사원번호를 ref배열에 순서대로 저장
			})
			
			$("#num-ref").val(ref); //사원 번호를 순서대로 저장한 ref배열 => 문자열로 변환
		}
	}
</script>


</body>
</html>