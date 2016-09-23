/**
 * Created by peiyulin on 16/9/1.
 */

function onNext() {
    var x=document.getElementById("page4").innerText;
    if(x=='20'){
        return
    }
    var a=parseInt(x);

    document.getElementById("page4").innerText=a+4;
    document.getElementById("page3").innerText=a+3;
    document.getElementById("page2").innerText=a+2;
    document.getElementById("page1").innerText=a+1;

}


function onPrev() {
    var x=document.getElementById("page1").innerText;

    if(x=='1'){
        return
    }

    document.getElementById("page4").innerText=x-1;
    document.getElementById("page3").innerText=x-2;
    document.getElementById("page2").innerText=x-3;
    document.getElementById("page1").innerText=x-4;
}