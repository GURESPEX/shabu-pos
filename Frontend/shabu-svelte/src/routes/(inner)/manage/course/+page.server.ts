import type { Course } from '$lib/util/model/model';
import { redirect } from '@sveltejs/kit';
import type { Action, Actions } from '../$types';
import axios from 'axios';

export async function load({ locals }) {
	if (locals.user.role === 1) {
		throw redirect(301, '/manage/table');
	}

	const course: Course = (await axios.get('http://localhost:8082/api/course/')).data;

	const pageData = {
		courses: course
	};

	return { pageData };
}

const createCourse: Action = async ({ request }) => {
	const formData = await request.formData();
	const courseName = formData.get('courseName');
	const courseDescription = formData.get('courseDescription');
	const coursePrice = formData.get('coursePrice');
	axios.post('http://localhost:8082/api/course/create', [
		{
			name: courseName,
			description: courseDescription,
			price: coursePrice
		}
	]);
	throw redirect(301, '/manage/course');
};

const updateCourse: Action = async ({ request }) => {
	const formData = await request.formData();
	const courseId = formData.get('courseId');
	const courseName = formData.get('courseName');
	const courseDescription = formData.get('courseDescription');
	const coursePrice = formData.get('coursePrice');
	axios.post(`http://localhost:8082/api/course/update/${courseId}`, {
		name: courseName,
		description: courseDescription,
		price: coursePrice
	});
	throw redirect(301, '/manage/course');
};

const deleteCourse: Action = async ({ request }) => {
	const formData = await request.formData();
	const courseId = formData.get('courseId');
	axios.post(`http://localhost:8082/api/course/delete/${courseId}`);
	throw redirect(301, '/manage/course');
};

export const actions: Actions = { createCourse, updateCourse, deleteCourse };
