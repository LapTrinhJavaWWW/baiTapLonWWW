$(document).ready(function () {
    let ipeditinfors = document.querySelectorAll('.ip-edit-infor');
    ipeditinfors.forEach(e => {
       e.style.display = 'none';
    });

    // let faeditinfors = document.querySelectorAll('.fa-edit-infor');
    // faeditinfors.forEach(e => {
    //     e.addEventListener('click', (ee) => {
    //         ee.preventDefault();
    //         let id = e.getAttribute('id');
    //         let ip = document.querySelector('#ip-' + id);
    //         let fa = document.querySelector('#fa-' + id);
    //         if (ip.style.display === 'none') {
    //             ip.style.display = 'block';
    //             fa.classList.remove('fa-edit');
    //             fa.classList.add('fa-check');
    //         } else {
    //             ip.style.display = 'none';
    //             fa.classList.remove('fa-check');
    //             fa.classList.add('fa-edit');
    //         }
    //     });
    // });
});


let navs = document.querySelectorAll('.nav-menu');
navs.forEach(e => {
    e.addEventListener('mouseover', (ee) => {
        ee.preventDefault();
        $(".hover-dienthoai").show();
        $(".hover-dienthoai").addClass('animation')
    });
});

let fatherHoverdt = document.querySelector('.hover-dienthoai');
fatherHoverdt.addEventListener('mouseleave', (ee) => {
    ee.preventDefault();
    $(".hover-dienthoai").hide();
});

let navbar = document.querySelector('.navbar');
navbar.addEventListener('mouseover', (ee) => {
    ee.preventDefault();
    $(".hover-dienthoai").hide();
});

let content = document.querySelector('.content');
content.addEventListener('mouseover', (ee) => {
    ee.preventDefault();
    $(".hover-dienthoai").hide();
});

let slide = document.querySelector('.slide');
slide.addEventListener('mouseover', (ee) => {
    ee.preventDefault();
    $(".hover-dienthoai").hide();
});

// function fillterNavHoverHangDT(value){
//     brands.add(value);
//     ShowClickIconPhone();
//     AjaxFillterBrandPhone();
// }