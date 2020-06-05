//for popup
let mask="";
function openPop(obj){
    var popup = document.getElementById(obj);
    document.body.appendChild(popup);
    console.log(mask instanceof Element);
    if(mask == ""){
    	console.log("시작");
    	mask = document.createElement('div');
        mask.className="mask";
        console.log(mask instanceof Element);
        document.body.insertBefore(mask,popup);
        mask.style.display="block";
    }
    popup.style.display="block";
    var posX = (window.innerWidth-popup.offsetWidth)/2;
    var posY = (window.innerHeight-popup.offsetHeight)/2;
    if(posX < 0) posX=0;
    if(posY < 0) posY=0;
    popup.style.top=posY+"px";
    popup.style.left=posX+"px";
    popup.focus();
}
function closePop(obj){     
	var popup = document.getElementById(obj);
	popup.style.display="none";	
   var popups = document.querySelectorAll(".popup");
   for(var i=0;i<popups.length;i++)
	   if(popups[i].style.display === "block")
		   return false;
   
   document.body.removeChild(mask);
   mask="";
}
//for checkbox
function check_all(e,formName,checkListName) {
   var theList = document.forms[formName][checkListName];
   var theTarget = e;
    // console.log(theList.length);
if (theTarget.classList.contains('on')) {
    theTarget.classList.remove('on')
    for (var i = 0; i < theList.length; i++) {
        theList[i].checked = false
    }
 } else {
    theTarget.classList.add('on')
    for (var i = 0; i < theList.length; i++) {
        theList[i].checked = true
    }
 }
}
function reset_all(formName,checkListName){
    var theList = document.forms[formName][checkListName];
    for (var i = 0; i < theList.length; i++) 
    	theList[i].checked = false;
}
//for input
function amountControl(e,type){
    var target=e.parentElement.parentElement.parentElement.querySelector("input");
    var val = parseInt(target.value);
    switch(type){
        case "plus": val++; break;
        case "minus": val--
            if(val < 0) val =0;
        break;
    }
    target.value = val;
}
//for tab
function tab(index){
	var menuList = document.querySelector(".tab").children;
	var conList =  document.querySelectorAll(".tab-con");
	for(var i=0;i<menuList.length;i++){
		if(i == index){
			menuList[i].classList.add("on");
    		conList[i].style.display="block";
    		continue;
		}
		menuList[i].classList.remove("on");
		conList[i].style.display="none";
	}
}
