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

  document.getElementById("thugon").style.display = 'none';
  const course = document.querySelectorAll(".card");
  if(course.length > 6){
    for (var i = 6; i < course.length; i++) {
        course[i].style.display = 'none';
    }
  }else{
    document.getElementById("xemthem").style.display = 'none';
  }


  $('#xemthem').click(xemThemCourse);
  $('#thugon').click(thuGonCourse);

});

//click xem thêm sản phẩm
function xemThemCourse(e){
    e.preventDefault();
    const course = document.querySelectorAll(".card");
    for (var i = 6; i < course.length; i++) {
        $(course[i]).fadeIn("slow");
    }
    document.getElementById("xemthem").style.display = 'none';
    document.getElementById("thugon").style.display = 'block';
}

//click thu gọn sản phẩm
function thuGonCourse(e){
    e.preventDefault();
    const course = document.querySelectorAll(".card");
    for (var i = 6; i < course.length; i++) {
        $(course[i]).fadeOut("slow");
    }
    document.getElementById("xemthem").style.display = 'block';
    document.getElementById("thugon").style.display = 'none';
}




//envet click slide next and prev
const items = document.querySelectorAll('.item');
    for (var i = 5; i < items.length; i++) {
        items[i].style.display = 'none';
    }

var prev = 0;
var next = 0;


function plus(n){
   next = prev + n;
  
    if(next < 0){
        next = 0;
    }
    if(next > 5){
        next = 5;
    }
    const items = document.querySelectorAll('.item');
    for (var i = 0; i < items.length; i++) {
        items[i].style.display = 'none';
    }
    for (var i = next; i < next + 5; i++) {
        items[i].style.display = 'block';
    }
    prev = next;

}