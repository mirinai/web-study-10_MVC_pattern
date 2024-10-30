/**
 * 
 */

// 게시글 작성 시 입력 검증 함수
const boardCheck = () => {
    // 작성자 이름이 비어 있는지 확인
    if (document.frm.name.value.length == 0) {
        window.alert("작성자를 넣으세요"); // 경고창 표시
        return false; // 유효성 검사 실패 시 false 반환
    }
    // 비밀번호가 비어 있는지 확인
    if (document.frm.pass.value.length == 0) {
        window.alert("비밀번호를 넣으세요"); // 경고창 표시
        return false; // 유효성 검사 실패 시 false 반환
    }
    // 제목이 비어 있는지 확인
    if (document.frm.title.value == "") {
        window.alert("제목을 입력하세요"); // 경고창 표시
        return false; // 유효성 검사 실패 시 false 반환
    }
    return true; // 모든 조건이 충족되면 true 반환
}

// 새로운 창을 열어주는 함수
function open_win(url, name) {
    // 지정된 URL과 이름을 가진 새로운 창을 열고 크기(width=500, height=230)를 설정
    window.open(url, name, 'width=500, height=230');
}

// 비밀번호 확인 시 입력 검증 함수
function passCheck() {
    // 비밀번호가 비어 있는지 확인
    if (document.frm.pass.value.length == 0) {
        alert("비밀번호를 입력하세요"); // 경고창 표시
        return false; // 유효성 검사 실패 시 false 반환
    }
    return true; // 비밀번호가 입력되어 있으면 true 반환
}
