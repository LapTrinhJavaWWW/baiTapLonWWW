let btnSearch = document.getElementById('btnSearch');
let inSearch = document.getElementById('ipSearch');
btnSearch.addEventListener('mouseenter', () => {
    if ($('#ipSearch').css('display') == 'none') {
        $('#ipSearch').fadeIn(500);
    } else {
        $('#ipSearch').fadeOut(500);
        $('#ipSearch').val('');
    }
});

inSearch.addEventListener('mouseleave', () => {
    $('#ipSearch').fadeOut(500);
    $('#ipSearch').val('');
});

inSearch.addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        $('#ipSearch').fadeOut(500);
        $('#ipSearch').val('');
        searchProduct($(this).val());
    }
});
let arrProduct = [];
function searchProduct(value) {
    let url = "/admin/product/search?value=" + value;
    $.ajax({
        url: url,
        type: "GET",
        success: function (data) {
            arrProduct = data;
            GenarateTableProductHTML(data);
        }
    });
}


let tabproducts = document.querySelectorAll('.tabproduct');
tabproducts.forEach((item) => {
    item.addEventListener('click', (e) => {
        let data = e.target.getAttribute('data-tab');
        if (data == 'dsproduct') {
            $('#tabdsproduct').removeClass('hidden');
            $('#tabaddproduct').addClass('hidden ');

            $('#btnDsProduct').addClass('active dark:text-blue-500 border-b-2 border-blue-600');
            $('#btnDsProduct').removeClass('border-transparent');
            $('#btnAddProduct').removeClass('active dark:text-blue-500 border-b-2 border-blue-600');
        }
        if (data == 'addproduct') {
            $('#tabaddproduct').removeClass('hidden');
            $('#tabdsproduct').addClass('hidden');

            $('#btnAddProduct').addClass('active dark:text-blue-500 border-b-2 border-blue-600');
            $('#btnAddProduct').removeClass('border-transparent');

            $('#btnDsProduct').removeClass('active dark:text-blue-500 border-b-2 border-blue-600');
            $('#btnDsProduct').addClass('border-transparent');
        }
    });
});

//tool add product
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
    if(!regexNumber.test(value)){
        this.value = '';
    }else{
        this.value = value.replace(/\B(?=(\d{3})+(?!\d))/g, ".");
    }
});

//save product

let btnSave = document.getElementById('btnSave');
btnSave.addEventListener('click', function () {
    let name = $('#name').val();
    let slug = $('#slug').val();
    let price = $('#price').val();
    price = price.replace(/\./g, '');
    let img = $('#img').val();
    let chip = $('#chip').val();
    let screen = $('#screen').val();
    let rom = $('#rom').val();
    let ram = $('#ram').val();
    let type = $('#type').val();
    let bestSellerCheck = $('#bestSeller');
    let bestSeller = "true";
    if(bestSellerCheck.is(':checked')){
        bestSeller = "true";
    }else{
        bestSeller = "false";
    }

    let req={
        name: name,
        slug: slug,
        price: price,
        img: img,
        chip: chip,
        screen: screen,
        rom: rom,
        ram: ram,
        type: type,
        bestSeller: bestSeller
    }
    AjaxSaveProduct(req);
});

function AjaxSaveProduct(req){
    window.history.replaceState(null, null, "/admin/product?action=save");
    $.ajax({
        url: "/admin/product?action=save",
        type: "GET",
        data: req,
        success: function (data) {
            $('#messageSave').removeClass('hidden');
            sleep(1000).then(() => {
                window.location.href = "/admin";
            });
        }
    });
}

function sleep (time) {
    return new Promise((resolve) => setTimeout(resolve, time));
}
