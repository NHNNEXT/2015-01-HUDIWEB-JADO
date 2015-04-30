// ##### Form Manager #####
// Form Manager는 encrypt 기능을 포함합니다.

// YG: FormManager는 하나의 form을 관리하는 객체가 아닌가요?
//     왜 모든 함수가 element를 받아서 처리하죠?
//     이렇게 되면 FormManager가 객체인 이유가 없습니다.
//     FormManager를 생성할 때 대표격인 element를 받아오는 건 어떨까요?
function FormManager() {
	this.encrypt = new Encrypt();
};

FormManager.prototype.isNull = function(inputElement) {
    var inputEl = jado.util.getElement(inputElement);
    if (!inputEl || inputEl.tagName != "INPUT") return;
    if (inputEl.value.trim().length)
        return false;
    return true;
}

FormManager.prototype.showSellerEnroll = function(element) {
    // YG: 어떤 element(?)를 가져왔는데, 변수명이 벌써 chkBox입니다.
    //     체크박스가 아닌 div를 인자로 막 넣으면 안 된다는 느낌을
    //     함수명이나 parameter 이름에서부터 팍팍 줄 수 있는 코드가 좋은 코드입니다.
	var chkBox = jado.util.getElement(element);
	if (!chkBox) return;
	chkBox.addEventListener("click", function() {
		var target = jado.util.getElement(".formContainer");
		if (chkBox.checked) {
			target.classList.add('scrollSellerEnroll');
		} else {
			target.classList.remove('scrollSellerEnroll');
		}
	})
};

//경륜아, 이건 목적이 뭐인지 얘기해주게 ㅋㅋㅋ
FormManager.prototype.whatIsThis = function(element) {
	var elForm = jado.util.getElement(element);
    if (!elForm) return;
    var elements = {
        elId: 'userId',
        elPw1: 'password',
        elPw2: 'checkPassword',
        elName: 'name',
        elPhone: 'phone',
        elAddress: 'address',
        elSubmit: 'submit'
    };

    for (var prop in elements) {
        // YG: 이 if문이 존재하는 이유는 뭔가요?
        //     질문을 바꿔서, hasOwnProperty는 언제 false를 반환합니까?
        if (elements.hasOwnProperty(prop)) {
            this[prop] = jado.util.getElement('input[name="' + elements[prop] + '"]');
        }
    }
};