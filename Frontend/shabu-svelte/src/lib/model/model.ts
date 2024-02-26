// Check
export type User = {
	id: number;
	sid: string;
	firstName: string;
	lastName: string;
	email?: string;
	tel: string;
	address: string;
	city: string;
	district: string;
	subDistrict: string;
	postCode: string;
	session?: string;
	createDate: string;
	updateDate?: string;

	loginHistorys: LoginHistory[];
};
export type Admin = User;
export type Employee = User;
// Check
export type Course = {
	id: number;
	name: string;
	description?: string;
	price: number;
	courseDetail: Food[];
};
// Check
export type CustomerTable = {
	id: number;
};
// Check
export type CustomerTableOrder = {
	id: number;
	customerAmount: number;
	canceled: boolean;
	createDate: string;
	updateDate?: string;
	customerTable: CustomerTable;
	customerTableOrderDetail: Course[];
};
// Check
export type Food = {
	id: number;
	name: string;
	description?: string;
	foodType: FoodType;
};
// Check
export type FoodType = {
	id: number;
	name: string;
	foodCategory: FoodCategory;
};
// Check
export type FoodCategory = {
	id: number;
	name: string;
};
// Check
export type LoginHistory = {
	id: number;
	createDate: string;
};
// Check
export type Payment = {
	id: number;
	createDate: string;
	user: User;
	customerTableOrder: CustomerTableOrder;
};
