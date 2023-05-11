// event input search
let inputSearch = document.getElementById('inputSearch');
inputSearch.addEventListener('input', function () {
	$.ajax({
		url: '/admin/user/search',
		type: 'POST',
		data: { search: this.value },
		success: function (data) {
			console.log(data);
			GenerateTable(data);
		},
	});
});

function GenerateTable(data) {
	let html = '';
	let i = 1;
	data.forEach(function (item) {
		html += `<tr>
		<td>${i}</td>
		<td class="text-center">${item.id}</td>
		<td>${item.id}</td>
		<td>${item.firstName}</td>
		<td>${item.lastName}</td>
        <td class="text-center">1502154</td>
		<td class="text-center">
        	<input type="checkbox" />
        </td>
	</tr>`;
		i++;
	});
	$('#tableBodyDs').html(html);
}
