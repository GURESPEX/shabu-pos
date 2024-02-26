import { UserRole } from '$lib/model/enum';
import type { EmployeeFullTime, EmployeePartTime } from '$lib/model/model';
import type { Handle } from '@sveltejs/kit';

import axios from 'axios';

export const handle: Handle = async ({ event, resolve }) => {
	// const response = await fetch('http://localhost:8082/api/user/', { method: 'GET' });
	const session = event.cookies.get('session');

	let admin = (await axios.get('http://localhost:8082/api/admin/')).data;

	let employee: EmployeeFullTime = (await axios.get('http://localhost:8082/api/employee/')).data;

	admin = admin
		.filter((e) => e.session === session)
		.map((e) => {
			return { ...e, role: UserRole.ADMIN };
		})[0];
	employee = employee
		.filter((e) => e.session === session)
		.map((e) => {
			return { ...e, role: UserRole.EMPLOYEE };
		})[0];

	const user = [admin, employee].filter((e) => e?.session === session)[0] || null;

	event.locals.user = user;

	console.log(event.locals);

	return await resolve(event);
};
