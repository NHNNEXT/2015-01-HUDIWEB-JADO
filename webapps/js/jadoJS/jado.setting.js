// YG: 아직 작업중인 파일인거죠? Setting.js에 쓰려던 내용을 여기에 옮겨 적습니다.
function Setting() {
	this.b = "board";
	this.c = "category";
	this.boardNum = 0;
	this.categoryNum = 0;
	// YG: 여기에 바로 this.init()이 오는건 어떨까요?
};

Setting.prototype.init = function() {
	this.elBoards = jado.util.getElement('#boardAfter');
	this.elCategorys = jado.util.getElement('#categoryAfter');
	console.log(this.elBoards);
	console.log(this.elCategorys);
};

Setting.prototype.addListItem = function(bORc, elementList, index) {
	// YG: elementList.appendChild(elItem) 같은 방식이 낫겠죠?
	// YG: 추가로, HTML을 JS에 이렇게 작성하는건 문제가 생길 여지가 많습니다.
    //     js 템플릿 엔진을 찾아보세요. underscore가 간단하게 쓰긴 좋습니다.
    //     html도 이런 string을 사용하는 대신 파일로 분리해서 ajax로 받아오는 방식으로 바꿔보세요.
	elementList.innerHTML = elementList.innerHTML 
	+ "<li name="+index+">"
		+ "<input type='text' name='" + bORc + "' placeholder='제목을 입력해 주세요'>"
		+ "<button type='button' onclick='removeList(" + index + ")'>삭제</button>"
	+ "</li>";
	index++;
};

Setting.prototype.removeListItem = function(elementList, index) {
	var element= elementList.querySelector('li[name="'+ index +'"]');
    elementList.removeChild(element);
};
