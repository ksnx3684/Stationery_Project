const submitBtn=document.getElementById("submitBtn")
const checkbox=document.getElementsByClassName("checkbox")
const proNum=document.getElementById("productNum")
const piece=document.getElementById("piece")
const auth=window.sessionStorage.getItem("auth")


let productNum=proNum.value;
let optionNum=0;
let optionCk="false";


submitBtn.addEventListener("click",function(event){
  
    
        let productCount=piece.value;
        //radio check된 value optionNum에 넣어주기 
        for(cb of checkbox){
            if(cb.checked){
            optionNum=cb.value;
            optionCk="true";
            }
        }


        //옵션 있는 상품 옵션선택안했을경우 
        if(optionNum==0&&optionCk=="false"&&auth){
            alert("옵션을 선택해주세요")
            
        }else{

            let xhttp = new XMLHttpRequest();
            xhttp.open("POST","./addCart");
            xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded")
            xhttp.send("productNum="+productNum+"&productCount="+productCount+"&optionNum="+optionNum);

            xhttp.onreadystatechange = function(){
                if(this.readyState==4 && this.status==200){
                    console.log(this.responseText)
                    let result = this.responseText.trim();
                    if(result=='1'){
                        console.log("장바구니 추가 성공")
                        let check = confirm("장바구니에 추가되었습니다. 장바구니 화면으로 이동하시겠습니까?")
                        if(check){
                            location.replace("../cart/cartlist")
                        }
                        return;
                    }else if(result=='2'){
                        alert("이미 장바구니에 추가된 상품입니다.")
                    }else{ //3
                        let check = confirm("로그인이 필요합니다. 로그인 페이지로 이동하시겠습니까?")
                        if(check){
                            location.replace("../users/login")
                        }
                        return;
                    }
                }
            }
        }
})