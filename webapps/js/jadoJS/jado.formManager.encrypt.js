// ##### Encrypt #####
function Encrypt() {
	this.elements = {
		elId: 'userId',
		elPw1: 'password',
		elPw2: 'checkPassword',
		elIdEncryption: 'idEncryption',
		elPwEncryption: 'pwEncryption',
		elSubmit: 'submit',
		rsaPublicKeyModulus: 'rsaPublicKeyModulus',
		rsaPublicKeyExponent: 'rsaPublicKeyExponent'
	};
};

// YG: element(아니, selector를) init 함수에서 따로 받아야 하는 이유는 뭘까요?
//     new Encrypt(selector)로 인자를 받는 방식이 더 깔끔해보입니다.
Encrypt.prototype.init = function(encryptElement) {
	this.elForm = jado.util.getElement(encryptElement);
	if (!this.elForm) return;
	for (var prop in this.elements) {
		// YG: same issue with WhatIsThis on formManager
		if (this.elements.hasOwnProperty(prop)) {
		    this[prop] = this.elForm.querySelector('input[name="' + this.elements[prop] + '"]');
		}
	}
};

Encrypt.prototype.removeInput = function() {
	if (this.elPw1) {
		this.elPw1.value = "";
	}
	if (this.elPw2) {
		this.elPw2.value = "";
	}
	if (this.elId) {
		this.elId.value = "";
	}
};

Encrypt.prototype.putSecuredRsa = function() {
	var rsaPublicKeyModulus = this.rsaPublicKeyModulus.value;
	var rsaPublicKeyExponent = this.rsaPublicKeyExponent.value;

	var rsa = new RSAKey();
	rsa.setPublic(rsaPublicKeyModulus, rsaPublicKeyExponent);

	console.log(this.elId.value);
	this.elIdEncryption.value = rsa.encrypt(this.elId.value);
	this.elPwEncryption.value = rsa.encrypt(this.elPwEncryption.value);
};

Encrypt.prototype.putSecuredPassword = function() {
	var securedPassword = sha256_digest(this.elPw1.value);
	this.elPwEncryption.value = securedPassword;
};