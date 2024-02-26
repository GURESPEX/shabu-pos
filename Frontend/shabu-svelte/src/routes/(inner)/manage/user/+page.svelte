<script lang="ts">
	import { PlusIcon, Trash2Icon, User2Icon } from 'lucide-svelte';
	import * as Tabs from '$lib/components/ui/tabs';
	import * as Select from '$lib/components/ui/select';
	import type { PageData } from './$types';
	import * as Sheet from '$lib/components/ui/sheet';
	import { Button } from '$lib/components/ui/button';
	import { Input } from '$lib/components/ui/input';
	import { Label } from '$lib/components/ui/label';
	import Textarea from '$lib/components/ui/textarea/textarea.svelte';
	import * as Avatar from '$lib/components/ui/avatar';
	import { UserRole } from '$lib/model/enum';
	export let data: PageData;
	const { allUser } = data;
	let { admin, employee } = allUser;

	type Card = {
		title: string;
		subtitle: string;
		amount: Number;
	};

	let cards: Card[] = [
		{
			title: 'Employee',
			subtitle: 'Show amount of Employees.',
			amount: allUser.employee.length
		},
		{
			title: 'Admin',
			subtitle: 'Show amount of Admins.',
			amount: allUser.admin.length
		}
	];

	let search: string = '';

	$: filteredAdmins = admin.filter(({ firstname, lastname }: any) =>
		new RegExp(search, 'i').test(`${firstname} ${lastname}`)
	);
	$: filteredEmployees = employee.filter(({ firstname, lastname }: any) =>
		new RegExp(search, 'i').test(`${firstname} ${lastname}`)
	);
</script>

<page-title class="flex flex-row items-center gap-4 py-4 px-16 h-20 text-4xl font-bold uppercase">
	Manage User
