function delete_ok(id) {        
	var a = confirm("정말로 삭제하겠습니까?");
	if (a)
		location.href = 'deleteok/' + id;
} 