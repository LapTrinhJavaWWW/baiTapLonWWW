$(document).ready(function () {
  const nav = document.querySelectorAll('.nav-menu')

  $('.checkHangDTALL').prop('checked', true)
  $('#locTheo').hide()

  $('.father-hoverdt').hide()
  $('.father-hovertablet').hide()
  $('.father-hoverphukien').hide()
  $('#menu-user').hide()

  const menuuser = document.querySelector('.user-of-me')

  menuuser.addEventListener('mouseover', (e) => {
    e.preventDefault()
    $('#menu-user').fadeIn('slow')
  })

  nav.forEach((item) => {
    item.addEventListener('mouseover', (e) => {
      e.preventDefault()
      const id = e.target.getAttribute('id')

      if (id === 'dienthoai') {
        $('.father-hoverdt').fadeIn('slow')
        $('.father-hovertablet').hide()
        $('.father-hoverphukien').hide()
      }

      if (id === 'tablet') {
        $('.father-hovertablet').fadeIn('slow')
        $('.father-hoverdt').hide()
        $('.father-hoverphukien').hide()
      }

      if (id === 'phukien') {
        $('.father-hoverphukien').fadeIn('slow')
        $('.father-hoverdt').hide()
        $('.father-hovertablet').hide()
      } else if (id == null) {
        $('.father-hoverdt').hide()
        $('.father-hovertablet').hide()
        $('.father-hoverphukien').hide()
      }
    })
  })

  document.getElementById('thugon').style.display = 'none'
  const course = document.querySelectorAll('.card')
  if (course.length > 6) {
    for (var i = 6; i < course.length; i++) {
      course[i].style.display = 'none'
    }
  } else {
    document.getElementById('xemthem').style.display = 'none'
  }

  $('#xemthem').click(xemThemCourse)
  $('#thugon').click(thuGonCourse)
})
//click xem thêm sản phẩm
function xemThemCourse(e) {
  e.preventDefault()
  const course = document.querySelectorAll('.card')
  for (var i = 6; i < course.length; i++) {
    $(course[i]).fadeIn('slow')
  }
  document.getElementById('xemthem').style.display = 'none'
  document.getElementById('thugon').style.display = 'block'
}

//click thu gọn sản phẩm
function thuGonCourse(e) {
  e.preventDefault()
  const course = document.querySelectorAll('.card')
  for (var i = 6; i < course.length; i++) {
    $(course[i]).fadeOut('slow')
  }
  document.getElementById('xemthem').style.display = 'block'
  document.getElementById('thugon').style.display = 'none'
}
//envent click slide next and prev
const items = document.querySelectorAll('.item')
for (var i = 5; i < items.length; i++) {
  items[i].style.display = 'none'
}

var prev = 0
var next = 0

function plus(n) {
  next = prev + n

  if (next < 0) {
    next = 0
  }
  if (next > 5) {
    next = 5
  }
  const items = document.querySelectorAll('.item')
  for (var i = 0; i < items.length; i++) {
    items[i].style.display = 'none'
  }
  for (var i = next; i < next + 5; i++) {
    items[i].style.display = 'block'
  }
  prev = next
}

//envent fillter san pham
let checkedBrands = [...new Set()]
let checkedPrice = [...new Set()]

const checkpricenav = document.querySelectorAll('.check-price-nav')
checkpricenav.forEach((item) => {
  item.addEventListener('click', (e) => {
    $('#locTheo').show()
    createElement(item.getAttribute('value'))
    checkedPrice.push(item.getAttribute('value'))
    const checkprices = document.querySelectorAll('.check-price')
    checkprices.forEach((items) => {
      if (items.getAttribute('value') === item.getAttribute('value'))
        items.checked = true
    })
    $('.checkGiaALL').prop('checked', false)
    searchProduct()
  })
})

const checkprices = document.querySelectorAll('.check-price')
checkprices.forEach((item) => {
  item.addEventListener('change', (e) => {
    if (e.target.checked) {
      $('#locTheo').show()
      createElement(e.target.value)
      checkedPrice.push(e.target.value)
      $('.checkGiaALL').prop('checked', false)
      searchProduct()
    }
    if (!e.target.checked) {
      checkedPrice = checkedPrice.filter((item) => item !== e.target.value)
      deleteElement(e.target.value)
      if (checkedPrice.length === 0) {
        $('.checkGiaALL').prop('checked', true)
        checkedPrice = []
      }
      searchProduct()
    }
  })
})

const checkdienthoais = document.querySelectorAll('.check-dienthoai')
checkdienthoais.forEach((item) => {
  item.addEventListener('change', (e) => {
    if (e.target.checked) {
      $('#locTheo').show()
      createElement(e.target.value)
      checkedBrands.push(e.target.value)
      $('.checkHangDTALL').prop('checked', false)
      searchProduct()
    }

    if (!e.target.checked) {
      checkedBrands = checkedBrands.filter((item) => item !== e.target.value)
      deleteElement(e.target.value)
      if (checkedBrands.length === 0) {
        $('.checkHangDTALL').prop('checked', true)
        checkedBrands = []
      }
      searchProduct()
    }
  })
})

