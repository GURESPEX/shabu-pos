<script lang="ts">
	import Input from '$lib/components/ui/input/input.svelte';
	import * as Sheet from '$lib/components/ui/sheet';
	import * as Table from '$lib/components/ui/table';
	import * as Select from '$lib/components/ui/select';
	import * as Dialog from '$lib/components/ui/dialog';
	import Slider from '$lib/components/ui/slider/slider.svelte';
	import Button from '$lib/components/ui/button/button.svelte';
	import {
		BanIcon,
		CheckIcon,
		FullscreenIcon,
		MinusIcon,
		PencilIcon,
		PlusIcon,
		RockingChairIcon,
		ScanEyeIcon,
		Trash2Icon,
		User2Icon,
		XIcon
	} from 'lucide-svelte';
	import type { PageData } from './$types';
	import { number } from 'zod';
	import Label from '$lib/components/ui/label/label.svelte';

	export let data: PageData;

	const { pageData, user } = data;
	const { customerTables, courses } = pageData;

	function increaseCustomerAmount_Input() {
		customerAmount_Input++;
	}

	function decreaseCustomerAmount_Input() {
		customerAmount_Input <= 1 ? customerAmount_Input : customerAmount_Input--;
	}

	type Card = {
		title: string;
		subtitle: string;
		amount: Number;
	};

	let cards: Card[] = [
		{
			title: 'Available Table',
			subtitle: 'Show amount of Available Tables.',
			amount: customerTables.filter((e: any) => {
				return !(e.order && !e.order?.payment && !e.order?.canceled);
			}).length
		},
		{
			title: 'Customer',
			subtitle: 'Show amount of Customers currently.',
			amount: customerTables
				.filter((e: any) => {
					return e.order && !e.order?.payment && !e.order?.canceled;
				})
				.reduce((customerAmount: number, e: any) => {
					return customerAmount + e.order.customerAmount;
				}, 0)
		},
		{
			title: 'Table',
			subtitle: 'Show amount of Tables.',
			amount: customerTables.length
		}
	];

	let search: string = '';
	let tableSelected: any = null;
	let sheetOpen: boolean = false;
	let customerAmount_Input = 1;
	let course_Id: any = null;

	$: filteredTables = customerTables.filter(({ id }: any) =>
		new RegExp(search, 'i').test(`T${id}`)
	);
</script>

