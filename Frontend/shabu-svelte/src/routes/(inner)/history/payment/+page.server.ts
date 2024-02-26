import type { CustomerTableOrder, Payment } from '$lib/util/model/model';

export async function load() {
	const customerTableOrder: CustomerTableOrder = await (
		await fetch('http://localhost:8082/api/customerTableOrder/', {
			method: 'GET'
		})
	).json();
	const payment: Payment = await (
		await fetch('http://localhost:8082/api/payment/', {
			method: 'GET'
		})
	).json();

	const paymentData = payment.map((e) => {
		return {
			...e,
			customerTableOrder: customerTableOrder.filter((f) => {
				return f.id === e.id;
			})[0]
		};
	});

	const pageData = {
		paymentData
	};

	return { pageData };
}
