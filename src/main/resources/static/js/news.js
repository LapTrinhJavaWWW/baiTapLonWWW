$(document).ready(function () {
    const nav = document.querySelectorAll(".nav-menu");
  
    $(".father-hoverdt").hide();
    $(".father-hovertablet").hide();
    $(".father-hoverphukien").hide();
    $("#menu-user").hide();
  
    const menuuser = document.querySelector(".user-of-me");
  
    menuuser.addEventListener("mouseover", (e) => {
      e.preventDefault();
      $("#menu-user").fadeIn("slow");
    });
  
    nav.forEach((item) => {
      item.addEventListener("mouseover", (e) => {
        e.preventDefault();
        const id = e.target.getAttribute("id");
  
        if (id === "dienthoai") {
          $(".father-hoverdt").fadeIn("slow");
          $(".father-hovertablet").hide();
          $(".father-hoverphukien").hide();
        }
  
        if (id === "tablet") {
          $(".father-hovertablet").fadeIn("slow");
          $(".father-hoverdt").hide();
          $(".father-hoverphukien").hide();
        }
  
        if (id === "phukien") {
          $(".father-hoverphukien").fadeIn("slow");
          $(".father-hoverdt").hide();
          $(".father-hovertablet").hide();
        } else if (id == null) {
          $(".father-hoverdt").hide();
          $(".father-hovertablet").hide();
          $(".father-hoverphukien").hide();
        }
      });
    }); 


    // const newsSPmoi = document.querySelectorAll(".news-item-spmoi");
    // if(newsSPmoi.length > 5){
    //   for (let i = 5; i < newsSPmoi.length; i++) {
    //     newsSPmoi[i].style.display = "none";
    //   }
    // }

  });
  
  
  
 