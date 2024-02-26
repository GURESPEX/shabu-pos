import { UserRole } from '$lib/model/enum';
import type { Admin, Employee } from '$lib/util/model/model';
import axios from 'axios';
import type { Action, Actions } from './$types';
import { redirect } from '@sveltejs/kit';

export async function load({ locals }) {
	if (locals.user.role === 1) {
		throw redirect(301, '/manage/table');
	}

	const admin: Admin = (await axios.get('http://localhost:8082/api/admin/')).data;
	const employee: Employee = (await axios.get('http://localhost:8082/api/employee/')).data;

	const allUser = {
		admin: admin.map((e) => {
			return { ...e, role: 'Admin' };
		}),
		employee: employee.map((e) => {
			return { ...e, role: 'Employee' };
		})
	};

	return { allUser };
}

const createAdmin: Action = async ({ request }) => {
	const formData = await request.formData();
	const sid = formData.get('sid');
	const firstname = formData.get('firstname');
	const lastname = formData.get('lastname');
	const email = formData.get('email');
	const tel = formData.get('tel');
	const address = formData.get('address');
	const city = formData.get('city');
	const district = formData.get('district');
	const subDistrict = formData.get('subDistrict');
	const postCode = formData.get('postCode');
	const password = formData.get('password');
	const confirmPassword = formData.get('confirmPassword');

	if (password === confirmPassword) {
		axios.post('http://localhost:8082/api/admin/create', [
			{
				sid,
				firstname,
				lastname,
				email,
				tel,
				address,
				city,
				district,
				subDistrict,
				postCode,
				password,
				payments: null,
				loginHistories: null
			}
		]);
	} else {
		throw redirect(301, '/manage/user');
	}
};

const updateAdmin: Action = async ({ request }) => {
	const formData = await request.formData();
	const id = formData.get('id');
	const sid = formData.get('sid');
	const firstname = formData.get('firstname');
	const lastname = formData.get('lastname');
	const email = formData.get('email');
	const tel = formData.get('tel');
	const address = formData.get('address');
	const city = formData.get('city');
	const district = formData.get('district');
	const subDistrict = formData.get('subDistrict');
	const postCode = formData.get('postCode');
	const password = formData.get('password');

	axios.post(`http://localhost:8082/api/admin/update/${id}`, {
		sid,
		firstname,
		lastname,
		email,
		tel,
		address,
		city,
		district,
		subDistrict,
		postCode,
		password,
		payments: null,
		loginHistories: null
	});
};

const deleteAdmin: Action = async ({ request }) => {
	const formData = await request.formData();
	const id = formData.get('id');
	axios.post(`http://localhost:8082/api/admin/delete/${id}`);
};

/* ----- */

const createEmployee: Action = async ({ request }) => {
	const formData = await request.formData();
	const sid = formData.get('sid');
	const firstname = formData.get('firstname');
	const lastname = formData.get('lastname');
	const email = formData.get('email');
	const tel = formData.get('tel');
	const address = formData.get('address');
	const city = formData.get('city');
	const district = formData.get('district');
	const subDistrict = formData.get('subDistrict');
	const postCode = formData.get('postCode');
	const password = formData.get('password');
	const confirmPassword = formData.get('confirmPassword');

	if (password === confirmPassword) {
		axios.post('http://localhost:8082/api/employee/create', [
			{
				sid,
				firstname,
				lastname,
				email,
				tel,
				address,
				city,
				district,
				subDistrict,
				postCode,
				password,
				payments: null,
				loginHistories: null
			}
		]);
	} else {
		throw redirect(301, '/manage/user');
	}
};

const updateEmployee: Action = async ({ request }) => {
	const formData = await request.formData();
	const id = formData.get('id');
	const sid = formData.get('sid');
	const firstname = formData.get('firstname');
	const lastname = formData.get('lastname');
	const email = formData.get('email');
	const tel = formData.get('tel');
	const address = formData.get('address');
	const city = formData.get('city');
	const district = formData.get('district');
	const subDistrict = formData.get('subDistrict');
	const postCode = formData.get('postCode');
	const password = formData.get('password');

	axios.post(`http://localhost:8082/api/employee/update/${id}`, {
		sid,
		firstname,
		lastname,
		email,
		tel,
		address,
		city,
		district,
		subDistrict,
		postCode,
		password,
		payments: null,
		loginHistories: null
	});
};

const deleteEmployee: Action = async ({ request }) => {
	const formData = await request.formData();
	const id = formData.get('id');
	axios.post(`http://localhost:8082/api/employee/delete/${id}`);
	console.log('Deleted', id);
};

export const actions: Actions = {
	createAdmin,
	updateAdmin,
	deleteAdmin,
	createEmployee,
	updateEmployee,
	deleteEmployee
};
