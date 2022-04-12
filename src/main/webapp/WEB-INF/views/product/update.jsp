<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<c:import url="../template/header.jsp"></c:import>
	<link rel="stylesheet" href="../resources/css/hamberger.css">
</head>
<body>



	<h1>update page</h1>
	<form action="./update" method="post" enctype="multipart/form-data">
		<input type="hidden" name="productNum" value="${dto.productNum}" id="getProductNum">
		<div class="form-group">
			<label>상품 이름</label>
			<input type="text" name="name" value="${dto.name}"><br>
		</div>
		<div class="form-group">
			<label>설명</label>
			<textarea name="contents" rows="2" cols="30">${dto.contents}</textarea><br>
		</div>
		<div class="form-group">
			<label>가격</label>
			<input type="text" name="price" value="${dto.price}"><br>
		</div>
		
		
<!-- 재고 -->
		<c:choose>
			<c:when test="${empty options}">
				재고<input type="text" value="${dto.stock}" id="stockForm" readonly>
				재고 추가 <input type="text" name="stock" value="0"> 
			</c:when>
			<c:otherwise>
				재고<input type="hidden" value="${dto.stock}" id="stockForm">
				재고 추가 <input type="hidden" name="stock" value="0"> 
			</c:otherwise>
		</c:choose>
		<%-- <c:if test="${empty options}"> 
			재고<input type="text" name="stock" value="${dto.stock}" id="stockForm">
			재고 추가 <input type="text" name="stock" value="0"> 
		</c:if>
		 --%>
<hr><!--------카테고리------------->		
		<div>
			category <select name="categoryNum">
				<c:forEach items="${list}" var="list">
					<c:choose>
						<c:when test="${list.parentId eq null}">
							<!--parentid가 null -> 최상위카테고리 -->
							<optgroup label="${list.categoryName}">
							</optgroup>
						</c:when>
						<c:otherwise>
							<option value="${list.categoryNum}">
								&nbsp;&nbsp;&nbsp;${list.categoryName}</option>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</select>
		</div>
<hr><!--------옵션------------->
	<form:form action="./update" method="post" enctype="multipart/form-data">
	<div id="options">
		<div id="oriOption">
			<c:forEach items="${options}" var="options" varStatus="state">
	
				<li>
				<button type="button">${options.optionContents}</button>	
				재고: ${options.optionStock}
		
				재고 추가 <input type="text" name="optionStock" value="0"> 
				
				<input type="hidden" name="optionNum" value="${options.optionNum}">
				<input type="hidden" name="productNum" value="${options.productNum}">
				<button type="button" data-fileNum="${options.optionNum}" class="optionDeleteBtn">X</button>
				</li>

			</c:forEach>
		</div>
		
		<div id="optionResult"></div>
		
		<button type="button" id="optionAdd_btn">옵션 추가</button>
	
	</div>
	</form:form>
<hr><!--------파일------------->


		<div id="files">
			<c:forEach items="${dto.productFileDTOs}" var="f" varStatus="state">
				<div>
					<c:choose>
						<c:when test="${state.first}">
					대표이미지 ${f.oriName} <button type="button"
								data-fileNum="${f.fileNum}" class="fileDeleteBtn_t">X</button>
							<%-- 대표사진<input type="file" name="files" value="${f.fileName}"> --%>
							<!-- 		나중에 썸네일 수정하기
						
						파일선택버튼을 눌렀으면 히든 보내줌 아니면 지워 
						수정안하는 경우에는 히든 보내지마   -->
							<%-- 	<input type="hidden" name="fileNum" value="${f.fileNum}" id="hiddenfileNum">  --%>

						</c:when>


						<c:otherwise>
					기본이미지 ${f.oriName} <button type="button"
								data-fileNum="${f.fileNum}" class="fileDeleteBtn">X</button>

							<%-- 사진 <input type="file" name="files" value="${f.fileName}"> --%>
						</c:otherwise>

					</c:choose>
				</div>
				
			
				<div id="fileResult_t"></div>
				<!-- 썸네일에 fileName을 넣어야되는데 fileManger를 거쳐야 얻을 수 있음 여러 파일중에서 썸네일을 어떻게 구분해서 prductcontroller까지 보내지  -->

			</c:forEach>
			<input type="hidden" name="check" id="tCheck" value="2">
		</div>


		<div id="fileResult"></div>
		<div>
			<button type="button" id="fileAdd">파일 추가</button>
		</div>

		<button type="submit">수정 확인</button>
	</form>

	<script type="text/javascript" src="../resources/js/product/productFileUpdate.js"></script>
	<script type="text/javascript" src="../resources/js/product/productOption.js"></script>
	<script src="../resources/js/hamberger.js"></script>
</body>
</html>