import { redirect } from '@sveltejs/kit';
import type { Action, Actions } from './$types';
import axios from 'axios';

const login: Action = async ({ cookies, request }) => {
	const data = await request.formData();
	const userID = data.get('userID');
	const password = data.get('password');

	const session = (await axios.post('http://localhost:8082/api/login', { userID, password })).data;

	if (!session) {
		throw redirect(301, '/login');
	}

	cookies.set('session', session, { maxAge: 60 * 60 * 24 * 30 });

	throw redirect(301, '/');
};

export const actions: Actions = { login };