</page-title>
<page-header class="flex flex-row gap-4 py-4 px-16 bg-white border-y">
	{#each cards as { title, subtitle, amount }}
		<card class="flex flex-col flex-1 gap-4 justify-between drop-shadow-sm">
			<card-title-box class="flex flex-col gap-1">
				<card-title class="text-2xl font-semibold">{title}</card-title>
				<card-subtitle class="text-md text-slate-400">{subtitle}</card-subtitle>
			</card-title-box>
			<card-amount
				class="flex flex-row gap-4 justify-between items-end text-4xl text-white font-bold truncate p-4 bg-slate-900 drop-shadow rounded"
			>
				<div class="truncate">{amount}</div>
			</card-amount>
		</card>
	{/each}
</page-header>
<page-content class="flex flex-col flex-1 gap-4 py-4 px-16 pb-0 overflow-hidden">
	<Tabs.Root value="employee" class="flex flex-col">
		<Tabs.List class="flex flex-row gap-1">
			<Tabs.Trigger value="employee" class="flex flex-row flex-1">Employee</Tabs.Trigger>
			<Tabs.Trigger value="admin" class="flex flex-row flex-1">Admin</Tabs.Trigger>
		</Tabs.List>
		<Tabs.Content value="employee">
			<div class="flex flex-row gap-2 mb-4">
				<Input type="text" placeholder="Search by Name" bind:value={search} />
				<Sheet.Root>
					<Sheet.Trigger>
						<Button size="icon">
							<PlusIcon class="w-4 h-4" />
						</Button>
					</Sheet.Trigger>
					<Sheet.Content class="flex flex-col p-0 gap-0 sm:max-w-xl">
						<Sheet.Header class="border-b">
							<Sheet.Title class="flex flex-row p-4">
								<div class="flex flex-row items-center text-4xl font-bold uppercase">
									Create Employee
								</div>
							</Sheet.Title>
						</Sheet.Header>
						<div class="flex flex-col p-4">
							<form action="?/employee" method="post" class="flex flex-col gap-4">
								<div class="flex flex-col gap-2">
									<Label for="sid">Personal ID <span class="text-red-400">*</span></Label>
									<Input
										id="sid"
										name="sid"
										type="text"
										placeholder="Enter Personal ID"
										minlength={13}
										maxlength={13}
										required
									/>
								</div>
								<div class="flex flex-row gap-2">
									<div class="flex flex-col gap-2 flex-1">
										<Label for="firstname">Firstname <span class="text-red-400">*</span></Label>
										<Input
											id="firstname"
											name="firstname"
											type="text"
											placeholder="Enter Firstname"
											required
										/>
									</div>
									<div class="flex flex-col gap-2 flex-1">
										<Label for="lastname">Lastname <span class="text-red-400">*</span></Label>
										<Input
											id="lastname"
											name="lastname"
											type="text"
											placeholder="Enter Lastname"
											required
										/>
									</div>
								</div>
								<div class="flex flex-col gap-2">
									<Label for="email">Email</Label>
									<Input id="email" name="email" type="email" placeholder="Enter Email" />
								</div>
								<div class="flex flex-col gap-2">
									<Label for="tel">Phone Number <span class="text-red-400">*</span></Label>
									<Input
										id="tel"
										name="tel"
										type="tel"
										minlength={10}
										maxlength={10}
										placeholder="Enter Phone Number"
										required
									/>
								</div>
								<div class="flex flex-col gap-2">
									<Label for="address">Address <span class="text-red-400">*</span></Label>
									<Textarea id="address" name="address" placeholder="Enter Address" />
								</div>
								<div class="flex flex-row gap-2">
									<div class="flex flex-col gap-2 flex-1">
										<Label for="city">City <span class="text-red-400">*</span></Label>
										<Input id="city" name="city" type="text" placeholder="Enter City" required />
									</div>
									<div class="flex flex-col gap-2 flex-1">
										<Label for="district">District <span class="text-red-400">*</span></Label>
										<Input
											id="district"
											name="district"
											type="text"
											placeholder="Enter District"
											required
										/>
									</div>
								</div>
								<div class="flex flex-row gap-2">
									<div class="flex flex-col gap-2 flex-1">
										<Label for="subDistrict">Sub-District <span class="text-red-400">*</span></Label
										>
										<Input
											id="subDistrict"
											name="subDistrict"
											type="text"
											placeholder="Enter Sub-District"
											required
										/>
									</div>
									<div class="flex flex-col gap-2 flex-1">
										<Label for="postCode">Postcode <span class="text-red-400">*</span></Label>
										<Input
											id="postCode"
											name="postCode"
											type="text"
											maxlength={5}
											placeholder="Enter Postcode"
											required
										/>
									</div>
								</div>
								<div class="flex flex-col gap-2">
									<Label for="password">Password <span class="text-red-400">*</span></Label>
									<Input
										id="password"
										name="password"
										type="text"
										minlength={8}
										placeholder="Enter Password"
										required
									/>
								</div>
								<div class="flex flex-col gap-2">
									<Label for="confirmPassword"
										>Confirm Password <span class="text-red-400">*</span></Label
									>
									<Input
										id="confirmPassword"
										name="confirmPassword"
										type="password"
										minlength={8}
										placeholder="Retype to confirm Password"
										required
									/>
								</div>
								<Button formaction="?/createEmployee">Create</Button>
							</form>
						</div>
					</Sheet.Content>
				</Sheet.Root>
			</div>
			<div
				class="grid grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 gap-2 border-t pt-4 overflow-auto"
			>
				{#each filteredEmployees as employee}
					<Sheet.Root>
						<Sheet.Trigger
							class="flex flex-col bg-white border rounded hover:bg-slate-100 transition-all drop-shadow-sm"
						>
							<div class="flex flex-col p-4 gap-2 w-full">
								<div class="flex flex-row items-center gap-4 w-full font-semibold overflow-hidden">
									<Avatar.Root class="w-16 h-16">
										<Avatar.Image src="/unknow-profile.svg" alt="@shadcn" />
										<Avatar.Fallback>
											{employee.firstname[0].toUpperCase()}{employee.lastname[0].toUpperCase()}
										</Avatar.Fallback>
									</Avatar.Root>
									<div class="flex flex-col">
										<div class="flex flex-row gap-2 items-center truncate">
											<User2Icon class="w-4 h-4" />{employee.firstname}
											{employee.lastname}
										</div>
										<div
											class="flex flex-row gap-2 items-center text-xs uppercase text-slate-400 font-normal"
										>
											{employee.role}
										</div>
									</div>
								</div>
							</div>
						</Sheet.Trigger>
						<Sheet.Content class="flex flex-col p-0 gap-0 sm:max-w-xl">
							<Sheet.Header class="border-b">
								<Sheet.Title class="flex flex-row p-4">
									<div class="flex flex-row items-center text-4xl font-bold uppercase">
										Update Employee
									</div>
								</Sheet.Title>
							</Sheet.Header>
							<div class="flex flex-col p-4 gap-2">
								<form action="?/employee" method="post" class="flex flex-col gap-4">
									<input name="id" type="hidden" value={employee.id} />
									<div class="flex flex-col gap-2">
										<Label for="sid">Personal ID <span class="text-red-400">*</span></Label>
										<Input
											id="sid"
											name="sid"
											type="text"
											placeholder="Enter Personal ID"
											minlength={13}
											maxlength={13}
											value={employee.sid}
											required
										/>
									</div>
									<div class="flex flex-row gap-2">
										<div class="flex flex-col gap-2 flex-1">
											<Label for="firstname">Firstname <span class="text-red-400">*</span></Label>
											<Input
												id="firstname"
												name="firstname"
												type="text"
												placeholder="Enter Firstname"
												value={employee.firstname}
												required
											/>
										</div>
										<div class="flex flex-col gap-2 flex-1">
											<Label for="lastname">Lastname <span class="text-red-400">*</span></Label>
											<Input
												id="lastname"
												name="lastname"
												type="text"
												placeholder="Enter Lastname"
												value={employee.lastname}
												required
											/>
										</div>
									</div>
									<div class="flex flex-col gap-2">
										<Label for="email">Email</Label>
										<Input
											id="email"
											name="email"
											type="email"
											placeholder="Enter Email"
											value={employee.email}
										/>
									</div>
									<div class="flex flex-col gap-2">
										<Label for="tel">Phone Number <span class="text-red-400">*</span></Label>
										<Input
											id="tel"
											name="tel"
											type="tel"
											minlength={10}
											maxlength={10}
											placeholder="Enter Phone Number"
											value={employee.tel}
											required
										/>
									</div>
									<div class="flex flex-col gap-2">
										<Label for="address">Address <span class="text-red-400">*</span></Label>
										<Textarea
											id="address"
											name="address"
											placeholder="Enter Address"
											value={employee.address}
										/>
									</div>
									<div class="flex flex-row gap-2">
										<div class="flex flex-col gap-2 flex-1">
											<Label for="city">City <span class="text-red-400">*</span></Label>
											<Input
												id="city"
												name="city"
												type="text"
												placeholder="Enter City"
												value={employee.city}
												required
											/>
										</div>
										<div class="flex flex-col gap-2 flex-1">
											<Label for="district">District <span class="text-red-400">*</span></Label>
											<Input
												id="district"
												name="district"
												type="text"
												placeholder="Enter District"
												value={employee.district}
												required
											/>
										</div>
									</div>
									<div class="flex flex-row gap-2">
										<div class="flex flex-col gap-2 flex-1">
											<Label for="subDistrict"
												>Sub-District <span class="text-red-400">*</span></Label
											>
											<Input
												id="subDistrict"
												name="subDistrict"
												type="text"
												placeholder="Enter Sub-District"
												value={employee.subDistrict}
												required
											/>
										</div>
										<div class="flex flex-col gap-2 flex-1">
											<Label for="postCode">Postcode <span class="text-red-400">*</span></Label>
											<Input
												id="postCode"
												name="postCode"
												type="text"
												maxlength={5}
												placeholder="Enter Postcode"
												value={employee.postCode}
												required
											/>
										</div>
									</div>
									<div class="flex flex-col gap-2">
										<Label for="password">Password <span class="text-red-400">*</span></Label>
										<Input
											id="password"
											name="password"
											type="text"
											minlength={8}
											placeholder="Enter Password"
											value={employee.password}
											required
										/>
									</div>
									<Button formaction="?/updateEmployee">Update</Button>
								</form>
								<form action="?/employee" method="post" class="flex flex-row">
									<input name="id" type="hidden" value={employee.id} />
									<Button size="icon" formaction="?/deleteEmployee" class="flex-1"
										><Trash2Icon class="w-4 h-4" /></Button
									>
								</form>
							</div>
						</Sheet.Content>
					</Sheet.Root>
				{/each}
			</div>
		</Tabs.Content>
		<Tabs.Content value="admin">
			<div class="flex flex-row gap-2 mb-4">
				<Input type="text" placeholder="Search by Name" bind:value={search} />
				<Sheet.Root>
					<Sheet.Trigger>
						<Button size="icon">
							<PlusIcon class="w-4 h-4" />
						</Button>
					</Sheet.Trigger>
					<Sheet.Content class="flex flex-col p-0 gap-0 sm:max-w-xl">
						<Sheet.Header class="border-b">
							<Sheet.Title class="flex flex-row p-4">
								<div class="flex flex-row items-center text-4xl font-bold uppercase">
									Create Admin
								</div>
							</Sheet.Title>
						</Sheet.Header>
						<div class="flex flex-col p-4">
							<form action="?/admin" method="post" class="flex flex-col gap-4">
								<div class="flex flex-col gap-2">
									<Label for="sid">Personal ID <span class="text-red-400">*</span></Label>
									<Input
										id="sid"
										name="sid"
										type="text"
										placeholder="Enter Personal ID"
										minlength={13}
										maxlength={13}
										required
									/>
								</div>
								<div class="flex flex-row gap-2">
									<div class="flex flex-col gap-2 flex-1">
										<Label for="firstname">Firstname <span class="text-red-400">*</span></Label>
										<Input
											id="firstname"
											name="firstname"
											type="text"
											placeholder="Enter Firstname"
											required
										/>
									</div>
									<div class="flex flex-col gap-2 flex-1">
										<Label for="lastname">Lastname <span class="text-red-400">*</span></Label>
										<Input
											id="lastname"
											name="lastname"
											type="text"
											placeholder="Enter Lastname"
											required
										/>
									</div>
								</div>
								<div class="flex flex-col gap-2">
									<Label for="email">Email</Label>
									<Input id="email" name="email" type="email" placeholder="Enter Email" />
								</div>
								<div class="flex flex-col gap-2">
									<Label for="tel">Phone Number <span class="text-red-400">*</span></Label>
									<Input
										id="tel"
										name="tel"
										type="tel"
										minlength={10}
										maxlength={10}
										placeholder="Enter Phone Number"
										required
									/>
								</div>
								<div class="flex flex-col gap-2">
									<Label for="address">Address <span class="text-red-400">*</span></Label>
									<Textarea id="address" name="address" placeholder="Enter Address" />
								</div>
								<div class="flex flex-row gap-2">
									<div class="flex flex-col gap-2 flex-1">
										<Label for="city">City <span class="text-red-400">*</span></Label>
										<Input id="city" name="city" type="text" placeholder="Enter City" required />
									</div>
									<div class="flex flex-col gap-2 flex-1">
										<Label for="district">District <span class="text-red-400">*</span></Label>
										<Input
											id="district"
											name="district"
											type="text"
											placeholder="Enter District"
											required
										/>
									</div>
								</div>
								<div class="flex flex-row gap-2">
									<div class="flex flex-col gap-2 flex-1">
										<Label for="subDistrict">Sub-District <span class="text-red-400">*</span></Label
										>
										<Input
											id="subDistrict"
											name="subDistrict"
											type="text"
											placeholder="Enter Sub-District"
											required
										/>
									</div>
									<div class="flex flex-col gap-2 flex-1">
										<Label for="postCode">Postcode <span class="text-red-400">*</span></Label>
										<Input
											id="postCode"
											name="postCode"
											type="text"
											maxlength={5}
											placeholder="Enter Postcode"
											required
										/>
									</div>
								</div>
								<div class="flex flex-col gap-2">
									<Label for="password">Password <span class="text-red-400">*</span></Label>
									<Input
										id="password"
										name="password"
										type="text"
										minlength={8}
										placeholder="Enter Password"
										required
									/>
								</div>
								<div class="flex flex-col gap-2">
									<Label for="confirmPassword"
										>Confirm Password <span class="text-red-400">*</span></Label
									>
									<Input
										id="confirmPassword"
										name="confirmPassword"
										type="password"
										minlength={8}
										placeholder="Retype to confirm Password"
										required
									/>
								</div>
								<Button formaction="?/createAdmin">Create</Button>
							</form>
						</div>
					</Sheet.Content>
				</Sheet.Root>
			</div>
			<div
				class="grid grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 gap-2 border-t pt-4 overflow-auto"
			>
				{#each filteredAdmins as admin}
					<Sheet.Root>
						<Sheet.Trigger
							class="flex flex-col bg-white border rounded hover:bg-slate-100 transition-all drop-shadow-sm"
						>
							<div class="flex flex-col p-4 gap-2 w-full">
								<div class="flex flex-row items-center gap-4 w-full font-semibold overflow-hidden">
									<Avatar.Root class="w-16 h-16">
										<Avatar.Image src="/unknow-profile.svg" alt="@shadcn" />
										<Avatar.Fallback>
											{admin.firstname[0].toUpperCase()}{admin.lastname[0].toUpperCase()}
										</Avatar.Fallback>
									</Avatar.Root>
									<div class="flex flex-col">
										<div class="flex flex-row gap-2 items-center truncate">
											<User2Icon class="w-4 h-4" />{admin.firstname}
											{admin.lastname}
										</div>
										<div
											class="flex flex-row gap-2 items-center text-xs uppercase text-slate-400 font-normal"
										>
											{admin.role}
										</div>
									</div>
								</div>
							</div>
						</Sheet.Trigger>
						<Sheet.Content class="flex flex-col p-0 gap-0 sm:max-w-xl">
							<Sheet.Header class="border-b">
								<Sheet.Title class="flex flex-row p-4">
									<div class="flex flex-row items-center text-4xl font-bold uppercase">
										Update Admin
									</div>
								</Sheet.Title>
							</Sheet.Header>
							<div class="flex flex-col p-4 gap-2">
								<form action="?/admin" method="post" class="flex flex-col gap-4">
									<input name="id" type="hidden" value={admin.id} />
									<div class="flex flex-col gap-2">
										<Label for="sid">Personal ID <span class="text-red-400">*</span></Label>
										<Input
											id="sid"
											name="sid"
											type="text"
											placeholder="Enter Personal ID"
											minlength={13}
											maxlength={13}
											value={admin.sid}
											required
										/>
									</div>
									<div class="flex flex-row gap-2">
										<div class="flex flex-col gap-2 flex-1">
											<Label for="firstname">Firstname <span class="text-red-400">*</span></Label>
											<Input
												id="firstname"
												name="firstname"
												type="text"
												placeholder="Enter Firstname"
												value={admin.firstname}
												required
											/>
										</div>
										<div class="flex flex-col gap-2 flex-1">
											<Label for="lastname">Lastname <span class="text-red-400">*</span></Label>
											<Input
												id="lastname"
												name="lastname"
												type="text"
												placeholder="Enter Lastname"
												value={admin.lastname}
												required
											/>
										</div>
									</div>
									<div class="flex flex-col gap-2">
										<Label for="email">Email</Label>
										<Input
											id="email"
											name="email"
											type="email"
											placeholder="Enter Email"
											value={admin.email}
										/>
									</div>
									<div class="flex flex-col gap-2">
										<Label for="tel">Phone Number <span class="text-red-400">*</span></Label>
										<Input
											id="tel"
											name="tel"
											type="tel"
											minlength={10}
											maxlength={10}
											placeholder="Enter Phone Number"
											value={admin.tel}
											required
										/>
									</div>
									<div class="flex flex-col gap-2">
										<Label for="address">Address <span class="text-red-400">*</span></Label>
										<Textarea
											id="address"
											name="address"
											placeholder="Enter Address"
											value={admin.address}
										/>
									</div>
									<div class="flex flex-row gap-2">
										<div class="flex flex-col gap-2 flex-1">
											<Label for="city">City <span class="text-red-400">*</span></Label>
											<Input
												id="city"
												name="city"
												type="text"
												placeholder="Enter City"
												value={admin.city}
												required
											/>
										</div>
										<div class="flex flex-col gap-2 flex-1">
											<Label for="district">District <span class="text-red-400">*</span></Label>
											<Input
												id="district"
												name="district"
												type="text"
												placeholder="Enter District"
												value={admin.district}
												required
											/>
										</div>
									</div>
									<div class="flex flex-row gap-2">
										<div class="flex flex-col gap-2 flex-1">
											<Label for="subDistrict"
												>Sub-District <span class="text-red-400">*</span></Label
											>
											<Input
												id="subDistrict"
												name="subDistrict"
												type="text"
												placeholder="Enter Sub-District"
												value={admin.subDistrict}
												required
											/>
										</div>
										<div class="flex flex-col gap-2 flex-1">
											<Label for="postCode">Postcode <span class="text-red-400">*</span></Label>
											<Input
												id="postCode"
												name="postCode"
												type="text"
												maxlength={5}
												placeholder="Enter Postcode"
												value={admin.postCode}
												required
											/>
										</div>
									</div>
									<div class="flex flex-col gap-2">
										<Label for="password">Password <span class="text-red-400">*</span></Label>
										<Input
											id="password"
											name="password"
											type="text"
											minlength={8}
											placeholder="Enter Password"
											value={admin.password}
											required
										/>
									</div>
									<Button formaction="?/updateAdmin">Update</Button>
								</form>
								<form action="?/course" method="post" class="flex flex-row">
									<input name="id" type="hidden" value={admin.id} />
									<Button size="icon" formaction="?/deleteAdmin" class="flex-1"
										><Trash2Icon class="w-4 h-4" /></Button
									>
								</form>
							</div>
						</Sheet.Content>
					</Sheet.Root>
				{/each}
			</div>
		</Tabs.Content>
	</Tabs.Root>
</page-content>

<style>
</style>
