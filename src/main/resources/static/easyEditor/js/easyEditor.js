function EasyEditor(id) {
	this.id = id;
	this.obj = document.getElementById(id);
	this.obj.setAttribute('class', 'easyEditor');
	this.obj.spellcheck = false;
	this.obj.contentEditable = 'true';
	this.sel;
	this.ran;
	this.editorBegin();
}
EasyEditor.prototype = {
	editorBegin: function() {
		var self = this;
		var obj = this.obj;
		obj.focus();
		this.setRange();
		obj.blur();
		window.scrollTo(0, 0);
		this.defaultFunc();
		this.filter();
		obj.onclick = function() {
			self.eventFunc(self);
		}
		obj.onkeyup = function() {
			self.eventFunc(self);
		}
		obj.onkeypress = function() {
			self.eventFunc(self);
		}
		obj.ondrop = function(event) {
			var event = event || window.event;
			if(event.preventDefault) {
				event.preventDefault();
			} else {
				event.returnValue = false;
			}
		}
	},
	eventFunc: function(editor) {
		var self = editor;
		var obj = editor.obj;
		var oText = obj.textContent;
		var oHtml = obj.innerHTML;
		var oTLen = oText.length;
		var oHLen = oHtml.length;
		if(oTLen == 0 && oHLen != 0) {
			if(oHLen == 4) {
				var br = obj.getElementsByTagName('br');
				self.removeElement(br[br.length - 1]);
			} else if(oHLen == 11 || oHLen == 13) {
				var p = obj.getElementsByTagName('p');
				self.removeElement(p[p.length - 1]);
			} else if(oHLen == 15) {
				var div = obj.getElementsByTagName('div');
				self.removeElement(div[div.length - 1]);
			}
		}
		if(document.activeElement.id = self.id) self.setRange();
	},
	placeholder:function(holder){
		this.obj.setAttribute('placeholder',holder);
		return this;
	},
	insertHTML: function(html) {
		if(this.ran == undefined) {
			this.obj.focus();
			this.setRange();
		}
		var oFragment = this.ran.createContextualFragment(html);
		var oLastNode = oFragment.lastChild;
		this.ran.deleteContents();
		this.ran.insertNode(oFragment);
		this.ran.setStartAfter(oLastNode);
		this.sel.removeAllRanges();
		this.sel.addRange(this.ran);
		this.obj.focus();
	},
	/*
	 	opts.src; 图片路径
	 	opts.remark; 图片文字描述
	 	opts.afterInsert; 插入后回调
	 * */
	insertEmoji: function(opts) {
		if(opts.src == undefined){
			console.error('insert emoji src is not define');
			return false;
		}
		if(!opts.remark) opts.remark = '';
		var newImg = '<img src="' + opts.src + '" easy-remark="'+opts.remark+'" />';
		this.insertHTML(newImg);
		if(opts.afterInsert) {
			opts.afterInsert.call(this);
		}
		return this;
	},
	/*
	 opts.text; 插入文字
	 opts.color; 文字颜色
	 opts.afterInsert; 插入后毁掉函数
	 * */
	insertBlock: function(opts) {
		this.insertHTML('<br id="changeLinear"/><span id="easyEditorSaveWidth">' + opts.text + '</span>');
		var spanObj = document.getElementById('easyEditorSaveWidth');
		var width = this.getRect(spanObj).width;
		this.removeElement(spanObj);
		this.removeElement(document.getElementById('changeLinear'));
		opts.color = opts.color == undefined ? '' : 'color:'+opts.color + ';';
		var labelBox = '<input type="text" disabled="disabled" value="' + opts.text + '" style="width:' + width + 'px;'+opts.color+'">';
		this.insertHTML(labelBox);
		if(opts.afterInsert) {
			opts.afterInsert.call(this);
		}
		return this;
	},
	getContent: function(bol) {
		var oldHtml = this.obj.innerHTML;
		if(typeof bol == 'function') {
			var func = bol;
			var bol = false;
		}
		var res = this.obj.innerHTML;
		if(bol) {
			var block = this.obj.getElementsByTagName('input');
			for(var i=0;i<block.length;i++){
				var newSpan = document.createElement('span');
				newSpan.innerHTML ='|'+block[i].getAttribute('value')+'|';
				block[i].parentNode.insertBefore(newSpan,block[i]);
			}
			var emojis = this.obj.getElementsByTagName('img');
			for(var i=0;i<emojis.length;i++){
				var newSpan = document.createElement('span');
				newSpan.innerHTML ='|'+emojis[i].getAttribute('easy-remark')+'|';
				emojis[i].parentNode.insertBefore(newSpan,emojis[i]);
			}
			res = this.obj.innerHTML;
			res = res.replace(/<\/p>/g,'#easyEditor#');
			res = res.replace(/<\/div>/g,'#easyEditor#');
			res = res.replace(/<\/?[^>]*>/g,'');
			res = res.replace(/#easyEditor#/g,'<br />');
		}
		this.obj.innerHTML = oldHtml;
		this.obj.focus();
		return res;
	},
	editorHolder: function(holder) {
		this.obj.placeholder = holder;
		return this;
	},
	getRange: function(savedSel) {
		var self = this;
		if(!savedSel) {
			if(self.ran) {
				var savedSel = self.ran;
			} else {
				return;
			}
		}
		if(window.getSelection) {
			var sel = window.getSelection();
			sel.removeAllRanges();
			sel.addRange(savedSel);
		} else if(document.selection) {
			savedSel.select();
		}
	},
	setRange: function() {
		this.sel = this.createRange();
		if(this.sel == null) {
			return false
		} else {
			(window.getSelection) ? this.ran = this.sel.getRangeAt(0): this.ran = this.sel.createRange();
		}
	},
	createRange: function() {
		if(window.getSelection) {
			var sel = window.getSelection();
			if(sel.rangeCount > 0) {
				return sel;
			}
		} else if(document.selection) {
			var sel = document.selection;
			return sel.createRange();
		}
		return null;
	},
	filter: function() {
		var self = this;
		try {
			document.execCommand("AutoUrlDetect", false, false);
		} catch(e) {}
		this.obj.onpaste = function(event) {
			var event = event || window.event;
			if(event.preventDefault) {
				event.preventDefault();
			} else {
				event.returnValue = false;
			}
			var text = null;
			if(window.clipboardData && clipboardData.setData) {
				text = window.clipboardData.getData('text');
			} else {
				text = (event.originalEvent || event).clipboardData.getData('text/plain');
			}
			if(document.body.createTextRange) {
				if(document.selection) {
					textRange = document.selection.createRange();
				} else if(window.getSelection) {
					sel = window.getSelection();
					var range = sel.getRangeAt(0);
					var tempEl = document.createElement("span");
					tempEl.innerHTML = "&#FEFF;";
					range.deleteContents();
					range.insertNode(tempEl);
					textRange = document.body.createTextRange();
					textRange.moveToElementText(tempEl);
					tempEl.parentNode.removeChild(tempEl);
				}
				textRange.text = text;
				textRange.collapse(false);
				textRange.select();
			} else {
				document.execCommand("insertText", false, text);
			}
		}
	},
	defaultFunc: function() {
		if((typeof Range !== "undefined") && !Range.prototype.createContextualFragment) {
			Range.prototype.createContextualFragment = function(html) {
				var frag = document.createDocumentFragment(),
					div = document.createElement("div");
				frag.appendChild(div);
				div.outerHTML = html;
				return frag;
			};
		}
	},
	removeElement: function(_element) {
		var _parentElement = _element.parentNode;
		if(_parentElement) {
			_parentElement.removeChild(_element);
		}
	},
	getRect: function(el) {
		var rect = el.getBoundingClientRect();
		var _left = document.documentElement.clientLeft;
		var _top = document.documentElement.clientTop;
		return {
			left: rect.left - _left,
			top: rect.top - _top,
			right: rect.right - _left,
			bottom: rect.bottom - _top,
			width: rect.right - rect.left,
			height: rect.bottom - rect.top
		}
	}
}
