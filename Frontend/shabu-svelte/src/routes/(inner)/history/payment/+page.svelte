<script lang="ts">
	import * as Table from '$lib/components/ui/table';
	import * as Sheet from '$lib/components/ui/sheet';
	import Button from '$lib/components/ui/button/button.svelte';
	import Input from '$lib/components/ui/input/input.svelte';
	import { PlusIcon, Trash2Icon, XIcon } from 'lucide-svelte';
	import type { PageData } from './$types';
	export let data: PageData;
	const { pageData } = data;
	let { paymentData } = pageData;
	paymentData = paymentData.map((e) => {
		return { ...e, fullId: `#${String(e.id).padStart(8, '0')}` };
	});

	paymentData.reverse();

	type Card = {
		title: string;
		subtitle: string;
		amount: Number;
	};

	let cards: Card[] = [
		{
			title: 'Payment History',
			subtitle: 'Show amount of Payment Histories.',
			amount: paymentData.length
		}
	];

	let search: string = '';

	$: filteredPayments = paymentData.filter(({ fullId }: any) =>
		new RegExp(search, 'i').test(fullId)
	);
</script>

<page-title class="flex flex-row items-center gap-4 py-4 px-16 h-20 text-4xl font-bold uppercase">
	Payment History
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
		<Input type="text" placeholder="Search by ID" bind:value={search} />
	</div>
	<div class="border-t overflow-auto">
		<div class="grid grid-cols-1 lg:grid-cols-2 xl:grid-cols-3 gap-2 pt-4 overflow-auto">
			{#each filteredPayments as payment}
				<Sheet.Root>
					<Sheet.Trigger
						class="flex flex-col gap-2 bg-white border rounded hover:bg-slate-100 transition-all drop-shadow-sm"
					>
						<div class="flex flex-col p-4 gap-2 w-full">
							<div
								class="flex flex-row justify-between items-center gap-4 w-full font-semibold text-2xl"
							>
								{payment.fullId}
								<div
									class="flex flex-row justify-center items-center px-2 h-8 text-sm border rounded text-slate-400"
								>
									{payment.customerTableOrder.course.name}
								</div>
							</div>
							<div class="flex flex-row text-md text-slate-400 flex-1">
								{new Date(payment.createDate).toLocaleString()}
							</div>
						</div>
						<div class="flex flex-row p-4 text-2xl text-orange-400 border-t w-full font-semibold">
							฿ {(
								payment.customerTableOrder.course.price * payment.customerTableOrder.customerAmount
							).toFixed(2)}
						</div>
					</Sheet.Trigger>
					<Sheet.Content class="flex flex-col p-0 gap-0 sm:max-w-xl">
						<Sheet.Header class="border-b">
							<Sheet.Title class="flex flex-row p-4">
								<div class="flex flex-row items-center text-4xl font-bold uppercase">
									{payment.fullId}
								</div>
							</Sheet.Title>
						</Sheet.Header>
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
											{payment.customerTableOrder.customerAmount}
											<XIcon class="w-3 h-3" />
										</Table.Cell>
										<Table.Cell class="px-4 text-left">
											{payment.customerTableOrder.course.name}
										</Table.Cell>
										<Table.Cell class="px-4 text-right">
											{payment.customerTableOrder.course.price}
										</Table.Cell>
										<Table.Cell class="px-4 text-right">
											{payment.customerTableOrder.course.price *
												payment.customerTableOrder.customerAmount}
										</Table.Cell>
									</Table.Row>
								</Table.Body>
							</Table.Root>
						</div>
						<div class="flex flex-col justify-between border-t p-4 gap-4">
							<div class="flex flex-row gap-4">
								<div>Billing by</div>
								<div class="flex flex-row flex-1 justify-end">
									{payment.user.firstname}
									{payment.user.lastname}
								</div>
							</div>
						</div>
						<div class="flex flex-col justify-between border-t p-4 gap-4">
							<div class="flex flex-row gap-4">
								<div>Table</div>
								<div class="flex flex-row flex-1 justify-end">
									T{payment.customerTableOrder.customerTable.id}
								</div>
							</div>
							<div class="flex flex-row gap-4">
								<div>Customer Amount</div>
								<div class="flex flex-row flex-1 justify-end">
									{payment.customerTableOrder.customerAmount}
								</div>
							</div>
							<div class="flex flex-row gap-4">
								<div>Payment Date</div>
								<div class="flex flex-row flex-1 justify-end">
									{new Date(payment.createDate).toLocaleString()}
								</div>
							</div>
						</div>
						<div class="flex flex-row border-t p-4 gap-4 text-4xl font-semibold">
							<div class="">Total</div>
							<div class="flex flex-row flex-1 gap-4 justify-end">
								<div>
									{(
										payment.customerTableOrder.customerAmount *
										payment.customerTableOrder.course.price
									).toFixed(2)}
								</div>
								<div>฿</div>
							</div>
						</div>
					</Sheet.Content>
				</Sheet.Root>
			{/each}
		</div>
	</div>
</page-content>