<page-title class="flex flex-row items-center gap-4 py-4 px-16 h-20 text-4xl font-bold uppercase">
	Manage Table
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
	<div class="flex flex-row gap-2">
		<Input type="text" placeholder="Search by Name" bind:value={search} />
		{#if user.role !== 1}
			<form action="?/customerTable" method="post" class="flex flex-row gap-2">
				<Button size="icon" formaction="?/createCustomerTable">
					<PlusIcon class="w-4 h-4" />
				</Button>
				<Button size="icon" formaction="?/deleteCustomerTable">
					<Trash2Icon class="w-4 h-4" />
				</Button>
			</form>
		{/if}
	</div>
	{#if filteredTables.length !== 0}
		<div
			class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-2 lg:grid-cols-4 xl:grid-cols-6 2xl:grid-cols-8 gap-2 border-t pt-4 overflow-auto"
		>
			{#each filteredTables as filteredTable}
				<Sheet.Root>
					<Sheet.Trigger>
						<div
							class:border-orange-500={filteredTable.order &&
								!filteredTable.order?.payment &&
								!filteredTable.order?.canceled}
							class="flex flex-col aspect-square bg-white border rounded hover:bg-slate-100 transition-all drop-shadow-sm"
						>
							<button
								class="flex flex-col justify-center items-center flex-1 p-4 font-bold text-5xl"
								on:click={() => {
									tableSelected = filteredTable.id;
									console.log(tableSelected);
								}}
							>
								{`T${filteredTable.id}`}
							</button>
							<div class="w-full p-2">
								<div
									class:text-white={filteredTable.order &&
										!filteredTable.order?.payment &&
										!filteredTable.order?.canceled}
									class:text-slate-400={!(
										filteredTable.order &&
										!filteredTable.order?.payment &&
										!filteredTable.order?.canceled
									)}
									class:bg-orange-400={filteredTable.order &&
										!filteredTable.order?.payment &&
										!filteredTable.order?.canceled}
									class:border={!(
										filteredTable.order &&
										!filteredTable.order?.payment &&
										!filteredTable.order?.canceled
									)}
									class:border-slate-200={!(
										filteredTable.order &&
										!filteredTable.order?.payment &&
										!filteredTable.order?.canceled
									)}
									class="flex flex-row gap-2 justify-center items-center px-2 py-1 rounded text-sm"
								>
									{#if filteredTable.order && !filteredTable.order?.payment && !filteredTable.order?.canceled}
										{filteredTable.order.customerAmount || '-'}
										<User2Icon class="w-4 h-4" />
									{:else}
										Available
									{/if}
								</div>
							</div>
						</div>
					</Sheet.Trigger>
					<Sheet.Content class="flex flex-col p-0 gap-0 sm:max-w-xl">
						<Sheet.Header class="border-b">
							<Sheet.Title class="flex flex-row p-4">
								<div class="flex flex-row items-center text-4xl font-bold uppercase">
									{`T${filteredTable.id}`}
								</div>
							</Sheet.Title>
						</Sheet.Header>
						{#if filteredTable.order && !filteredTable.order?.payment && !filteredTable.order?.canceled}
							<div class="flex flex-col flex-1">
								<Table.Root>
									<Table.Header>
										<Table.Row>
											<Table.Head class="px-4 text-center">Amount</Table.Head>
											<Table.Head class="px-4 text-left w-full">Course</Table.Head>
											<Table.Head class="px-4 text-right">Price</Table.Head>
											<Table.Head class="px-4 text-right">Total</Table.Head>
										</Table.Row>
									</Table.Header>
									<Table.Body>
										<Table.Row>
											<Table.Cell class="px-4 flex flex-row justify-center items-center gap-1">
												{filteredTable.order?.customerAmount}
												<XIcon class="w-3 h-3" />
											</Table.Cell>
											<Table.Cell class="px-4 text-left">
												{filteredTable.order?.course.name}
											</Table.Cell>
											<Table.Cell class="px-4 text-right">
												{filteredTable.order?.course.price}
											</Table.Cell>
											<Table.Cell class="px-4 text-right">
												{filteredTable.order?.course.price * filteredTable.order?.customerAmount}
											</Table.Cell>
										</Table.Row>
									</Table.Body>
								</Table.Root>
							</div>
							<div class="flex flex-col justify-between border-t p-4 gap-4">
								<div class="flex flex-row gap-4">
									<div>Table</div>
									<div class="flex flex-row flex-1 justify-end">T{filteredTable.id}</div>
								</div>
								<div class="flex flex-row gap-4">
									<div>Customer Amount</div>
									<div class="flex flex-row flex-1 justify-end">
										{filteredTable.order?.customerAmount}
									</div>
								</div>
							</div>
							<div class="flex flex-row border-t p-4 gap-4 text-4xl font-semibold">
								<div class="">Total</div>
								<div class="flex flex-row flex-1 gap-4 justify-end">
									<div>
										{(
											filteredTable.order?.customerAmount * filteredTable.order?.course.price
										).toFixed(2)}
									</div>
									<div>฿</div>
								</div>
							</div>

							<form
								action="?/customerTableOrder"
								method="post"
								class="flex flex-row gap-2 p-4 border-t"
							>
								<input type="hidden" name="userID" value={user.sid} />
								<input type="hidden" name="password" value={user.password} />
								<input
									type="hidden"
									name="customerOrderId"
									value={customerTables.filter((e) => e.id === tableSelected)[0].order.id}
								/>
								<Button formaction="?/checkBill" class="flex flex-row flex-1 justify-between gap-4">
									Check Bill <CheckIcon class="w-4 h-4" />
								</Button>
								<Button size="icon" formaction="?/cancelOrder">
									<BanIcon class="w-4 h-4" />
								</Button>
							</form>
						{:else}
							<div class="flex flex-col flex-1 p-4 gap-4">
								<div class="flex flex-col">
									<div class="flex flex-row text-2xl font-semibold">Create Order</div>
									<div class="flex flex-row text-md text-slate-400">
										Enter the order detail and then click create button.
									</div>
								</div>
								<div class="flex flex-col gap-2">
									<div class="flex flex-col gap-2">
										<Label for="customerAmount">Customer Amount</Label>
										<div class="flex flex-row gap-2">
											<Input
												id="customerAmount"
												type="number"
												placeholder="Customer Amount"
												min="1"
												class="flex flex-row flex-1"
												bind:value={customerAmount_Input}
											/>
											<Button variant="outline" size="icon" on:click={increaseCustomerAmount_Input}>
												<PlusIcon class="w-4 h-4" />
											</Button>
											<Button variant="outline" size="icon" on:click={decreaseCustomerAmount_Input}>
												<MinusIcon class="w-4 h-4" />
											</Button>
										</div>
									</div>
									<div class="flex flex-row gap-2">
										<Select.Root
											onSelectedChange={(e) => {
												course_Id = e.value;
											}}
										>
											<Select.Trigger>
												<Select.Value placeholder="Select Course" />
											</Select.Trigger>
											<Select.Content>
												{#each courses as course}
													<Select.Item value={course.id}>{course.name}</Select.Item>
												{/each}
											</Select.Content>
										</Select.Root>
									</div>
								</div>
								<form action="?/order" method="post" class="flex flex-row gap-2">
									<input type="hidden" name="customerAmount" bind:value={customerAmount_Input} />
									<input type="hidden" name="customerTable_Id" bind:value={tableSelected} />
									<input type="hidden" name="course_Id" bind:value={course_Id} />
									<Button
										class="flex-1"
										disabled={customerAmount_Input < 1 ||
											customerAmount_Input === null ||
											course_Id === null}
										formaction="?/createOrder"
									>
										Create Order
									</Button>
								</form>
							</div>
						{/if}
					</Sheet.Content>
				</Sheet.Root>
			{/each}
		</div>
	{:else}
		<div
			class="flex flex-row justify-center items-center gap-2 flex-1 border-t pt-4 text-4xl font-bold uppercase text-slate-400"
		>
			Not found
		</div>
	{/if}
</page-content>

<style>
</style>
