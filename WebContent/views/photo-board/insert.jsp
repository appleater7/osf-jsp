<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/pboard/insert" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="pb_title"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="pb_content"></textarea></td>
			</tr>
			<tr>
				<th>사진</th>
				<td><input type="file" name="pb_file_path"></td>
			</tr>
			<tr>
				<td colspan="2"><button>업로드</button></td>
			</tr>
		</table>
	</form>
	<form action="/pboard/list" method="get">
	<button>리스트 조회</button>
	</form>
	</form>
	<form action="" method="get">
	<input type="number" name="pb_num">
	<button>리스트 단건 조회</button>
	</form>
</body>
</html>