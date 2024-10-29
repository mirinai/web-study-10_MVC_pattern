/**
 * 
 */

const boardCheck = () => {
	if (document.frm.name.value.length == 0) {
		window.alert("작성자를 넣으세요");
		return false;
	}
	if (document.frm.pass.value.length == 0) {
		window.alert("비밀번호를 넣으세요");
		return false;
	}
	if(document.frm.title.value==""){
		window.alert("제목을 입력하세요");
		return false;
	}
	return true;
}

function open_win(url, name){
	window.open(url,name,'width=500, height=230');
}

function passCheck(){
	if(document.frm.pass.value.length==0){
		alert("비밀번호를 입력하세요");
		return false;
	}
	return true;
}