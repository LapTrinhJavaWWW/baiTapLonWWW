let btnops = document.querySelectorAll('.btnop');
let mains = document.querySelectorAll('.main');
let mainParent = document.querySelector('.main-parent');

let dataOpen = '';
let breadcrumbValue = '';

btnops.forEach((btnop, index) => {
   btnop.addEventListener('click', () => {
      mainParent.style.display = 'none';
      mains.forEach((main) => {
         main.style.display = 'none';
      });
      mains[index].style.display = 'block';
      dataOpen = btnop.getAttribute('data-openbtn');
      breadcrumbValue = dataOpen;
      GenarateBreadcrumb(breadcrumbValue);
      ajaxOpentag();
   });
});

function GenarateBreadcrumb(data) {
   let html = '';
   $('.breadcrumb').html(html);

   html = `
            <ol class="inline-flex items-center space-x-1 md:space-x-3">
                <li class="inline-flex items-center text-slate-700">
                    <a href="/admin" class="inline-flex items-center text-sm font-medium hover:text-pink-500">
                    <svg aria-hidden="true" class="w-4 h-4 mr-2" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path d="M10.707 2.293a1 1 0 00-1.414 0l-7 7a1 1 0 001.414 1.414L4 10.414V17a1 1 0 001 1h2a1 1 0 001-1v-2a1 1 0 011-1h2a1 1 0 011 1v2a1 1 0 001 1h2a1 1 0 001-1v-6.586l.293.293a1 1 0 001.414-1.414l-7-7z"></path></svg>
                    Home
                    </a>
                </li>
                <li>
                    <div class="flex items-center">
                    <svg aria-hidden="true" class="w-6 h-6 text-gray-400" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" d="M7.293 14.707a1 1 0 010-1.414L10.586 10 7.293 6.707a1 1 0 011.414-1.414l4 4a1 1 0 010 1.414l-4 4a1 1 0 01-1.414 0z" clip-rule="evenodd"></path></svg>
                    <a href="#" class="hover:text-pink-500 text-slate-700 ml-1 text-sm font-medium md:ml-2">${data}</a>
                    </div>
                </li>
            </ol>
    `;
   $('.breadcrumb').html(html);
}

function ajaxOpentag() {
   let url = '/admin/tag?open=' + dataOpen.toLowerCase();
   $.ajax({
      url: url,
      type: 'POST',
      success: function (data) {
         if (dataOpen == 'Product') {
            GenarateTableProductHTML(data);
         } else if (dataOpen == 'User') {
            GenarateTableUserHTML(data);
         }
         window.history.replaceState(null, null, url);
      },
   });
}

function GenarateTableProductHTML(data) {
   let html = '';
   $('#tableBodyProduct').html(html);

   data.forEach((item) => {
      html += `
        <tr class="bg-white border-b dark:bg-gray-900 dark:border-gray-700">
            <th scope="row"class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">${item.id}
            </th>
            <td class="px-6 py-4">${item.name}
            </td>
            <td class="px-6 py-4">
                <img src="${item.img[0]}" alt="" class="rounded-md">
            </td>
            <td class="px-6 py-4">${item.chip}
            </td>
            <td class="px-6 py-4">${item.screen}
            </td>
            <td class="px-6 py-4">${item.rom}
            </td>
            <td class="px-6 py-4">${item.ram}
            </td>
            <td class="px-6 py-4">${item.price}
            </td>
            <td class="px-6 py-4">${item.slug}
            </td>
            <td class="px-6 py-4">${item.type}
            </td>
            <td class="px-6 py-4">${item.bestSeller}
            </td>
            <td class="px-6 py-4">
                <a href="#"
                    class="font-medium text-blue-600 dark:text-blue-500 hover:underline"><i class="fa-regular fa-pen-to-square"></i></a>
            </td>
            <td class="px-6 py-4">
                <a href= "/admin/deleted?id=${item.id}" 
                    class="font-medium text-blue-600 dark:text-blue-500 hover:underline"><i class="fas fa-trash cursor-pointer"></i></a>
            </td>
        </tr>
        `;
   });
   $('#tableBodyProduct').html(html);
}

function GenarateTableUserHTML(data) {
   let html = '';
   $('#tableBodyUser').html(html);

   data.forEach((item) => {
      html += `
        <tr class="bg-white border-b dark:bg-gray-900 dark:border-gray-700">
            <th scope="row"class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">${item.id}
            </th>
            <td class="px-6 py-4">${item.firstName}
            </td>
            <td class="px-6 py-4">${item.lastName}
            </td>
            <td class="px-6 py-4">${item.email}
            </td>
            <td class="px-6 py-4">${item.role}
            </td>
            <td class="px-6 py-4">
                <a href="#"
                    class="font-medium text-blue-600 dark:text-blue-500 hover:underline"><i class="fa-regular fa-pen-to-square"></i></a>
            </td>
            <td class="px-6 py-4">
                <a th:href= "@{/admin/user/delete?email=}+ ${item.email}" 
                    class="font-medium text-blue-600 dark:text-blue-500 hover:underline"><i class="fa-solid fa-user-minus"></i></a>
            </td>
        </tr>
        `;
   });
   $('#tableBodyUser').html(html);
}
