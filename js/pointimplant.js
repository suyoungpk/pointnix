// select박스 선택에 따른 내용 변경 
function chgType(e){
	var type=e.options[e.selectedIndex].value;
	var t;
	var list = document.querySelector(".dependOption");
	list.innerHTML="";
	switch(type){
	case "ops1": 
		e.parentElement.nextElementSibling.style.display="block";
		if(!e.parentElement.parentElement.classList.contains("half-select"))
			e.parentElement.parentElement.classList.add("half-select");
		t = document.querySelector('#optionType01 > table')|| document.querySelector('#optionType01').content;
		break;
	case "ops2":
		e.parentElement.nextElementSibling.style.display="none";
		e.parentElement.parentElement.classList.remove("half-select");
		t = document.querySelector('#optionType04 > table')|| document.querySelector('#optionType04').content;
		break;
	case "ops3": 
		e.parentElement.nextElementSibling.style.display="none";
		e.parentElement.parentElement.classList.remove("half-select");
		t = document.querySelector('#optionType05 > table')|| document.querySelector('#optionType05').content;
		break;
	}
	var clone = t.cloneNode(true);
	list.appendChild(clone);
	return false;
}
function chgType2(e){
	var type=e.options[e.selectedIndex].value;
	var t;
	var list = document.querySelector(".dependOption");
	list.innerHTML="";
	switch(type){
	case "implant": 
		t = document.querySelector('#optionType01 > table')|| document.querySelector('#optionType01').content;
		break;
	case "crown":
		t = document.querySelector('#optionType02 > table')|| document.querySelector('#optionType02').content;
		break;
	case "inlay": 
		t = document.querySelector('#optionType03 > table')|| document.querySelector('#optionType03').content;
		break;
	}
	var clone = t.cloneNode(true);
	list.appendChild(clone);
	return false;
}
var toothId = [ // 디자인을 위한 num은  고정 값 / 다른 속성값을 넣어 사용 가능=value는 임의의 다른 값 > 변경가능
	{num : 18, value:1},{num : 17, value:1},{num : 16, value:1},{num : 15, value:1},{num : 14, value:1},
	{num : 13, value:1},{num : 12, value:1},{num : 11, value:1},{num : 21, value:1},{num : 22, value:1},
	{num : 23, value:1},{num : 24, value:1},{num : 25, value:1},{num : 26, value:1},{num : 27, value:1},
	{num : 28, value:1},{num : 38, value:1},{num : 37, value:1},{num : 36, value:1},{num : 35, value:1},
	{num : 34, value:1},{num : 33, value:1},{num : 32, value:1},{num : 31, value:1},{num : 41, value:1},
	{num : 42, value:1},{num : 43, value:1},{num : 44, value:1},{num : 45, value:1},{num : 46, value:1},
	{num : 47, value:1},{num : 48, value:1}
];

var linkAreas = document.querySelector(".select-tooth-img").querySelectorAll("area"); //클릭 영역 리스트
var selectedArr = [];
var selectedList = new Set();
var matched = function(){
	var filed  = [];
	for(var i = 0;i<toothId.length;i++)
		for(var j =0;j<selectedArr.length;j++)
			if((selectedArr[j] == toothId[i].num))  filed.push(toothId[i]);
		
	return filed;
}
function setSeletedArr(arr){ 
	selectedArr = arr;
	selectedList = new Set( matched());
	console.log("selectedList : "+selectedList);
}
var tooth = { // 치식 이미지 선택을 위한 객체
   id:0,
   select : function (mode){
	   linkAreas[this.id].dataset.active=true;
	   selectedList.add(toothId[this.id]);
	   
	   var tag = document.createElement("span");
   	   tag.classList.add("flag","no"+toothId[this.id].num);
   	   if(mode != "edit")
   		   tag.onclick= this.unselect.bind(this);
   	   tag.dataset.id = this.id;
   	   document.querySelector(".select-tooth-img").appendChild(tag);
   },
   unselect: function(e){
	 if(e) this.id = parseInt(e.target.dataset.id);
	 linkAreas[this.id].dataset.active=false;
	 selectedList.delete(toothId[this.id]);
	 var tag = document.querySelector(".no"+toothId[this.id].num);
	 document.querySelector(".select-tooth-img").removeChild(tag);
	   }
  };
function selectTooth(e){ // 치식선택 이미지 클릭 이벤트 
	var active = (e.target.dataset.active == "true");
	var index =parseInt(e.target.dataset.id);
	tooth.id=index;
	if(active) tooth.unselect();
	else tooth.select();
};
function resetTooth(mode){ // 클릭 영역에 대한 셋팅
	for(var i=0;i<linkAreas.length;i++){
		linkAreas[i].alt="치식번호 : "+toothId[i].num;
		linkAreas[i].dataset.id=i;
		if(mode != "edit")
			linkAreas[i].onclick=selectTooth.bind(this);
		if(selectedList.has(toothId[i])){
			linkAreas[i].dataset.active=true;
			tooth.id = i;
			tooth.select(mode);
		}
		else linkAreas[i].dataset.active=false;
	} 
}
  