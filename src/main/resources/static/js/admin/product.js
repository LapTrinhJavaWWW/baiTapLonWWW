let tabs = document.querySelectorAll('.nav-link-tab');
let tabdanhsachsanpham = document.getElementById('tab-danhsachsanpham');
let tabthemsanpham = document.getElementById('tab-themsanpham');
tabthemsanpham.style.display = 'none';
tabs.forEach(function (tab) {
   tab.addEventListener('click', function (event) {
      event.preventDefault();
      let tabVal = tab.getAttribute('data-tab');
      if (tabVal == 'tablist') {
         document.getElementById('tab-danhsachsanpham').style.display = 'block';
         document.getElementById('tab-themsanpham').style.display = 'none';
      } else {
         document.getElementById('tab-themsanpham').style.display = 'block';
         document.getElementById('tab-danhsachsanpham').style.display = 'none';
      }
   });
});

// tool hỗ trợ nhập nhanh sản phẩm
let ipnameproduct = document.getElementById('name');
let slug = document.getElementById('slug');
let ipprice = document.getElementById('price');
ipnameproduct.addEventListener('keyup', function () {
   let value = $(this).val();
   let valueSplit = value.split(' ');
   valueSplit.forEach((item) => {
      item = item.toLowerCase().trim();
      switch (item) {
         case 'apple':
            slug.value = item;
            break;
         case 'oppo':
            slug.value = item;
            break;
         case 'samsung':
            slug.value = item;
            break;
         case 'xiaomi':
            slug.value = item;
            break;
         case 'vivo':
            slug.value = item;
            break;
         case 'asus':
            slug.value = item;
            break;
         case 'nokia':
            slug.value = item;
            break;
         case 'masstel':
            slug.value = item;
            break;
         case 'realme':
            slug.value = item;
            break;
         case 'tecno':
            slug.value = item;
            break;
         case 'iphone':
            slug.value = 'apple';
            break;
         default:
            break;
      }
   });
});
ipprice.addEventListener('blur', function () {
   let value = this.value;
   let regexNumber = /^[0-9]+$/;
   if (!regexNumber.test(value)) {
      this.value = '';
   } else {
      this.value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
   }
});
// save product
let btnSave = document.getElementById('btnSave');
btnSave.addEventListener('click', function () {
   let name = $('#name').val();
   let slug = $('#slug').val();
   let price = $('#price').val();
   price = price.replace(/\./g, '');
   let img = $('#image').val();
   let chip = $('#chip').val();
   let screen = $('#screen').val();
   let rom = $('#rom').val();
   let ram = $('#ram').val();
   let type = $('#type').val();
   let bestSellerCheck = $('#bestSeller');
   let bestSeller = 'true';
   if (bestSellerCheck.is(':checked')) {
      bestSeller = 'true';
   } else {
      bestSeller = 'false';
   }

   let req = {
      name: name,
      slug: slug,
      price: price,
      img: img,
      chip: chip,
      screen: screen,
      rom: rom,
      ram: ram,
      type: type,
      bestSeller: bestSeller,
   };
   AjaxSaveProduct(req);
});

function AjaxSaveProduct(req) {
   $.ajax({
      url: '/admin/product/save',
      type: 'GET',
      data: req,
      success: function (data) {
         $('.toast').removeClass('hide');
         $('.toast').addClass('show');
         // reload page
         location.reload();
      },
   });
}

// delete product
let btnOpenModalDelete = document.querySelectorAll('.openModelDel');
btnOpenModalDelete.forEach(function (btn) {
   btn.addEventListener('click', function () {
      let id = btn.getAttribute('value');

      let btnDel = document.getElementById('btnDel');
      btnDel.addEventListener('click', function () {
         AjaxDeleteProduct(id);
      });
   });
});

function AjaxDeleteProduct(id) {
   $.ajax({
      url: '/admin/product/deleted?id=' + id,
      type: 'GET',
      success: function (data) {
         // reload page
         location.reload();
      },
   });
}