function deleteElement(value) {
  let i = document.querySelectorAll('.locTheoText')
  const parent = document.getElementById('fillterDT')
  i.forEach((item) => {
    if (item.innerText === value) {
      parent.removeChild(item)
      checkCountChecked()
      checkdienthoais.forEach((item) => {
        if (item.value === value) {
          item.checked = false
          checkedBrands = checkedBrands.filter((item) => item !== value)
          searchProduct()
        }
      })

      checkprices.forEach((item) => {
        if (item.value === value) {
          item.checked = false
          checkedPrice = checkedPrice.filter((item) => item !== value)
          searchProduct()
        }
      })
    }
  })
}

function checkCountChecked() {
  let i = document.querySelectorAll('.locTheoText')
  if (i.length === 0) {
    $('#locTheo').hide()
    $('.checkHangDTALL').prop('checked', true)
    $('.checkGiaALL').prop('checked', true)
  }
}

function createElement(value) {
  const i = document.createElement('i')
  const parent = document.getElementById('fillterDT')
  i.setAttribute('class', 'fa fa-times locTheoText')
  i.setAttribute('onclick', 'deleteElement(this.innerText)')
  i.innerText = value
  parent.appendChild(i)
}

// (function ($) {
//   showSwal = function (type) {
//     'use strict';
//     if (type === 'basic') {
//       swal({
//         title: 'Không còn sản phảm này!',
//         text: 'xin mời chọn sản phẩm khác',
//         button: {
//           text: "OK",
//           value: true,
//           visible: true,
//           className: "btn btn-primary"
//         }
//       })

//     }
//   }

// })(jQuery);

function searchProduct() {
  req = {
    brands: checkedBrands,
    prices: checkedPrice,
  }
  var myJSON = JSON.stringify(req)
  $.ajax({
    url: '/api/san-pham/loc',
    type: 'POST',
    data: myJSON,
    contentType: 'application/json; charset=utf-8',
    success: function (data) {
      genProductList(data)
    },
    error: function (data) {
      console.log(data)
    },
  })
}
function genProductList(products) {
  if (products.length > 0) {
    let html = ''
    $('.products').html(html)
    for (product of products) {
      html += `
          <div class="card  w3-center w3-animate-opacity" style="width: 18rem;">
          <img src="${product.dsImg[0]}" class="card-img-top" alt="...">
          <div class="card-body float-sm-start">
            <h5 class="card-title" >${product.tenSanpham}</h5>
            <i>
              <h5 class="card-title price">${product.gia}</h5>
            </i>
            <p class="card-text d-flex align-products-center" style="margin-left: 0rem !important;">
              <box-icon name='chip'></box-icon>
              <span>${product.chip}</span>
            </p>
            <p class="card-text d-flex align-products-center" style="margin-left: 0rem !important;">
              <box-icon name='mobile-alt'></box-icon>
              <span>${product.manHinh + ' inch'}</span>
            </p>
            <p class="card-text d-flex align-products-center" style="margin-left: 0rem !important;">
              <box-icon name='hdd'></box-icon>
              <span>${product.rom + ' GB'}</span>
            </p>
            <p class="card-text d-flex align-products-center" style="margin-left: 0rem !important;">
              <box-icon name='microchip'></box-icon>
              <span>${product.ram + ' GB'}</span>
            </p>
            <a href="#" class="btn btn-danger">Mua ngay</a> <a
              href="/show/ + ${
                product.tenSanpham
              }" class="btn btn-danger show-course"><i
                class="fa fa-eye" aria-hidden="true">Xem chi tiết</i></a>
          </div>
        </div>
          `
    }
    $('.products').html(html)
    $('.no-products').css('display', 'none')
  } else {
    // swal("Sản phẩm không tồn tại!", "Mời chọn sản phẩm khác", "info");
  }
}

$('.checkHangDTALL').click(function () {
  if (this.checked) {
    $('.check-dienthoai').prop('checked', false)
    checkedBrands = []
    searchProduct()
  } else {
    $('.check-dienthoai').prop('checked', false)
    checkedBrands = []
    searchProduct()
  }
})

$('.checkGiaALL').click(function () {
  if (this.checked) {
    $('.check-price').prop('checked', false)
    checkedPrice = []
    searchProduct()
  } else {
    $('.check-price').prop('checked', false)
    checkedPrice = []
    searchProduct()
  }
})

function fillterIconHangDT(name) {
  $('#locTheo').show()
  let i = document.querySelectorAll('.locTheoText')
  const parent = document.getElementById('fillterDT')
  const checkdienthoais = document.querySelectorAll('.check-dienthoai')
  if (i.length == 0) {
    createElement(name)
    checkedBrands.push(name)
    checkdienthoais.forEach((item) => {
      if (item.value === name) {
        item.checked = true
      }
    })
    $('.checkHangDTALL').prop('checked', false)
    searchProduct()
  } else {
    checkdienthoais.forEach((item) => {
      if (item.value === name) {
        item.checked = true
        // searchProduct();
      }
    })
    let check = true
    i.forEach((item) => {
      if (item.innerText === name) {
        check = false
      }
    })
    if (check) {
      createElement(name)
      checkedBrands.push(name)
      $('.checkHangDTALL').prop('checked', false)
      searchProduct()
    }
  }
}
