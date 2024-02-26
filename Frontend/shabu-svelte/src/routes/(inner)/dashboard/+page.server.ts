import type {
	Admin,
	Course,
	CustomerTable,
	CustomerTableOrder,
	EmployeeFullTime,
	EmployeePartTime,
	Food,
	FoodCategory,
	FoodType,
	LoginHistory,
	Payment
} from '$lib/util/model/model';

export async function load() {
	const food: Food = await (
		await fetch('http://localhost:8082/api/food/', { method: 'GET' })
	).json();
	const foodType: FoodType = await (
		await fetch('http://localhost:8082/api/foodType/', { method: 'GET' })
	).json();
	const foodCategory: FoodCategory = await (
		await fetch('http://localhost:8082/api/foodCategory/', { method: 'GET' })
	).json();
	const course: Course = await (
		await fetch('http://localhost:8082/api/course/', {
			method: 'GET'
		})
	).json();
	const customerTable: CustomerTable = await (
		await fetch('http://localhost:8082/api/customerTable/', {
			method: 'GET'
		})
	).json();
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
	const admin: Admin = await (
		await fetch('http://localhost:8082/api/admin/', { method: 'GET' })
	).json();
	const employeeFullTime: EmployeeFullTime = await (
		await fetch('http://localhost:8082/api/employeeFullTime/', { method: 'GET' })
	).json();
	const employeePartTime: EmployeePartTime = await (
		await fetch('http://localhost:8082/api/employeePartTime/', { method: 'GET' })
	).json();
	const loginHistory: LoginHistory = await (
		await fetch('http://localhost:8082/api/loginHistory/', {
			method: 'GET'
		})
	).json();

	const pageData = {
		food,
		foodType,
		foodCategory,
		course,
		customerTable,
		customerTableOrder,
		payment,
		admin,
		employeeFullTime,
		employeePartTime,
		loginHistory
	};

	return { pageData };
}
