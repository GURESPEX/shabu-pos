import type { CustomerTable, CustomerTableOrder, Payment, Course } from '$lib/util/model/model';
import { redirect } from '@sveltejs/kit';
import type { Action, Actions } from './$types';
import axios from 'axios';

export async function load() {
	const customerTable: CustomerTable = (await axios.get('http://localhost:8082/api/customerTable/'))
		.data;
	const customerTableOrder: CustomerTableOrder = (
		await axios.get('http://localhost:8082/api/customerTableOrder/')
	).data;
	const payment: Payment = (await axios.get('http://localhost:8082/api/payment/')).data;

	const course: Course = (await axios.get('http://localhost:8082/api/course/')).data;

	const customerTables = customerTable.map((customerTableE: unknown) => {
		return {
			id: customerTableE.id,
			order:
				customerTableOrder
					.filter((customerTableOrderE: unknown) => {
						return customerTableE.id === customerTableOrderE.customerTable.id;
					})
					.map((customerTableOrderE: unknown) => {
						return {
							...customerTableOrderE,
							payment:
								payment
									.filter((paymentE: unknown) => {
										return paymentE.id === customerTableOrderE.id;
									})
									.at(0) || null
						};
					})
					.at(-1) || null
		};
	});

	const pageData = {
		customerTables,
		courses: course
	};

	return { pageData };
}

const createCustomerTable: Action = async () => {
	axios.post('http://localhost:8082/api/customerTable/create');
	throw redirect(301, '/manage/table');
};

const deleteCustomerTable: Action = async () => {
	axios.post('http://localhost:8082/api/customerTable/delete');
	throw redirect(301, '/manage/table');
};

const createOrder: Action = async ({ request }) => {
	const formData = await request.formData();
	const customerAmount = formData.get('customerAmount');
	const customerTable_Id = formData.get('customerTable_Id');
	const course_Id = formData.get('course_Id');

	try {
		axios.post('http://localhost:8082/api/customerTableOrder/create', [
			{
				customerAmount: Number.parseInt(customerAmount as string),
				canceled: false,
				payment: null,
				customerTable: {
					id: Number.parseInt(customerTable_Id as string)
				},
				course: {
					id: Number.parseInt(course_Id as string)
				}
			}
		]);
	} catch (error) {
		console.log(error);
	}

	throw redirect(301, '/manage/table');
};

const checkBill: Action = async ({ request }) => {
	const formData = await request.formData();
	const userID = formData.get('userID');
	const password = formData.get('password');
	const customerOrderId = formData.get('customerOrderId');
	axios.post('http://localhost:8082/api/checkBill', {
		userID,
		password,
		customerOrderId: Number.parseInt(customerOrderId as string)
	});
	throw redirect(301, '/manage/table');
};

const cancelOrder: Action = async ({ request }) => {
	const formData = await request.formData();
	const customerOrderId = formData.get('customerOrderId');
	axios.post(`http://localhost:8082/api/customerTableOrder/cancelOrder/${customerOrderId}`);
	throw redirect(301, '/manage/table');
};

export const actions: Actions = {
	createCustomerTable,
	deleteCustomerTable,
	createOrder,
	checkBill,
	cancelOrder
};
