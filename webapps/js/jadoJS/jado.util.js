// ##### Util #####
function Util() {};

Util.prototype.getElement = function(element) {
	// YG: element는 DOMelement를 지칭해야 좋습니다.
	//     getElement의 인자로는 selector가 오는게 더욱 명확한 것 같네요.
	if (!element) return;
	return document.querySelector(element);
};

Util.prototype.getElementAll = function(element) {
	if (!element) return;
	return document.querySelectorAll(element);
};

// YG: save된 url은 어디에 활용되는건가요?
//     이건 코드를 아무리 뒤져봐도 의도를 알 수가 없어서...
Util.prototype.saveUrl = function() {
	var url = this.getElement('input[name=url]');
	if (!url) return;
	url.value = document.location.href;
};