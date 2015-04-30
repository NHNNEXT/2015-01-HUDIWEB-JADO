// YG: 이 프로젝트에서 가장 깔끔하고 이쁜 코드네요. 좋아요 쾅!
function ScrollAction(elTarget) {
    this.elTarget = document.querySelector(elTarget);
    if (!this.elTarget) return;
    this.elSections = this.elTarget.querySelectorAll('article');
    this.viewHeight = 0;
    this.scrollHeight = 0;
    this.lastPage = 0;
    this.recentPage = 0;
};

ScrollAction.prototype.run = function() {
    console.log(this);
    this.recentPage = this.getPage(window.pageYOffset, this.viewHeight);
    if (this.lastPage !== this.recentPage) {
        this.removePage();
        this.startPage();
        this.lastPage = this.recentPage;
    }
};

ScrollAction.prototype.getViewportH = function() {
    var docElem = window.document.documentElement;
    var clientHeight = docElem.clientHeight,
        innerHeight = window.innerHeight;
    this.viewHeight = Math.max(clientHeight, innerHeight);
};

ScrollAction.prototype.runOnScroll = function() {
    window.addEventListener("scroll", (function(e) {
        this.run();
    }).bind(this));
};

ScrollAction.prototype.getPage = function(posY, view) {
    var temp = (view + posY) / view;
    return parseInt(temp);
};

ScrollAction.prototype.removePage = function() {
    if (this.lastPage < 1 || this.lastPage > 3) return;
    this.elSections[this.lastPage - 1].classList.remove('animation');
};

ScrollAction.prototype.startPage = function() {
    if (this.recentPage < 1 || this.recentPage > 3) return;
    this.elSections[this.recentPage - 1].classList.add('animation');
};

