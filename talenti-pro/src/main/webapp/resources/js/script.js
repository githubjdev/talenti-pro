function mascaraCPF(campo) {
	campo.value = campo.value
		.replace(/\D/g, '')
		.replace(/(\d{3})(\d)/, '$1.$2')
		.replace(/(\d{3})(\d)/, '$1.$2')
		.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
}



function somenteNumeros(input) {
    // input pode ser o próprio elemento ou um event.target
    var el = input.target ? input.target : input;

    el.value = el.value.replace(/\D/g, ''); // remove tudo que não é dígito
}


function validarEmailField(campo) {
	if (!validarEmail(campo.value)) {
		alert('E-mail inválido');
		setTimeout(() => campo.focus(), 10); // ❗ impede loop/travamento
	}
}

function validarEmail(email) {
	const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
	return regex.test(email);
}