// update product
let id = null;
let btnOpenModalUpdate = document.querySelectorAll('.openModelUpdate');
btnOpenModalUpdate.forEach(function (btn) {
   btn.addEventListener('click', function () {
      let data = btn.getAttribute('value');
      data = data.split(',');
      id = data[0];
      $('#slugupdate').val(data[8]);
      $('#nameupdate').val(data[1]);
      // regex [ ]
      let regex = /\[(.*?)\]/;
      let img = data[2].match(regex);
      img = img[1];
      $('#imageupdate').attr('src', img);
      $('#inputimageupdate').hide();
      $('#chipupdate').val(data[3]);
      $('#screenupdate').val(data[4]);
      $('#romupdate').val(data[5]);
      $('#ramupdate').val(data[6]);
      $('#typeupdate').val(data[9]);
      let valPrice = Number(data[7]).toString();
      valPrice = valPrice.replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
      $('#priceupdate').val(valPrice);
      $('#idupdate').val(data[0]);
      let bestSeller = data[10];
      if (bestSeller == 'true') {
         $('#bestSellerupdate').prop('checked', true);
      } else {
         $('#bestSellerupdate').prop('checked', false);
      }
   });
});
// btn clear image
let btnClearImgUpdate = document.getElementById('btnClearImgUpdate');
btnClearImgUpdate.addEventListener('click', function () {
   $('#imageupdate').hide();
   $(this).hide();
   $('#imageupdate').attr('src', '');
   $('#inputimageupdate').show();
});

// format cash
let priceupdate = document.getElementById('priceupdate');
priceupdate.addEventListener('blur', function () {
   let value = this.value;
   // regex number và dấu phẩy
   let regexNumber = /^[0-9,]+$/;
   // regex 3 số 1 dấu phẩy
   let regex = /(\d)(?=(\d{3})+(?!\d))/g;
   if (!regexNumber.test(value)) {
      this.value = '';
   } else {
      this.value = value.replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1,');
   }
});

// reload modal
let exampleModalUpdate = document.getElementById('exampleModalUpdate');
exampleModalUpdate.addEventListener('hide.bs.modal', function (event) {
   $('#imageupdate').show();
   $('#btnClearImgUpdate').show();
   $('#inputimageupdate').hide();
});
// event key enter modal
exampleModalUpdate.addEventListener('keypress', (e) => {
   if (e.key === 'Enter') {
      UpdateProduct();
   }
});

// event button update
let btnUpdate = document.getElementById('btnUpdate');
btnUpdate.addEventListener('click', function () {
   UpdateProduct();
});

function UpdateProduct() {
   let slug = $('#slugupdate').val();
   let name = $('#nameupdate').val();
   let price = $('#priceupdate').val();
   price = price.replace(/\,/g, '');
   let img = $('#imageupdate').attr('src');
   if (img == '') {
      img = $('#inputimageupdate').val();
   }
   let chip = $('#chipupdate').val();
   let screen = $('#screenupdate').val();
   let rom = $('#romupdate').val();
   let ram = $('#ramupdate').val();
   let type = $('#typeupdate').val();
   let bestSellerCheck = $('#bestSellerupdate');
   let bestSeller = 'false';
   if (bestSellerCheck.is(':checked')) {
      bestSeller = 'true';
   }
   let req = {
      slug: slug,
      name: name,
      price: price,
      img: img,
      chip: chip,
      screen: screen,
      rom: rom,
      ram: ram,
      type: type,
      bestSeller: bestSeller,
   };
   if (ValidatorValue(req) == true) {
      AjaxUpdateProduct(req);
   }
}

// ajax update
function AjaxUpdateProduct(req) {
   $.ajax({
      url: '/admin/product/update?id=' + id,
      type: 'GET',
      data: req,
      success: function (data) {
         Swal.fire({
            position: 'center',
            icon: 'success',
            title: 'Cập nhật thành công',
            showConfirmButton: false,
         });
         // reload page
         sleep(1000).then(() => {
            location.reload();
         });
      },
   });
}
function sleep(time) {
   return new Promise((resolve) => setTimeout(resolve, time));
}

// check value before update
function ValidatorValue(req) {
   if (req.name == '' || req.price == '' || req.img == '' || req.chip == '' || req.screen == '' || req.rom == '' || req.ram == '' || req.type == '') {
      Swal.fire({
         position: 'center',
         icon: 'error',
         title: 'Thông tin không hợp lệ',
         showConfirmButton: true,
      });
      return false;
   }
   return true;
}
